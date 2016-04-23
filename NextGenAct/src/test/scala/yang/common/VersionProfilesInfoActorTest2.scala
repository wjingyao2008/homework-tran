package yang.common

import akka.actor.Status.Failure
import akka.actor._
import akka.util.Timeout
import com.nsn.oss.nbi.corba.ManagedGenericIRPConstDefs.Method
import com.nsn.oss.nbi.corba.ManagedGenericIRPSystem.InvalidParameter
import com.nsn.oss.nbi.{IRPInfo, IRPInfoServiceInstance, Operation}
import org.mockito.Mockito
import org.mockito.Mockito._
import yang.Protocol.AlarmOptPtl.get_alarm_IRP_operations_profile_msg
import yang.SupervisorTestActor
import yang.TestKitAndFunSuite

import scala.concurrent.duration._

/**
  * Created by y28yang on 1/31/2016.
  */

class VersionProfilesInfoActorTest2 extends TestKitAndFunSuite {
    test("when receive none version profile should throw exception" ){
      val infoservice = Mockito.mock(classOf[IRPInfoServiceInstance])
      when(infoservice.getIRPInfoById("AlarmIRP")).thenReturn(new IRPInfo("id", "idInNs"))
      val supervisor = system.actorOf(Props[SupervisorTestActor], "supervisor")

      supervisor ! Props(new VersionProfilesInfoActor(infoservice))

      val profileChild = expectMsgType[ActorRef]
      profileChild ! get_alarm_IRP_operations_profile_msg("v3")
      expectMsgPF() {
        case Failure(cause: InvalidParameter) => {
          assert(true)
          println("234")
        }
        case x => {
          fail()
        }
      }
    }
//}
//class VersionProfilesInfoActorTest3 extends TestKitAndFunSuite {
  test("when receive getOperationProfile should return array[Mehod]") {
      val infoservice = Mockito.mock(classOf[IRPInfoServiceInstance])
      val irpinfo = new IRPInfo("id", "idInNs")
      val operation = new Operation("get_version")
      operation.getParameters.add("p1")
      val operation2 = new Operation("get_profile")
      operation2.getParameters.add("p2");
      operation2.getParameters.add("p3");

      irpinfo.getVersions.add("v3")
      irpinfo.getOperations.add(operation)
      irpinfo.getOperations.add(operation2)
      when(infoservice.getIRPInfoById("AlarmIRP")).thenReturn(irpinfo)

      implicit val timeout = Timeout(5 seconds)
      val versionProfileInfoActor = system.actorOf(Props(new VersionProfilesInfoActor(infoservice)))
      versionProfileInfoActor.tell(get_alarm_IRP_operations_profile_msg("v3"), testActor)
      expectMsgPF() {
        case retArray: Array[Method] => {
          retArray.size should be(2)
          retArray(0).name should be("get_version")
          retArray(0).parameter_list.size should be(1)
          retArray(0).parameter_list should be(Array("p1"))
          retArray(1).name shouldBe "get_profile"
          retArray(1).parameter_list shouldBe (Array("p2", "p3"))
          println("123")
        }
        case _ => {
          fail()
        }
      }
    }
}

