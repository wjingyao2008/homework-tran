package chapter3

import akka.actor.Actor

/**
  * Created by y28yang on 1/29/2016.
  */
class SilentActor extends Actor{
  import SilentActorProtocol._
  var internalState=Vector[String]()

  def receive={
    case SilentMessage(date) => {
      print(date)
      internalState=internalState :+date
    }
    case GetState(receiver)=> receiver ! internalState

  }

  def state = internalState
}
