package mavenscala.railroad

import scala.collection.immutable.HashMap

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class SimpleStation(val stationName: String) extends Station {

  private var canTravelToRouteMap = new HashMap[String, Edge]

  override def allConnectedRoute: Map[String, Edge] = canTravelToRouteMap

  def getRouteOrUpdate(toStationName: String) = {
    canTravelToRouteMap.get(toStationName) match {
      case Some(edge) => edge
      case None => {
        val newStation = new SimpleStation(toStationName)
        addRoute(newStation,0)
      }
    }
  }

  def addRoute(otherStation: Station, distance: Integer): Edge = {
    val otherStationName = otherStation.name
    val newEdge=Edge(otherStation, distance)
    canTravelToRouteMap += otherStationName -> newEdge
    newEdge
  }

  def getDistanceFrom(iterator: Iterator[String]): Int = {
    var distanceTotal = 0
    if (iterator.hasNext) {
      val nextStationName = iterator.next()
      distanceTotal = travelTo(nextStationName)
      canTravelToRouteMap.get(nextStationName) match {
        case Some(Edge(nextStation, distance)) => {
          val nextTravelDistance = nextStation.getDistanceFrom(iterator)
          distanceTotal += nextTravelDistance
        }
        case None =>
      }
    }
    distanceTotal
  }

  override def travelTo(toRoute: String): Int = {
    getRoute(toRoute).distance
  }

  def getRoute(toStationName: String): Edge = {
    canTravelToRouteMap.get(toStationName) match {
      case Some(edge) => edge
      case None => throw new IllegalArgumentException(s"can't travel from $name to $toStationName")
    }
  }

  override def name: String = stationName


}
