package yang.common

import akka.actor.SupervisorStrategy.{Resume, Escalate}
import akka.actor.{ActorLogging, OneForOneStrategy, Actor}
import com.nsn.oss.nbi.corba.ManagedGenericIRPConstDefs.Method
import com.nsn.oss.nbi.corba.ManagedGenericIRPSystem.InvalidParameter
import com.nsn.oss.nbi.{IRPInfo, IRPInfoServiceInstance}
import yang.Protocol.AlarmOptPtl._

import scala.collection.JavaConversions._
/**
  * Created by y28yang on 1/31/2016.
  */
class VersionProfilesInfoActor(infoService: IRPInfoServiceInstance) extends Actor with  ActorLogging with FailurePropatingActor{

  private val ALARM_IRP_ID = "AlarmIRP"
  private val BCM_IRP_ID = "BasicCMIRP"
  private val KCM_IRP_ID = "KernelCMIRP"
  private val FT_IRP_ID = "FTIRP"
  private val EP_IRP_ID = "EPIRP"
  private val CS_IRP_ID = "CSIRP"
  private val NOTIFICATION_IRP_ID = "NotificationIRP"


  override def receive = {
    case `get_alarm_IRP_versions_msg` => {
      val infoSet= infoService.getIRPInfoById(ALARM_IRP_ID).getVersions
      val infoArray=infoSet.toArray(new Array[String](infoSet.size()))
      sender ! infoArray
    }
    case get_alarm_IRP_operations_profile_msg(version) =>{
       val profile= getAlarmOperationsProfile(version)
       sender ! profile
    }

  }

  def getAlarmOperationsProfile(version:String):Array[Method]={
    val irpInfo: IRPInfo = infoService.getIRPInfoById(ALARM_IRP_ID)
    if(irpInfo.getVersions.contains(version)){
     val result= irpInfo.getOperations.map(opt=>{
        val size=opt.getParameters.size()
        val arrayParas=opt.getParameters.toArray(new Array[String](size))
        new Method(opt.getName,arrayParas)
      })
     result.toArray
    }
    else {
      val expectVersions=irpInfo.getVersions.mkString("'",",","'")
      throw new InvalidParameter(s"Unsupported Alarm IRP version '$version'. Supported version is $expectVersions")
    }
  }

}
