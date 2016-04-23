package yang

import java.util

import akka.actor.Actor.Receive
import akka.actor._
import akka.testkit.TestActorRef
import com.nsn.oss.nbi.{IRPInfo, IRPInfoServiceInstance}
import com.nsn.oss.nbi.corba.AlarmIRPSystem.GetAlarmIRPVersions
import org.mockito.Mockito
import org.scalatest.{FunSuiteLike, FunSuite}
import org.mockito.Mockito._
import org.mockito.Mockito.mock
import org.mockito.Matchers._
import yang.common.VersionProfilesInfoActor

/**
  * Created by y28yang on 1/30/2016.
  */
class AlarmOperationImplTest extends TestKitAndFunSuite {

  test("testGet_alarm_IRP_versions") {
      val infoservice = Mockito.mock(classOf[IRPInfoServiceInstance])
      val version = Array("123.0.1", "32.111 V6.2")
      val versionSet = new util.HashSet[String]()
      versionSet.add("123.0.1");
      versionSet.add("32.111 V6.2")
      val info = mock(classOf[IRPInfo])

      when(infoservice.getIRPInfoById(any[String])).thenReturn(info)
      when(info.getVersions()).thenReturn(versionSet)
      val versionProfileInfoActor = system.actorOf(Props(new VersionProfilesInfoActor(infoservice)))
      val actorRef = system.actorOf(Props(new AlarmOperationActor(versionProfileInfoActor, null,null)))
      val alarmOperationImpl = new AlarmOperationImpl(actorRef)

      val result = alarmOperationImpl.get_alarm_IRP_versions()
      result should contain(version(0))
      result should contain(version(1))
      println(actorRef.path)

  }
    test("should timeout when timeout") {
      val actorRef = system.actorOf(Props(new TimeOutActor(10000)))
      val alarmOperationImpl = new AlarmOperationImpl(actorRef, 5)
      val thrown = intercept[GetAlarmIRPVersions] {
        alarmOperationImpl.get_alarm_IRP_versions()
      }
      thrown shouldBe a[GetAlarmIRPVersions]
    }


    test("should handle exception  when process") {
      val actorRef = system.actorOf(Props(new ExceptionHandler))
      val alarmOperationImpl = new AlarmOperationImpl(actorRef, 5)
      val thrown = intercept[GetAlarmIRPVersions] {
        alarmOperationImpl.get_alarm_IRP_versions()
      }
      thrown shouldBe a[GetAlarmIRPVersions]
    }



  class TimeOutActor(timeOut: Long) extends Actor with ActorLogging{
    override def receive = {
      case _ => {
        Thread.sleep(timeOut)
      }
    }

    override def postRestart(reason: Throwable) {
      super.postRestart(reason)
      log.info("Restarted,reason is:"+reason.getMessage)
    }
  }

  class ExceptionHandler extends Actor {
    override def receive = {
      case _ => {
        val exp = new RuntimeException("test")
        sender ! akka.actor.Status.Failure(exp)
        throw exp
      }
    }
  }

}
