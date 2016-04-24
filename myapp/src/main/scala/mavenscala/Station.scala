package mavenscala

import mavenscala.Controller.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
trait Station {

  def name: String

  def travelTo(toStationName:String):Int

  def travelThroughSeq(stationIterator:Iterator[String]):Int

  def addRoute(station:Station, distance: Integer)

  def allConnectedRoute:Map[String, Edge]

}
