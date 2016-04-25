package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator

/**
  * Created by y28yang on 4/25/2016.
  */
class MaximumStepsSearcher(val startName: String,
                           val stopName: String,
                           maxStops: Int) extends Searcher {
  val maxRouteNbr=maxStops+1

  def isSameInMaximumSteps(route: Seq[Edge]): Boolean = {
    val size=route.size
     return (size<=maxRouteNbr && size>1 &&
       route.last.toStation.name==stopName)
  }

  override def search(iterator: RouteIterator): String = {
    var numberOfRoute=0
    while (iterator.hasNext) {
      val route = iterator.next

      if(route.head.toStation.name!=startName){
        val name=route.head.toStation.name
        iterator.dropCurrentStation
      }
      else if(isSameInMaximumSteps(route)){
        numberOfRoute+=1
      }
      else if(route.length > maxRouteNbr){
        iterator.dropCurrentStation
      }

    }
    numberOfRoute.toString
  }
}
