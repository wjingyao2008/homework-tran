package chapter3

import akka.actor.Props
import akka.testkit.TestActors.EchoActor

/**
  * Created by y28yang on 1/29/2016.
  */
class TwoWayTest extends tool.combinTestKitAndSpec{

  "Reply with same message" must {
   "can receive back" in {
      val echo = system.actorOf(Props[EchoActor], "echo2")
      echo.tell("hi,can you reply?", testActor)
      expectMsg("hi,can you reply?")
    }

  }

}
