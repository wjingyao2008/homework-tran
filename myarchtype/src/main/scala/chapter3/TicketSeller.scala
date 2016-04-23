package chapter3

import akka.actor.{Actor, PoisonPill}
import chapter3.MessageProtelcol._
/**
  * Created by y28yang on 1/28/2016.
  */
class TicketSeller extends Actor{

  var ticketList=Vector[Ticket]()
  override def receive = {
    case GetEventCounts => sender() ! ticketList.size

    case TicketsPacForOneMovie(newTicket)=> ticketList=ticketList++newTicket

    case BuyTicket => {
      if (ticketList.isEmpty){
        sender() ! SoldOut
        self ! PoisonPill
      }

      ticketList.headOption.foreach { headTicket =>
        ticketList = ticketList.tail
        this.sender() ! headTicket
      }
    }


  }
}
