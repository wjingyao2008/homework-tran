package yang.alarm

import akka.actor.{ActorRef, Props, ActorLogging, Actor}
import yang.Protocol.AlarmOptPtl.{request_get_alarm_list_combined, request_get_alarm_list}
import yang.common.{DnNameMapper, ProxyFilterManagerInterface}

/**
  * Created by y28yang on 2/16/2016.
  */
class GetAlarmListGetInputParameterProcessor(proxyFilterManager:ProxyFilterManagerInterface,
                                             dnNameManager:DnNameMapper,
                                             iteratorFlexMapping:ActorRef) extends Actor with ActorLogging{



  override def receive: Receive = {
    case request_get_alarm_list(filter,isBaseObjectUsed,baseObject, proxyId)=>{
      val concateFilter=proxyFilterManager.concateFilter(filter,proxyId)

      val resultBaseObject=if(isBaseObjectUsed)dnNameManager.dnNameMap(baseObject) else null

      iteratorFlexMapping forward request_get_alarm_list_combined(concateFilter,resultBaseObject)
    }
  }



}
