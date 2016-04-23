package mavenscala



import scala.collection.immutable.HashMap

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class RouteStation(val theRouteName: String) extends Route {

  private var canTravelToRouteMap = new HashMap[String, Edge]

  def getRoute(toRouteName: String): Edge = {
    canTravelToRouteMap.get(toRouteName) match {
      case Some(tuple) => tuple
      case None => throw new IllegalArgumentException(s"can't travel from $routeName to $toRouteName")
    }
  }


  override def allConnectedRoute: Map[String,Edge] = canTravelToRouteMap


  def getRouteOrUpdate(routeName: String) = {
    canTravelToRouteMap.get(routeName) match {
      case Some(tuple) => tuple
      case None => {
        val route: Route = new RouteStation(routeName)
        val newEdge = Edge(route,0)
        canTravelToRouteMap += routeName -> newEdge
        newEdge
      }
    }
  }

  def addNeighborRoute(otherRoute: Route, distance: Integer) = {
    val otherRouteName = otherRoute.routeName
    canTravelToRouteMap += otherRouteName ->Edge(otherRoute, distance)
  }


  override def routeName: String = theRouteName

  override def travelTo(toRoute: String): Int = {
     getRoute(toRoute).distance
  }


  def travelThroughSeq(iterator: Iterator[String]): Int = {
    var distanceTotal = 0
    if (iterator.hasNext) {
      val nextRoute = iterator.next()
      distanceTotal = travelTo(nextRoute)
      canTravelToRouteMap.get(nextRoute) match {
        case Some(Edge(nextRoute, distance)) => {
          val nextTravelDistance = nextRoute.travelThroughSeq(iterator)
          distanceTotal += nextTravelDistance
        }
        case None =>
      }
    }
    distanceTotal
  }


}
