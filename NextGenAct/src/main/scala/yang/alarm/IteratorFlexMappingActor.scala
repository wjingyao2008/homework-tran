package yang.alarm

import akka.actor.{ActorRef, ActorLogging, Actor}
import akka.actor.Actor.Receive
import yang.Protocol.AlarmOptPtl.request_get_alarm_list_combined

/**
  * Created by y28yang on 2/16/2016.
  */
class IteratorFlexMappingActor(getAlarmListFromFmActor:ActorRef) extends Actor with ActorLogging{
  override def receive: Receive = {
    case msg:request_get_alarm_list_combined=>{
       this.log.info("i skip the iterator validate ,please TODO")
        //FlexibleMappingEndpoint
       getAlarmListFromFmActor forward ActorRef
    }
  }
}
