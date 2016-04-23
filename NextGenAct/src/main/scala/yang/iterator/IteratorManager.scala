package yang.iterator

import java.util.concurrent.TimeUnit

import akka.actor._
import org.omg.CosNotification.StructuredEvent
import org.omg.PortableServer.Servant
import yang.Protocol.AlarmOptPtl.reply_get_alarm_list
import yang.corba.CorbaObjInitiator
import yang.iterator.IteratorProtocol._

import scala.collection.mutable
import scala.concurrent.duration._

/**
  * Created by y28yang on 2/16/2016.
  */
class IteratorManager(corbaObjInitiator: CorbaObjInitiator,
                      dataPoller: DataPoller,
                      iteratorChildName:String="iterator",
                      scheduleIntervalMilSec: Long = 60000,
                      iteratorTimeOutEach:Long=60000) extends Actor with ActorLogging {

  val iteratorMap = new mutable.HashMap[ActorRef, Servant with TimeoutCheckable]

  override def preStart(): Unit = {
    val delay = Duration.create(scheduleIntervalMilSec, TimeUnit.MILLISECONDS)
    val receiver=self
    import scala.concurrent.ExecutionContext.Implicits.global
    context.system.scheduler.schedule(delay, delay, receiver, schedule_to_check_unused_iterator)
  }

  override def receive: Receive = {
    case RequestCreateIteratorIor(iteratorId) => {
      val actorRef = createNewIterator(iteratorId)
      log.info(s"creating ior for iterator:${actorRef.path}")
      val alarmIterator = new AlarmInformationIteratorImpl(actorRef, 5)
      corbaObjInitiator.active(alarmIterator)
      iteratorMap.put(actorRef, alarmIterator)
      sender ! reply_get_alarm_list(true, alarmIterator, new Array[StructuredEvent](0))
    }

    case request_iterator_manager_to_destroy(iterator) => deleteIterator(iterator)


    case `schedule_to_check_unused_iterator` => {
      for ((iterator, value) <- iteratorMap) {
        if (value.isTimeOut(iteratorTimeOutEach)) {
          deleteIterator(iterator)
        }
      }
    }

  }


  def deleteIterator(iterator: ActorRef) = {
    log.info(s"deleting iterator: ${iterator.path}")
    val servant = iteratorMap.get(iterator).get
    corbaObjInitiator.deActive(servant)
    iterator ! PoisonPill
    iteratorMap.remove(iterator)
  }

  def createNewIterator(iteratorIndex: Int): ActorRef = {
    val iteratorName=iteratorChildName+iteratorIndex
    val boxedIndex=Int.box(iteratorIndex)
    val actorRef = context.actorOf(Props.create(classOf[IteratorActor],
      dataPoller,self,boxedIndex),iteratorName)
    actorRef
  }
}
