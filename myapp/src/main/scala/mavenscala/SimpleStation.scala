package mavenscala



import scala.collection.immutable.HashMap

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class SimpleStation(val theRouteName: String) extends Station {

  private var canTravelToRouteMap = new HashMap[String, Edge]

  def getRoute(toRouteName: String): Edge = {
    canTravelToRouteMap.get(toRouteName) match {
      case Some(tuple) => tuple
      case None => throw new IllegalArgumentException(s"can't travel from $name to $toRouteName")
    }
  }


  override def allConnectedRoute: Map[String,Edge] = canTravelToRouteMap


  def getRouteOrUpdate(routeName: String) = {
    canTravelToRouteMap.get(routeName) match {
      case Some(tuple) => tuple
      case None => {
        val route: Station = new SimpleStation(routeName)
        val newEdge = Edge(route,0)
        canTravelToRouteMap += routeName -> newEdge
        newEdge
      }
    }
  }

  def addRoute(otherRoute: Station, distance: Integer) = {
    val otherRouteName = otherRoute.name
    canTravelToRouteMap += otherRouteName ->Edge(otherRoute, distance)
  }


  override def name: String = theRouteName

  override def travelTo(toRoute: String): Int = {
     getRoute(toRoute).distance
  }


  def getDistanceFrom(iterator: Iterator[String]): Int = {
    var distanceTotal = 0
    if (iterator.hasNext) {
      val nextRoute = iterator.next()
      distanceTotal = travelTo(nextRoute)
      canTravelToRouteMap.get(nextRoute) match {
        case Some(Edge(station, distance)) => {
          val nextTravelDistance = station.getDistanceFrom(iterator)
          distanceTotal += nextTravelDistance
        }
        case None =>
      }
    }
    distanceTotal
  }


}
