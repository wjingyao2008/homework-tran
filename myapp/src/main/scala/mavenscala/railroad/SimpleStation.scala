package mavenscala.railroad

import scala.collection.immutable.HashMap

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class SimpleStation(val stationName: String) extends Station {

  private var routesCanTravelTo = new HashMap[String, Edge]

  override def allConnectedRoute: Map[String, Edge] = routesCanTravelTo

  def getRouteOrUpdate(toStationName: String) = {
    routesCanTravelTo.get(toStationName) match {
      case Some(edge) => edge
      case None => addRoute(new SimpleStation(toStationName), 0)
    }
  }

  def addRoute(otherStation: Station, distance: Integer): Edge = {
    val otherName = otherStation.name
    val newEdge = Edge(otherStation, distance)
    routesCanTravelTo += otherName -> newEdge
    newEdge
  }


  override def name: String = stationName


}
