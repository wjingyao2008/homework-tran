package yang.corba

import org.omg.PortableServer.Servant

/**
  * Created by y28yang on 2/16/2016.
  */
trait CorbaObjInitiator {
  def active(toActive:Servant)
  def deActive(toDeactive:Servant)
}



