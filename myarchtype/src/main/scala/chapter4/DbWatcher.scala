package chapter4

import akka.actor.Actor.Receive
import akka.actor.{Terminated, ActorLogging, Actor, ActorRef}

/**
  * Created by y28yang on 1/29/2016.
  */
class DbWatcher(dbWriter:ActorRef) extends Actor with ActorLogging{
  override def receive = {
    case Terminated(actorRef) =>{
      log.warning(s"actor $actorRef was terminated")
    }
  }
}
