package tool

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}
import tool.StopSystemAfterAll

/**
  * Created by y28yang on 1/29/2016.
  */
class combinTestKitAndSpec extends TestKit(ActorSystem("testSystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {

}
