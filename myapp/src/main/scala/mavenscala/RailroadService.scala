package mavenscala

import mavenscala.Controller._

//import scala.collection.mutable.Map
/**
  * Created by Administrator on 2016/4/23 0023.
  */
class RailroadService {

  private var rootRoute = new SimpleStation("root")

  def getStation(routeName: String) = rootRoute.getRoute(routeName).toStation

  def addRoute(represent: String): Unit = {
    if (represent.length != 3)
      throw new IllegalArgumentException(s"the route between two towns are not valid: $represent.")
    val fromStation = represent.charAt(0).toString
    val toStation = represent.charAt(1).toString
    val distance = Integer.valueOf(represent.charAt(2).toString)
    addRoute(fromStation, toStation, distance)
  }


  private def addRoute(fromStation: String, toStation: String, distance: Int): Unit = {
    val fromRoute = getOrCreatRoute(fromStation)
    val toRoute = getOrCreatRoute(toStation)
    fromRoute.addRoute(toRoute, distance)
  }


  private def getOrCreatRoute(routName: String): Station = {
    rootRoute.getRouteOrUpdate(routName).toStation
  }

  def getDistance(routeSequeceStr: String): String = {
    val routeSequece = routeSequeceStr.split("-")
    try {
      rootRoute.travelThroughSeq(routeSequece.iterator).toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }

  def getTripNumberInMaximumStops(startRouteName: String, toRouteName: String, maxStops: Int): String = {
    try {
      val controller = new DepthController(startRouteName, toRouteName, maxStops)
      runsearch(startRouteName, toRouteName, controller)
      controller.getAllSeq.size.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }

  private def runsearch(startRouteName: String, toRouteName: String, controller: Controller) = {
    val startRoute = checkRouteExist(startRouteName)
    val toRoute = checkRouteExist(toRouteName)
    val searcher = new RouteDeepSearcher(controller)
    searcher.search(startRoute)
  }


  private def checkRouteExist(routeName: String): Station = this.getStation(routeName)

  def getTripNumberInExactStops(startRouteName: String, toRouteName: String, exactStops: Int): String = {
    try {
      val controller = new ExactStepController(startRouteName, toRouteName, exactStops)
      runsearch(startRouteName, toRouteName, controller)
      controller.getAllSeq.size.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }

  def getShortestDistanceBetween(startRouteName: String, toRouteName: String):String={
    try {
      val controller = new ShortestDistanceController(startRouteName, toRouteName)
      runsearch(startRouteName, toRouteName, controller)
      controller.getControllingDistance.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }


  def getTripNumberLessThan(startRouteName: String, toRouteName: String,lessThanDistance:Int):String={
    try {
      val controller = new LessThanDistanceController(startRouteName, toRouteName,lessThanDistance)
      runsearch(startRouteName, toRouteName, controller)
      controller.getAllSeq.size.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }



}
