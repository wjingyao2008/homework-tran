package yang.home;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExtPOA;
import org.omg.CosNaming.NamingContextExtPackage.InvalidAddress;
import org.omg.CosNaming.NamingContextPOA;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.util.Properties;

import yang.home.HelloApp.Hello;
import yang.home.HelloApp.HelloHelper;

/**
 * Created by y28yang on 1/29/2016.
 */
public class NamingContextImpl extends NamingContextExtPOA {

    public static void main(String[] args) throws org.omg.CORBA.ORBPackage.InvalidName {
        // create and initialize the ORB
        Properties orbProps = new Properties();
        //orbProps.setProperty("ORBInitRef","NameService=corbaname::127.0.0.1:11169");
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
        org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");

        /*try {
        // get reference to rootpoa & activate the POAManager
        POA rootpoa = null;

            rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

        rootpoa.the_POAManager().activate();

        // create servant and register it with the ORB
        HelloImpl helloImpl = new HelloImpl();
        helloImpl.setORB(orb);

        // get object reference from the servant
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
        Hello href = HelloHelper.narrow(ref);
        System.out.println(href.toString());

            orb.run();
        } catch (org.omg.CORBA.ORBPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        }*/
    }

    @Override
    public void bind(NameComponent[] n, Object obj) throws NotFound, CannotProceed, InvalidName, AlreadyBound {

    }

    @Override
    public void bind_context(NameComponent[] n, NamingContext nc)
        throws NotFound, CannotProceed, InvalidName, AlreadyBound {

    }

    @Override
    public void rebind(NameComponent[] n, Object obj) throws NotFound, CannotProceed, InvalidName {

    }

    @Override
    public void rebind_context(NameComponent[] n, NamingContext nc) throws NotFound, CannotProceed, InvalidName {

    }

    @Override
    public Object resolve(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {
        return null;
    }

    @Override
    public void unbind(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {

    }

    @Override
    public void list(int how_many, BindingListHolder bl, BindingIteratorHolder bi) {

    }

    @Override
    public NamingContext new_context() {
        return null;
    }

    @Override
    public NamingContext bind_new_context(NameComponent[] n) throws NotFound, AlreadyBound, CannotProceed, InvalidName {
        return null;
    }

    @Override
    public void destroy() throws NotEmpty {

    }

    @Override
    public String to_string(NameComponent[] n) throws InvalidName {
        return "123";
    }

    @Override
    public NameComponent[] to_name(String sn) throws InvalidName {
        return new NameComponent[0];
    }

    @Override
    public String to_url(String addr, String sn) throws InvalidAddress, InvalidName {
        return null;
    }

    @Override
    public Object resolve_str(String sn) throws NotFound, CannotProceed, InvalidName {
        return null;
    }
}
