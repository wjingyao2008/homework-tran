package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator
import mavenscala.search.searcher.controller.Controller

/**
  * Created by y28yang on 4/25/2016.
  */
class ControlledSearcher(val startName: String,
                         val stopName: String,
                         controller: Controller) extends Searcher {


  def isCurrentRouteNeeded(route: Seq[Edge]): Boolean = {
    val isSameWithLast = route.last.toStation.name == stopName
    return isSameWithLast && controller.isNeeded(route)
  }


  override def search(iterator: RouteIterator): String = {
    var numberOfRoute=0
    while (iterator.hasNext) {
      val route = iterator.next

      if (route.head.toStation.name != startName) {
        iterator.dropCurrentStation
      }
      else if (isCurrentRouteNeeded(route)) {
        numberOfRoute+=1
      }
      else if (controller.needDrop(route)) {
        iterator.dropCurrentStation
      }
    }
    numberOfRoute.toString
  }
}
