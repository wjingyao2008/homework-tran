package yang.alarm

import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, Props, ActorSystem}
import com.nsn.oss.nbi.IteratorStarter
import yang.corba.AlarmIRPStarter
import yang.iterator.IteratorManager
import yang.iterator.IteratorProtocol.RequestCreateIteratorIor
import yang.{AlarmOperationImpl, BlockingAsk, ExecutorPool}
import yang.Protocol.AlarmOptPtl.{reply_get_alarm_list}

/**
  * Created by y28yang on 2/17/2016.
  */
class IteratorIorServiceTest {

}

object IteratorIorServiceTest extends ExecutorPool with BlockingAsk{

  def main(args: Array[String]) {
    val system = ActorSystem("MyActors")
    val iteratorStarter: IteratorStarter = new IteratorStarter
    val flexMappingRef=null
    val actor=system.actorOf(Props(new IteratorManager(null,flexMappingRef)))
    val mockActor=system.actorOf(Props(new mockActor(actor)))

    this.submit(iteratorStarter)
    val alarmOperationImpl=new AlarmOperationImpl(mockActor,5){
      override def get_alarm_IRP_versions(): Array[String] = {
        println("received")
        Thread.sleep(3000000)
        Array[String]("1")
      }
    }
    val alarmirp = new AlarmIRPStarter(alarmOperationImpl,"8300")
    this.submit(alarmirp)
  }
}

class mockActor(actor:ActorRef) extends Actor{
  override def receive: Receive={
    case _=>{
      actor forward RequestCreateIteratorIor(0)
    }
  }
}
