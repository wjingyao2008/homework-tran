package yang.iterator

import akka.actor.ActorRef

/**
  * Created by y28yang on 3/2/2016.
  */
trait DataPoller {
  def startPoll(iteratorRef:ActorRef, iteratorId: Int):Boolean


//  def startPoll(nextActorSendTo:ActorRef) = {
//    val alarmList = fmService.getNext(iteratorId, fetchSize)
//    val hasFetchedAll = alarmList.size() < fetchSize
//    log.info(s"retrieved alarm ${alarmList.size()},has fetched all: $hasFetchedAll")
//    flexMappingActor ! new polled_data_to_flexmapping(hasFetchedAll,alarmList,iteratorId)
//  }
}
