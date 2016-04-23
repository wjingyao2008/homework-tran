package chapter3

/**
  * Created by y28yang on 1/28/2016.
  */
object MessageProtelcol {
 case class MovieShow(movieName:String, nbrOfTickets:Int)
  case object GetEventCounts
  case class EventList(events:List[MovieShow])
  case object TicketsReadyToSell
  case class TicketRequest(event: String)
  case object SoldOut
  case class TicketsPacForOneMovie(ticket:List[Ticket])
  case class Ticket(event:String,number:Int)
  case object BuyTicket


}
