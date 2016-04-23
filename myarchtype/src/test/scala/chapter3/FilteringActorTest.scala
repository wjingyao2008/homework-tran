package chapter3

import FilteringActorProtocol._
import akka.actor.{ActorRef, Props}


class FilteringActorTest extends tool.combinTestKitAndSpec{


    "filter out message " in {
      val props = Props(new FilteringActor(testActor, 5))
      val filter = system.actorOf(props, "filter-1")
      filter ! Event(1)
      filter ! Event(2)
      filter ! Event(3)
      filter ! Event(4)
      filter ! Event(5)
      filter ! Event(4)
      filter ! Event(3)
      filter ! Event(5)
      filter ! Event(6)

      val eventId = receiveWhile() {
        case Event(id) if id <= 5 => id
      }
      eventId must be(List(1, 2, 3, 4, 5))
      expectMsg(Event(6))
    }
}
