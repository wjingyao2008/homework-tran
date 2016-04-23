package yang.alarm

import java.util
import java.util.{GregorianCalendar, List}
import javax.xml.datatype.XMLGregorianCalendar

import com.nsn.oss.nbi.fm.operation.{AlarmOperationService, IteratorHolder, ServiceLocator}
import com.nsn.oss.nbi.fm.operation.interfaces._
import org.apache.cxf.jaxb.DatatypeFactory

/**
  * Created by y28yang on 2/3/2016.
  */
trait AlarmFmServiceInterface {
  def getAllAlarmCounts(filters:List[Filter],userInfo: UserInfo):AlarmCounts

  def getAllAlarm(filters:List[Filter],userInfo: UserInfo):Int

  @throws(classOf[IterationFault])
  def getNext(iteratorId: Int, howMany: Int):util.List[NsnAlarm]
}

class AlarmFmServiceImpl extends AlarmFmServiceInterface{
  override def getAllAlarmCounts(filters: util.List[Filter], userInfo: UserInfo): AlarmCounts = {
     ServiceLocator.getAlarmMonitorPort().getAllAlarmCounts(filters, userInfo)
  }

  override def getAllAlarm(filters: util.List[Filter],userInfo: UserInfo): Int = {
    val alarmMonitorSEI=ServiceLocator.getAlarmMonitorPort
    alarmMonitorSEI.getAllAlarms(Integer.MAX_VALUE,null,filters,userInfo)
    IteratorHolder.getInstance.offer(alarmMonitorSEI);
  }

  @throws(classOf[IterationFault])
  override def getNext(iteratorId: Int, howMany: Int): util.List[NsnAlarm] = {
    AlarmOperationService.getInstance().getNext(iteratorId,howMany)
  }
}
