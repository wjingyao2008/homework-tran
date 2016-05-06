package mavenscala.search.searcher.calculator

import mavenscala.railroad.Edge



class DistanceCalculator extends RouteCalculator{
  override def getResult(route: Seq[Edge]): String = calcDistance(route).toString

  def calcDistance(validPath:Seq[Edge]):Int={
    validPath.map(_.distance).sum
  }
}