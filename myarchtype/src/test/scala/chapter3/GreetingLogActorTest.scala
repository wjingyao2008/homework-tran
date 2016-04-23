package chapter3

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestKit, EventFilter, CallingThreadDispatcher}
import com.typesafe.config.ConfigFactory
import org.scalatest.{MustMatchers, WordSpecLike}
import tool.{StopSystemAfterAll, combinTestKitAndSpec}
import chapter3.GreetingLogActorTest._
/**
  * Created by y28yang on 1/29/2016.
  */
class GreetingLogActorTest extends TestKit(testSystem)
with WordSpecLike
with MustMatchers
with StopSystemAfterAll
{

//  "a log actor" must {
//    "when print log,if can be verify if using ActorLogging" in {
//      val dispatchId = CallingThreadDispatcher.Id
//      //make sure single thread
//      val props = Props[GreetingLogActor].withDispatcher(dispatchId)
//      val greeter = system.actorOf(props)
//      //intercept
//      EventFilter.info(message = "receive hi", occurrences = 1).intercept {
//        greeter ! "hi"
//      }
//
//    }
//  }
}
  object GreetingLogActorTest {
    val testSystem = {
      val config = ConfigFactory.parseString(
        """akka.event-handlers = ["akka.testkit.TestEventListener"]""")
      ActorSystem("testsystem", config)
    }
  }

