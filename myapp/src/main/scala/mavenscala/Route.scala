package mavenscala

import mavenscala.Controller.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
trait Route {

  def routeName: String

  def travelTo(toRouteName:String):Int

  def travelThroughSeq(iteratorStr:Iterator[String]):Int

  def addNeighborRoute(otherRoute:Route, distance: Integer)

  def allConnectedRoute:Map[String, Edge]

}
