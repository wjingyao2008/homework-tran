package chapter4

import java.io.File

import akka.actor.{ActorRef, Actor}
import akka.actor.Actor.Receive

/**
  * Created by y28yang on 1/29/2016.
  */
class LogProcessor(dbSupervisor: ActorRef) extends Actor  {

  import LogProcessingProtocl._

  override def receive = {
    case LogFile(file)=>
      val lines=parse(file)
      lines.foreach(dbSupervisor ! _)
  }

  def parse(file:File)={
    Vector("1","2","3")
  }
}
