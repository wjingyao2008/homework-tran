package yang.common

import com.nsn.oss.nbi.common.Logger
import com.nsn.oss.nbi.corba.ManagedGenericIRPSystem.InvalidParameter
import com.nsn.oss.nbi.proxyfilter.ProxyFilterManager
import com.nsn.oss.nbi.proxyfilter.exception.{InvalidProxyFilterFileException, FilterOutOfLengthException}

/**
  * Created by y28yang on 2/16/2016.
  */
trait ProxyFilterManagerInterface {

    def concateFilter(nmsFiler:String,proxyId:String):String

}

class ProxyFilterManagerImpl extends ProxyFilterManagerInterface{
  val LOGGER = Logger.getLogger(classOf[ProxyFilterManagerImpl]);

   def getProxyFilterByProxyInstanceId(proxyId: String): String = {

    try {
      //Common-filterMonitor has build-in mechanism to periodically check filter file status which we can use.If the file status is invalid,stop the process with user friendly exception.
       ProxyFilterManager.getProxyFilterStringByInstanceId(proxyId);
    } catch {
      case e:FilterOutOfLengthException=> {
        LOGGER.error("getProxyFilterStringByProxyInstanceId":Any, e:Throwable);
        throw new InvalidParameter(ProxyFilterManager.PROXY_OUT_OF_LENGTH_EXCEPTION_REASON);
      } case ex:InvalidProxyFilterFileException=> {
        LOGGER.error("getProxyFilterStringByProxyInstanceId":Any, ex:Throwable);
        throw new InvalidParameter(ProxyFilterManager.INVALID_FILTER_FILE_EXCEPTION_MSG);
      }
    }
  }

  def checkNmsFilterLength(nmsFilter:String)={
    if(ProxyFilterManager.isOutOfLength(nmsFilter)){
      throw new InvalidParameter(ProxyFilterManager.NMS_OUT_OF_LENGTH_EXCEPTION_REASON)
    }

  }

  override def concateFilter(nmsFiler: String, proxyId: String): String = {
    val proxyFilter=this.getProxyFilterByProxyInstanceId(proxyId)
    this.checkNmsFilterLength(nmsFiler)
    val nmsAndProxyFilter = ProxyFilterManager.concatFilter(nmsFiler, proxyFilter)
    this.LOGGER.trace(s"concate filter $nmsAndProxyFilter")
    nmsAndProxyFilter
  }
}
