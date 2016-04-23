package yang

import java.util.concurrent.{Executors, ExecutorService}

import akka.actor.{ActorRef, ActorSystem, Props, Actor}
import akka.actor.Actor.Receive
import akka.util.Timeout
import yang.HappenBefore.{OptCount, Total, opt}
import akka.pattern.ask
import scala.collection.mutable.Queue
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.collection.JavaConversions._

/**
  * Created by y28yang on 2/23/2016.
  * this verify that the akka meets the happen-before rule,
  */
object HappenBefore {

  case class opt(value: Long, addOrDec: Boolean)

  //case class dec(value: Long)
  case object OptCount

  case class Total(sum: Long, count: Long)

  case object GIVE_RESULT

  def main(args: Array[String]) {
    val pro1 = Props[QueueActor]
    val system = ActorSystem("mySystem")
    implicit val timeout = Timeout(10 seconds)

    val rangeActorRefs = (1 to 20).map(index => {
      system.actorOf(pro1, s"myActorInstance_$index")
    })
    val r = new scala.util.Random(System.currentTimeMillis)

    val allRandomRange = (0 to 10000).map(_ => opt(r.nextInt(100), r.nextBoolean()))

    val split = allRandomRange.splitAt(allRandomRange.size / 2)
    val split1 = split._1.splitAt(split._1.size / 2)
    val split2 = split._2.splitAt(split._2.size / 2)
    val array = Array(split1._1, split1._2, split2._1, split2._2)
    //val array=allRandomRange
    val executor = Executors.newCachedThreadPool();
    allRandomRange.map(opt=>{new Runnable {
      override def run(): Unit = {
        rangeActorRefs.map(actor=>{
          actor ! opt
        })
      }
    }})
//    array.map(opts => {
//      new Runnable {
//        override def run(): Unit = {
//          rangeActorRefs.map(actor => {
//            //            actor ! opts
//            opts.foreach(msg => {
//              actor ! msg
//            })
//          })
//        }
//      }
//    })
  .foreach(msg => {
      //     println("submit")
      executor.submit(msg)
    }
    )


    println("start asking....")
    Thread.sleep(10000)

    val results = rangeActorRefs.map(actor => {
      val future = actor ? Total
      val ret = Await.result(future, timeout.duration).asInstanceOf[(Long, Long)]
      println(ret)
      ret._1
    })


    println("result....:" + results.distinct.mkString("."))
    var total:Long = 0
    allRandomRange.foreach(msg=>{
      total+=msg.value
    })
//    allRandomRange.foreach(msg => {
//      val value = msg.value
//      if (msg.addOrDec) total += value
//      else total -= value
//    })
    println(total)
  }

}
class QueueActor extends Actor {

  val queue = new Queue[Long]()

  override def receive: Receive = {

    case opt(value, isAdd) => {
      //print("i get $value")
      queue.enqueue(value)

    }
    case Total => {
      val sumVal=queue.sum
      sender() !(sumVal,queue.size)
    }
    case any => {
      println("get: " + any)
    }
  }
}

class LongActor extends Actor {

  //val queue = new Queue[Long]()
  var sum: Long = 0
  var optCount: Long = 0

  override def receive: Receive = {

    case opt(value, isAdd) => {
      //print("i get $value")
      if (isAdd) sum += value
      else sum -= value
      optCount += 1
    }
    case Total => {
      println(s"get total $sum,$optCount")
      sender() !(sum, optCount)
    }
    case OptCount => {
      sender() ! optCount
    }
    case any => {
      println("get: " + any)
    }
  }
}
