package yang

import akka.actor.{ActorRef, Actor}
import akka.actor.Actor.Receive
import yang.Protocol.AlarmOptPtl._

/**
  * Created by y28yang on 1/30/2016.
  */
class AlarmOperationActor(versionProfileAct:ActorRef,
                          alarmCountActor:ActorRef,
                          alarmGetListActor:ActorRef) extends Actor{

  override def receive = {
    case `get_alarm_IRP_versions_msg` =>{
       // licenseChecker forward GetAlarmIrpVersions
      versionProfileAct forward  get_alarm_IRP_versions_msg
    }
    case msg:get_alarm_IRP_operations_profile_msg=>{
      versionProfileAct forward msg
    }
    case msg:request_get_alarm_count =>{
      alarmCountActor forward msg
    }
    case msg:request_get_alarm_list =>{
      alarmGetListActor forward msg
    }
    case _=> ???
  }
}
