package chapter4

import akka.actor.SupervisorStrategy.Resume
import akka.actor.{OneForOneStrategy, SupervisorStrategy, Actor, Props}

/**
  * Created by y28yang on 1/29/2016.
  */
class LogProcSupervisor(dbSupervisorProps:Props) extends Actor{

      //if file corrupted,keep deal the next msg
  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy(){
    case _:CorruptedFileException=>Resume
  }
    //by dbSupervisorProps, create db supervisor
  val dbSupervisor=context.actorOf(dbSupervisorProps)
  //this supervisor monitoer the logProcessor
  val logProcProps=Props(new LogProcessor(dbSupervisor))
  val logProcessor=context.actorOf(logProcProps)

  def receive={
    case m=> logProcessor forward(m)
  }

}
