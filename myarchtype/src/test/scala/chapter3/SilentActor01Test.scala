package chapter3

import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestActorRef, TestKit}
import org.junit.Test
import org.scalatest.{WordSpecLike, FunSpec, MustMatchers, WordSpec}
import SilentActorProtocol._
import tool.StopSystemAfterAll

/**
  * Created by y28yang on 1/29/2016.
  */

class SilentActor01Test  extends TestKit(ActorSystem("testSystem"))
with WordSpecLike
with MustMatchers
with StopSystemAfterAll
{

  "A silent actor" must {
    "change state when it receive a msg,in single thread" in{
     val silentActor=TestActorRef[SilentActor]
      silentActor ! SilentMessage("whisper")
      silentActor.underlyingActor.state must (contain("whisper"))

      //fail("not implement yet")
    }

   "change state when it receive a msg,in multi thread" in {
     //system is a multi thread impl
     val silentActor=system.actorOf(Props[SilentActor],"multiActor")
     silentActor ! SilentMessage("whisper1")
     silentActor ! SilentMessage("whisper2")
     //multi thread must use this to get
     //testActor is a test location
     silentActor ! GetState(testActor)
     //expect the test location received the returned msg with this msg
     expectMsg(Vector("whisper1","whisper2"))

   }

  }



}
