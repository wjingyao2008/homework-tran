package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator

import scala.collection.immutable.HashMap

/**
 * Created by y28yang on 4/25/2016.
 */
class ShortestDistanceSearcher(val startName: String,
                               val stopName: String) extends Searcher {

  var shortestDistance = Integer.MAX_VALUE

  override def search(iterator: RouteIterator): String = {
    while (iterator.hasNext) {
      val route = iterator.next

      if (route.head.toStation.name != startName) {
        iterator.dropCurrentStation
      }
      else if ((route.size > 1)){
        val distance=calcDistance(route)
        if (route.last.toStation.name == stopName) {
          updateShortest(distance)
          iterator.dropCurrentStation
        }else if(distance>=shortestDistance){
          iterator.dropCurrentStation
        }
      }
    }
    shortestDistance.toString
  }


  def updateShortest(distance:Int)= if(distance < shortestDistance) shortestDistance=distance

}
