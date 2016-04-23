package chapter3

import akka.actor.{ActorLogging, Actor}
import akka.event.Logging
import akka.event.LoggingReceive
/**
  * Created by y28yang on 1/28/2016.
  */
class GreetingLogActor extends Actor with ActorLogging {

  override def receive = LoggingReceive {
    case input:String => log.info("receive "+input)
    case _ => log.info("receive unknow messsage")
  }
}
