package chapter3

import akka.actor.{ActorRef, Actor}
import akka.actor.Actor.Receive
import FilteringActorProtocol._
/**
  * Created by y28yang on 1/29/2016.
  */
class FilteringActor(nextActor:ActorRef,bufferSize:Int) extends Actor{
  var uniqMessageQueue=Vector[Event]()
  override def receive = {
    case msg:Event =>
      if( !uniqMessageQueue.contains(msg)){
        uniqMessageQueue=uniqMessageQueue:+msg
        nextActor ! msg
        if (uniqMessageQueue.size>bufferSize){
          uniqMessageQueue=uniqMessageQueue.tail
        }
      }
  }
}
