package yang.corba;

import com.nsn.oss.nbi.NamingServiceRegister;
import com.nsn.oss.nbi.ProxyUtil;
import com.nsn.oss.nbi.ServantStarter;

import org.apache.log4j.Logger;
import org.omg.PortableServer.Servant;

public class AlarmIRPStarter extends ServantStarter {

    private final static Logger LOGGER = Logger.getLogger(com.nsn.oss.nbi.AlarmIRPStarter.class);

    private Servant servant;
    private String port;

    public AlarmIRPStarter(Servant servant,String portStr) {
        this.servant = servant;
        port=portStr;
    }

    @Override
    public void publish(org.omg.CORBA.Object object) throws Exception {

        LOGGER.info("Alarm IRP :"+object);
    }

    @Override
    public Servant getServant() {
        return servant;
    }

    @Override
    protected String getPOAName() {
        return "AlarmIRPCorbaPort";
    }

    @Override
    protected String getServantName() {
        return "{http://AlarmIRPSystem.AlarmIRP}AlarmIRP";
    }

    @Override
    protected String getPort() {
        //return ProxyUtil.getPortByInstanceIdandKey(ProxyUtil.getProxyInstanceId(), this.getClass().getName());
        return port;
    }
}
