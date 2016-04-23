package yang

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by y28yang on 2/17/2016.
  */
trait BlockingAsk {

    def blockAsk(actorTo:ActorRef,msg:Any,timeoutSec:Int)={
      implicit val timeout = Timeout(timeoutSec seconds)
      val futureResult=actorTo ? msg
      Await.result(futureResult,timeout.duration)
    }
}
