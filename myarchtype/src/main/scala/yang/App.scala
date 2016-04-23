package yang

import akka.actor.{ActorSystem, Props}
import chapter3.GreetingLogActor

/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    println("concat arguments = " + foo(args))
    val pro1=Props[GreetingLogActor]
    val system=ActorSystem("mySystem")
    //System.out.println(system.settings);
    val myActor=system.actorOf(pro1,"myActorInstance")
    myActor ! "test"
    myActor ! "mainApp"

    //myActor ! "test111"

  }

}
