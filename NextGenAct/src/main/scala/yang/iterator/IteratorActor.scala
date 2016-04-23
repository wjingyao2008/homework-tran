package yang.iterator

import akka.actor.{Status, ActorRef, ActorLogging, Actor}
import org.omg.CosNotification.StructuredEvent
import yang.iterator.IteratorProtocol._

import scala.collection.mutable.ListBuffer

/**
  * Created by y28yang on 2/18/2016.
  */
class IteratorActor(dataPoller: DataPoller,
                    iteratorManager: ActorRef,
                    iteratorIndex:Int) extends Actor with ActorLogging {

  private var events = new ListBuffer[StructuredEvent]
  private var stillWaitAppending=true
  override def preStart(): Unit = {
    self ! RequestToPollDate
  }

  override def receive: Receive = {
    case RequestToPollDate => {
      val iteratorName=self.path.name
      log.info(s"start polling for $iteratorName")
      if(dataPoller.startPoll(self,iteratorIndex)){
        self ! RequestToPollDate
      }
    }
    case request_next_date(howMany) => {
      val eventArray=popUp(howMany)
      val hasNext=hasMoreDataToTaken()
      sender() ! RespondNextDate(hasNext,eventArray)
    }
    case RequestDestroyIterator => {
      cleanUpAndAskForDestory
    }
    case appended_flexmapped_data(hasNext,newAppendedData)=>{
      events++=newAppendedData
      stillWaitAppending=hasNext
    }
    case Status.Failure(reason)=>{
      log.error("actor failed with flexmapping:"+reason)
      this.stillWaitAppending=false
    }
  }



  def cleanUpAndAskForDestory: Unit = {
    this.stillWaitAppending = false
    this.events.clear()
    iteratorManager ! request_iterator_manager_to_destroy(self)
  }

  def popUp(howMany: Short)= {
    val result = events.splitAt(howMany)
    events = result._2
    result._1.toArray
  }

  def hasMoreDataToTaken():Boolean={
    events.size!=0 || stillWaitAppending
  }


}
