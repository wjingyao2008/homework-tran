package mavenscala

import mavenscala.Controller._
import mavenscala.railroad.{RouteDeepSearcher, SimpleStation, Station}

//import scala.collection.mutable.Map
/**
  * Created by Administrator on 2016/4/23 0023.
  */
class RailroadService {

  private val rootStation = new SimpleStation("root")

  def addRoute(represent: String): Unit = {
    if (represent.length != 3)
      throw new IllegalArgumentException(s"the route between two towns are not valid: $represent.")
    val fromStation = represent.charAt(0).toString
    val toStation = represent.charAt(1).toString
    val distance = Integer.valueOf(represent.charAt(2).toString)
    addRoute(fromStation, toStation, distance)
  }

  private def addRoute(fromStationName: String, toStationName: String, distance: Int): Unit = {
    val fromStation = getOrCreatStation(fromStationName)
    val toStation = getOrCreatStation(toStationName)
    fromStation.addRoute(toStation, distance)
  }

  private def getOrCreatStation(stationName: String): Station = {
    rootStation.getRouteOrUpdate(stationName).toStation
  }

  def getDistance(routeSequeceStr: String): String = {
    val stations = routeSequeceStr.split("-").iterator
    try {
      rootStation.getDistanceFrom(stations).toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }

  def getTripNumberInMaximumStops(startStation: String, toStation: String, maxStops: Int): String = {
    try {
      val controller = new DepthController(startStation, toStation, maxStops)
      runsearch(startStation, toStation, controller)
      controller.getValidRoutes.size.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }

  private def runsearch(startStationName: String, toStationName: String, controller: Controller) = {
    val startStation = checkRouteExist(startStationName)
    checkRouteExist(toStationName)
    val searcher = new RouteDeepSearcher(controller)
    searcher.search(startStation)
  }

  private def checkRouteExist(stationName: String): Station = this.getStation(stationName)

  def getStation(routeName: String) = rootStation.getRoute(routeName).toStation

  def getTripNumberInExactStops(startName: String, toName: String, exactStops: Int): String = {
    try {
      val controller = new ExactStepController(startName, toName, exactStops)
      runsearch(startName, toName, controller)
      controller.getValidRoutes.size.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }

  def getShortestDistanceBetween(startName: String, toName: String): String = {
    try {
      val controller = new ShortestDistanceController(startName, toName)
      runsearch(startName, toName, controller)
      controller.getControllingDistance.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }


  def getTripNumberLessThan(startName: String, toName: String, lessThanDistance: Int): String = {
    try {
      val controller = new LessThanDistanceController(startName, toName, lessThanDistance)
      runsearch(startName, toName, controller)
      controller.getValidRoutes.size.toString
    } catch {
      case ex: IllegalArgumentException => "NO SUCH ROUTE"
    }
  }


}
