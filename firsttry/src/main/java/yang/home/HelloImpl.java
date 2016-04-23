package yang.home;

import org.omg.CORBA.ORB;

import yang.home.HelloApp.HelloPOA;

/**
 * Created by y28yang on 1/29/2016.
 */
class HelloImpl extends HelloPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    // implement sayHello() method
    public String sayHello() {
        return "\nHello world !!\n";
    }

    // implement shutdown() method
    public void shutdown() {
        orb.shutdown(true);
    }
}
