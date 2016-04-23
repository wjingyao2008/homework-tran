package yang.alarm

import akka.actor.ActorRef
import org.apache.log4j.Logger
import yang.iterator.DataPoller
import yang.iterator.IteratorProtocol.polled_data_to_flexmapping

/**
  * Created by y28yang on 3/2/2016.
  */
class AlarmFmPoller(flexMappingActor: ActorRef, fmService: AlarmFmServiceInterface,
                    fetchSize: Int) extends DataPoller {
  private val log = Logger.getLogger(classOf[AlarmFmPoller])

  override def startPoll(iteratorRef: ActorRef, iteratorIndex: Int): Boolean = {
    val alarmList = fmService.getNext(iteratorIndex, fetchSize)
    val hasFetchedAll = alarmList.size() < fetchSize
    log.info(s"retrieved alarm ${alarmList.size()},has fetched all: $hasFetchedAll")
    flexMappingActor.tell(new polled_data_to_flexmapping(hasFetchedAll, alarmList, iteratorIndex), iteratorRef)
    hasFetchedAll
  }
}
