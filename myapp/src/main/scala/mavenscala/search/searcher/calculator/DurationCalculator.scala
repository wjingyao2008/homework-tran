package mavenscala.search.searcher.calculator

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
class DurationCalculator extends RouteCalculator{
  override def getResult(route: Seq[Edge]): String =  (route.map(_.distance).sum+(route.size-2)*2).toString
}
