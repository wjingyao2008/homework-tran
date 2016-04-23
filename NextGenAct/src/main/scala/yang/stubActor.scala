package yang

import akka.actor.{ActorLogging, Actor}
import akka.actor.Actor.Receive

/**
  * Created by y28yang on 1/31/2016.
  */
class StubActor extends Actor with ActorLogging{
  override def receive = {
    case any: Any =>{
      log.info("get :"+any)
    }
  }
}
