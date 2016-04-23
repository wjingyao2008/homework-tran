package chapter3

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}
import chapter3.MessageProtelcol._
import tool.StopSystemAfterAll

/**
  * Created by y28yang on 1/29/2016.
  */
class TicketSellerTest extends TestKit(ActorSystem("testSystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {

  "A sender" must {
    "send a message to an actor when it finish" in {



    }
  }
}
