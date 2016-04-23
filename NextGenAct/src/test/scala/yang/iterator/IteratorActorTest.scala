package yang.iterator

import akka.actor.{ActorRef, Props}
import akka.testkit.TestActorRef
import org.omg.CosNotification.StructuredEvent
import yang.TestKitAndFunSuite
import yang.iterator.IteratorActorTest.DonothingPoller
import yang.iterator.IteratorProtocol._

object IteratorActorTest{
  class DonothingPoller extends DataPoller{
    override def startPoll(iteratorRef:ActorRef, iteratorId: Int): Boolean = {
      false
    }
  }
}
/**
  * Created by y28yang on 3/1/2016.
  */
class IteratorActorTest extends TestKitAndFunSuite {

  test("test auto poll") {
    val dataPoller = new mockDataPoller(testActor)

    val iteratorActorRef = system.actorOf(Props(new IteratorActor(dataPoller, testActor,1)),"1")
    Thread.sleep(500)
    dataPoller.pollCount shouldBe 4
  }

  test("actor test for take more than it has") {
    val dataPoller = new DonothingPoller

    val iteratorActorRef:TestActorRef[IteratorActor] = TestActorRef(Props(new IteratorActor(dataPoller, testActor,1)))
    val underlying=iteratorActorRef.underlyingActor
    underlying.hasMoreDataToTaken() shouldBe true
    iteratorActorRef ! request_next_date(3)
    expectMsgPF() {
      case RespondNextDate(hasNext,eventArray)=> {
        hasNext shouldBe true
        eventArray.size shouldBe 0
      }
      case any=>{fail(any.toString)}
    }
    val s1=new StructuredEvent()
    iteratorActorRef ! appended_flexmapped_data(false,Array(s1))
    iteratorActorRef ! request_next_date(3)
    expectMsgPF() {
      case RespondNextDate(hasNext,eventArray)=> {
        hasNext shouldBe false
        eventArray.size shouldBe 1
        eventArray(0) shouldBe s1
      }
      case any=>{fail(any.toString)}
    }

  }


  test("test for get next") {
    val dataPoller2 = new DonothingPoller
    val iteratorActorRef = system.actorOf(Props(new IteratorActor(dataPoller2, testActor,0)))
    val s1=new StructuredEvent()
    val s2=new StructuredEvent()
    val s3=new StructuredEvent()
    iteratorActorRef !  appended_flexmapped_data(true,Array(s1))
    iteratorActorRef !  appended_flexmapped_data(true,Array(s2))
    iteratorActorRef !  appended_flexmapped_data(false,Array(s3))
    expect(iteratorActorRef,s1,true)
    expect(iteratorActorRef,s2,true)
    expect(iteratorActorRef,s3,false)
  }

  test("test for get next in turn") {
    val dataPoller2 = new DonothingPoller
    val iteratorActorRef = system.actorOf(Props(new IteratorActor(dataPoller2, testActor,1)))
    val s1=new StructuredEvent()
    val s2=new StructuredEvent()
    val s3=new StructuredEvent()
    val s4=new StructuredEvent()
    iteratorActorRef !  appended_flexmapped_data(true,Array(s1))
    expect(iteratorActorRef,s1,true)
    iteratorActorRef !  appended_flexmapped_data(true,Array(s2))
    iteratorActorRef !  appended_flexmapped_data(true,Array(s3))

    expect(iteratorActorRef,s2,true)
    expect(iteratorActorRef,s3,true)
    iteratorActorRef !  appended_flexmapped_data(false,Array(s4))
    expect(iteratorActorRef,s4,false)
  }


  def expect(iteratorActorRef:ActorRef,event:StructuredEvent,expectHasNext:Boolean)={
    iteratorActorRef ! request_next_date(1)
    expectMsgPF() {
      case RespondNextDate(hasNext,eventArray)=> {
        hasNext shouldBe expectHasNext
        eventArray(0) shouldBe event
        println("assert ok")
      }
        case msg:Any=>{fail(msg.toString)}

    }
  }

  class mockDataPoller(testRef: ActorRef) extends DataPoller {
    val events = new scala.collection.mutable.Queue[StructuredEvent]()
    val s1=new StructuredEvent()
    val s2=new StructuredEvent()
    val s3=new StructuredEvent()

    events += s1
    events += s2
    events += s3
    @volatile
    var pollCount=0
    println(events.mkString(","))
    override def startPoll(iteratorRef:ActorRef, iteratorId: Int):Boolean= {
      println(s"poll $pollCount,event size: ${events.size}")
      pollCount+=1
      val hasNext=events.size > 0
      if(hasNext) events.dequeue
      hasNext
    }
  }

}

