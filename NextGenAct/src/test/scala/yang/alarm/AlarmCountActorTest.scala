package yang.alarm

import akka.actor.Props
import com.nsn.oss.nbi.common.etcl.EtclMapping
import com.nsn.oss.nbi.corba.ManagedGenericIRPConstDefs.StringTypeOpt
import com.nsn.oss.nbi.fm.operation.interfaces.{AlarmCounts, Filter, UserInfo}
import org.mockito.Matchers
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.omg.CORBA.IntHolder
import yang.{AlarmOperationActor, AlarmOperationImpl, TestKitAndFunSuite}
/**
  * Created by y28yang on 2/3/2016.
  */
class AlarmCountActorTest extends TestKitAndFunSuite with mockAlarmOperationService {

//  override def beforeAll(): Unit = {
//    //throw new RuntimeException("123")
//    println("123")
//    val mapping: EtclMapping = EtclMapping.instance
////    mapping.readCfg("/example.xml")
//  }
//
//  test("testAroundPostStop filter should ok") {
//    val alarCount = new AlarmCounts
//    alarCount.setCriticalCount(1: Long)
//    alarCount.setMajorCount(2: Long)
//    alarCount.setMinorCount(3: Long)
//    alarCount.setWarningCount(4: Long)
//    alarCount.setIndeterminateCount(5: Long)
//    alarCount.setClearedCount(6: Long)
//    when(mockAlarmOperationService.getAllAlarmCounts(Matchers.anyListOf(classOf[Filter]), any[UserInfo]))
//      .thenReturn(alarCount)
//    val alarmCountActor = system.actorOf(Props(new AlarmCountActor(this.mockAlarmOperationService)))
//    val actorRef = system.actorOf(Props(new AlarmOperationActor(null, alarmCountActor,null)))
//    val alarmOperationImpl = new AlarmOperationImpl(actorRef)
//    val filter = new StringTypeOpt
//    val intHodler1 = new IntHolder
//    val intHodler2 = new IntHolder
//    val intHodler3 = new IntHolder
//    val intHodler4 = new IntHolder
//    val intHodler5 = new IntHolder
//    val intHodler6 = new IntHolder
//
//    filter.value("a < 1 and $c.d == 2")
//    val result = alarmOperationImpl.get_alarm_count(filter, intHodler1, intHodler2, intHodler3, intHodler4, intHodler5, intHodler6)
//
//    intHodler1.value shouldBe 1
//    intHodler2.value shouldBe 2
//    intHodler3.value shouldBe 3
//    intHodler4.value shouldBe 4
//    intHodler5.value shouldBe 5
//    intHodler6.value shouldBe 6
//  }


}
