package chapter3

import akka.actor.ActorRef

/**
  * Created by y28yang on 1/29/2016.
  */
object SilentActorProtocol {
  case class SilentMessage(data: String)
  case class GetState(receiver: ActorRef)
}
