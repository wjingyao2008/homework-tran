package chapter4

import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.Restart
import akka.actor.{OneForOneStrategy, SupervisorStrategy, Props, Actor}

/**
  * Created by y28yang on 1/29/2016.
  */
class DbSupervisor(dbWriteProps:Props) extends Actor{


  override def supervisorStrategy = OneForOneStrategy(){
    case  _:DbBrokenConnectionException => Restart
  }

  val writer=context.actorOf(dbWriteProps)

  override def receive = {
    case msg=>writer forward(msg)
  }
}
