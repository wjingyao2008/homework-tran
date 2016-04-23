package yang

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest._

/**
  * Created by y28yang on 1/30/2016.
  */
class TestKitAndFunSuite(actorSystem: ActorSystem) extends TestKit(_system = actorSystem)
  with FunSuiteLike
  with Matchers
  with ImplicitSender
  with BeforeAndAfterAll {
  //when mixin,then this will shutdown all

  override protected def afterAll(): Unit = {
    super.afterAll()
    println("shutdown correctly")
    system.shutdown()
  }

  def this(logLevel: String) = this(ActorSystem("testSystem",
    ConfigFactory.parseString(
      s"""
      akka {
        loggers = ["akka.testkit.TestEventListener"]
        loglevel = "$logLevel"
                                actor {
                                   debug {
                                      receive = on
                                      autoreceive = on
                                      lifecycle = on
                                    }
                                  info {
                                              receive = on
                                               autoreceive = on
                                              lifecycle = on
                                      }
                                  }
      }
      """)))

  def this() = this("DEBUG")
}



