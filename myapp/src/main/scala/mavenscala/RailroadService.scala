package mavenscala

import java.util.NoSuchElementException

import mavenscala.railroad.{SimpleStation, Station}
import mavenscala.search._
import mavenscala.search.controllerimpl.{ExactStepController, LessThanDistanceController, MaximumStepsController, ShortestDistanceController}
import mavenscala.search.searcher._

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class RailroadService {


  val rootStation = new SimpleStation("root")

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

  def getDistance(routeStr: String): String = {
    val searcher=new FixPathSearcher(routeStr)
    useSearcher(searcher)
  }

  def getTripNumberInMaximumStops(startStation: String, toStation: String, maxStops: Int): String = {
    val searcher=new MaximumStepsSearcher(startStation,toStation,maxStops)
    useSearcher(searcher)
  }


  def getTripNumberInExactStops(startName: String, toName: String, exactStops: Int): String = {
    val searcher=new ExactStopSearcher(startName,toName,exactStops)
    useSearcher(searcher)
  }


  def getTripNumberLessThan(startName: String, toName: String, lessThanDistance: Int): String = {
    val searcher=new LessThanDistanceSearcher(startName,toName,lessThanDistance)
    useSearcher(searcher)
  }

  def getShortestDistanceBetween(startName: String, toName: String) = {
    val searcher=new ShortestDistanceSearcher(startName,toName)
    useSearcher(searcher)
  }

  private def useSearcher(searcher:Searcher)={
    val routeIterator=new RouteIterator(rootStation)
    searcher.search(routeIterator)
  }

}
