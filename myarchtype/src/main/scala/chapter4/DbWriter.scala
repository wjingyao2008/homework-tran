package chapter4

import akka.actor.Actor
import akka.actor.Actor.Receive
import chapter4.LogProcessingProtocl.Line

/**
  * Created by y28yang on 1/29/2016.
  */
class DbWriter(connection:DbCon) extends Actor{
  override def receive = {
    case Line(time,message,messageType) =>
      connection.write(Map('time-> time,
        'message->message,
        'messageType->messageType))
  }
}
