package yang.alarm

import akka.actor.{ActorLogging, Actor}
import yang.Protocol.AlarmOptPtl._
import com.nsn.oss.nbi.common.etcl.Engine
import com.nsn.oss.nbi.fm.operation.interfaces.{UserInfo, Filter}

/**
  * Created by y28yang on 1/31/2016.
  */
class AlarmCountActor(alarmOperationService: AlarmFmServiceInterface) extends Actor with ActorLogging {


  override def receive = {
    case request_get_alarm_count(filter) => {
      val filterList = convertFilter(filter)
       val userInfo = new UserInfo;
       userInfo.setFilterId(0:Short)
      val alarmCounts = alarmOperationService.getAllAlarmCounts(filterList, userInfo)
      sender() ! reply_get_alarm_count(alarmCounts.getCriticalCount.toInt,
        alarmCounts.getMajorCount.toInt,
        alarmCounts.getMinorCount.toInt,
        alarmCounts.getWarningCount.toInt,
        alarmCounts.getIndeterminateCount.toInt,
        alarmCounts.getClearedCount.toInt)
    }
  }




  def convertFilter(filter: String) = {
    this.log.info(s"received filter $filter")
    var inputFilters: java.util.List[Filter] = null
    if (filter != null && filter.length() != 0) {
      inputFilters = Engine.convert(filter);
      if (inputFilters.isEmpty()) {
        throw new Exception("Invalid Parameter for filter");
      }
    }
    inputFilters
  }
}
