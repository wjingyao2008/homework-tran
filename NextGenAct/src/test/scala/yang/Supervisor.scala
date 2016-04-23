package yang

import akka.actor.Status.Failure
import akka.actor.SupervisorStrategy.Restart
import akka.actor._
import com.typesafe.config.ConfigFactory
import yang.common.FailurePropatingActor

/**
  * Created by y28yang on 2/14/2016.
  */
class Supervisor(props: Props) extends Actor {

  import akka.actor.OneForOneStrategy
  import akka.actor.SupervisorStrategy._
  import scala.concurrent.duration._

  val child = context.actorOf(props, "child")

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 1, withinTimeRange = 1 minute) {

      case exp: Exception => {
        println("strategy used!:" + sender().path.toString)
        sender ! akka.actor.Status.Failure(exp)
        Resume
      }
    }

  def receive = {
    case p: Object => child forward p
  }
}
case class CreateChild(props:Props, childName:String)
class SupervisorTestActor extends Actor {
  override val supervisorStrategy =
  //OneForOneStrategy(maxNrOfRetries = 1, withinTimeRange = 1 minute) {
    OneForOneStrategy() {
      case _: Exception => {
        println("has been apply startegy")
        Restart
      }
    }
  var childActorName="child"
  override def receive = {
    case p: Props => sender() ! context.actorOf(p,childActorName)
    case CreateChild(props,childName)=>{
      sender() ! context.actorOf(props,childName)
    }
  }
}

class Child extends FailurePropatingActor {

  override def receive = {
    case p: Int => throw new RuntimeException("Child")
    case s: String => sender() ! s
  }
}


class FaultHandlingDocSpec(s:String) extends TestKitAndFlatSpec(logLevel = s) {
  def this()=this("DEBUG")
  "A supervisor" must "apply the chosen strategy for its child" in {
    val supervisor = system.actorOf(Props[SupervisorTestActor], "supervisor")
    supervisor ! CreateChild(Props[Child],"child1")

    val child = expectMsgType[ActorRef]
    child ! "123"
    expectMsg("123")
    println("123")
    child ! 1

    expectMsgPF() {
      case Failure(cause: RuntimeException) => {
        println(cause.getMessage)
        assert(true)
      }
      case x => {
        println(x)
        fail()
      }
    }
  }
}
