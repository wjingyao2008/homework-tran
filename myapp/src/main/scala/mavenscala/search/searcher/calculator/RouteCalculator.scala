package mavenscala.search.searcher.calculator

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
trait RouteCalculator {
  def getResult(route: Seq[Edge]): String
}
