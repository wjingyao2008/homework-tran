package tool

import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Suite}
/**
  * Created by y28yang on 1/29/2016.
  */
//extend the trait
trait StopSystemAfterAll extends BeforeAndAfterAll{

  //when mixin,then this will shutdown all
  this:TestKit with  Suite =>
   override protected def afterAll(): Unit ={
     super.afterAll()
     system.shutdown()
   }
}
