package yang.home;

import org.omg.PortableServer.*;
import org.jacorb.naming.*;
import java.util.*;
public class sdf {
    public static void main(String[] args) {
        sdf server = new sdf();
        server.startNameService();
        System.out.println("A : "
                           +String.valueOf(System.currentTimeMillis()));
        try {
            Thread.currentThread().sleep(10000); //10 seconds, waitfor JacORB's Name service!
        }
        catch (InterruptedException ex) {
            return;
        }
        System.out.println("B : "
                           +String.valueOf(System.currentTimeMillis()));
        Properties props = System.getProperties();
        try{
            org.omg.CORBA.ORB orb =
                org.omg.CORBA.ORB.init(args, props);
            org.omg.CORBA.Object obj =
                orb.resolve_initial_references("RootPOA");//###exception here###
            POA rootPOA = POAHelper.narrow(obj);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void startNameService(){
        final String[] s = new String[]{"-p","1077"};
        new Thread(){
            public void run(){
                NameServer.main(s);
            }
        }.start();
    }
}
