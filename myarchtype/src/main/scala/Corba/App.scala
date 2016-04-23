package Corba

import org.omg.CORBA.ORB
import org.omg.CosNaming.NamingContextPackage.NotFound
import org.omg.CosNaming.NamingContextPackage.InvalidName
import org.omg.CosNaming.{NamingContextExtHelper, NamingContextExt}

/**
  * Created by y28yang on 1/29/2016.
  */
object App {

  def PrintHelp: Unit ={
     println("help")
  }


    def main(args: Array[String]) {
//      if (args.length == 0) {
//        PrintHelp
//        System.exit(0)
//      }
//      val name: String = args(0)
//      if (name == "-?") {
//        PrintHelp
//        System.exit(0)
//      }
      val name="-d"
      System.setProperty("org.omg.CORBA.ORBClass", "Corba.ObjectImpl")
//      System.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton")
    //  System.setProperty("jacorb.home", ".")
      val orb = org.omg.CORBA.ORB.init(args, null)
//      if (name == "-l") {
//        new ContextLister(orb).list(System.out)
//        return
//      }
      var namingContext: NamingContextExt = null
      try {
        val ns = orb.resolve_initial_references("NameService")
        namingContext = NamingContextExtHelper.narrow(ns)
      }
      catch {
        case e: InvalidName => {
          e.printStackTrace
        }
      }
      if (name == "-d") {
        if (args.length == 2) {
          val deleteName: String = args(1)
          try {
            namingContext.unbind(namingContext.to_name(deleteName))
          }
          catch {
            case e: NotFound => {
              System.out.println("Not Found in Naming Service")
            }
            case e: Exception => {
              e.printStackTrace
            }
          }
        }
        else {
          println("fail")
          System.exit(0)
        }
        return
      }
      try {
        val ior: AnyRef = namingContext.resolve_str(name)
        System.out.println(ior)
      }
      catch {
        case e: NotFound => {
          System.out.println("Not Found in Naming Service")
        }
        case e: Exception => {
          e.printStackTrace
        }
      }
    }

}
