package chapter3

import akka.actor.{Actor, Props}
import MessageProtelcol._

/**
  * Created by y28yang on 1/28/2016.
  */
class BoxOffice extends Actor {
  override def receive = {

    case newMovie: MovieShow => {
      if (context.child(newMovie.movieName).isEmpty) {
        val ticketSeller = context.actorOf(Props[TicketSeller])
        val ticketsPac = TicketsPacForOneMovie((1 to newMovie.nbrOfTickets).map {
          nbr => Ticket(newMovie.movieName,nbr)
        }.toList)

        ticketSeller ! ticketsPac
        sender() ! TicketsReadyToSell
      }
    }
    case TicketRequest(movieName) =>{
      context.child(movieName) match {
        case Some(ticketSeller) => ticketSeller.forward(BuyTicket)
        case None =>sender ! SoldOut
      }
    }
  }
}
