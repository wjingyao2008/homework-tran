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

  def getDistanceBy(stationIterator: Iterator[String]): Int = {
    var distanceTotal = 0
    if (stationIterator.hasNext) {
      val nextStationName = stationIterator.next()
      distanceTotal = tryTravelTo(nextStationName)
      routesCanTravelTo.get(nextStationName) match {

        case Some(Edge(nextStation, distance)) =>
          val nextTravelDistance = nextStation.getDistanceBy(stationIterator)
          distanceTotal += nextTravelDistance

        case None =>
      }
    }
    distanceTotal
  }

  override def tryTravelTo(toStationName: String): Int = {
    getRoute(toStationName).distance
  }

  def getRoute(toStationName: String): Edge = {
    routesCanTravelTo.get(toStationName) match {
      case Some(edge) => edge
      case None => throw new NoSuchElementException(s"at $name ,there is no such $toStationName")
    }
  }

  override def name: String = stationName


}
