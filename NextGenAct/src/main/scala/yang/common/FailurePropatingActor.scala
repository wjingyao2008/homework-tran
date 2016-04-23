package yang.common

import akka.actor.{Actor, Status}

/**
  * Created by y28yang on 2/15/2016.
  */
trait FailurePropatingActor extends Actor{
  override def preRestart(reason:Throwable, message:Option[Any]){
    super.preRestart(reason, message)
    sender() ! Status.Failure(reason)
  }
}
