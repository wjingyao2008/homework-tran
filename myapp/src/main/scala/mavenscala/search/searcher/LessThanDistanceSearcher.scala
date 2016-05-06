package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator
import mavenscala.search.searcher.calculator.DistanceCalculator

/**
  * Created by y28yang on 4/25/2016.
  */
class LessThanDistanceSearcher(val startName: String,
                               val stopName: String,
                               val lessThanDistance: Int) extends Searcher{

  val routeCalculator=new DistanceCalculator

  def distanceNotExceed(route: Seq[Edge]): Boolean={
    routeCalculator.calcDistance(route)<lessThanDistance
  }


  override def search(iterator: RouteIterator): String = {
    var numberOfRoute=0
    while (iterator.hasNext) {
      val route = iterator.next

      if(route.head.toStation.name!=startName){
        val name=route.head.toStation.name
        iterator.dropCurrentStation
      }
      else if((route.size>1) && (route.last.toStation.name==stopName)){
        if(distanceNotExceed(route))
          numberOfRoute+=1
        else iterator.dropCurrentStation
      }
    }
    numberOfRoute.toString
  }
}
