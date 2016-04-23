package yang.common

import com.nsn.oss.nbi.common.Logger
import com.nsn.oss.nbi.corba.ManagedGenericIRPSystem.InvalidParameter
import com.nsn.oss.nbi.dnmapping.DNMappingException
import com.nsn.oss.nbi.dnmapping.engine.{DNMappingEngine, DNMappingEngineFactory}

/**
  * Created by y28yang on 2/16/2016.
  */
trait DnNameMapper {

   def dnNameMap(baseObject:String):String
}


object DnNameMapperImpl{
  val dnMapper = DNMappingEngineFactory.create;

   //this creation of object is meant to lazy-initialise dnMapper only when use.
  def mapDnFrom3GppToNSN(baseObject:String):String={
    //TODO val baseMoi=Config.get("com.nsn.oss.nbi.kernelcmirp.baseMoi", "root")
    val baseMoi: String ="root"
    var resultBase=baseObject
    if (baseObject eq baseMoi) {
      resultBase=null
    }
    else{
      try {
        dnMapper.map(baseObject, DNMappingEngine.Direction.FROM_3GPP_TO_NSN, DNMappingEngine.DEFAULT_FM_FRAGMENT_NAME)
      }
      catch {
        case e: DNMappingException => {
          throw new InvalidParameter("base_object")
        }
      }
    }
    resultBase
  }
}
class DnNameMapperImpl extends DnNameMapper{
  val LOGGER = Logger.getLogger(classOf[DnNameMapperImpl]);
  private val BASE_OBJECT_MAX_LEN: Int = 1024


  override def dnNameMap(baseObject: String): String = {
    LOGGER.info(s"baseObject before map: $baseObject")
    if ((baseObject eq null )||baseObject.isEmpty){
      throw new InvalidParameter("base_object cannot be null or empty.")
    }

    if(baseObject.length >BASE_OBJECT_MAX_LEN){
      throw new InvalidParameter("base_object is too long.")
    }
     val resultBase=DnNameMapperImpl.mapDnFrom3GppToNSN(baseObject)
      LOGGER.info("set baseObject:" + resultBase)
      resultBase
  }
}
