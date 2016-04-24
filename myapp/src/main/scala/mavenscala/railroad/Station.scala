package mavenscala.railroad

import java.util.NoSuchElementException

/**
  * Created by Administrator on 2016/4/23 0023.
  */
trait Station {

  def name: String

  @throws(classOf[NoSuchElementException])
  def getDistanceBy(stationIterator: Iterator[String]): Int

  def addRoute(station: Station, distance: Integer): Edge

  def allConnectedRoute: Map[String, Edge]

  @throws(classOf[NoSuchElementException])
  def getRoute(toStationName: String): Edge

  protected def tryTravelTo(toStationName: String): Int
}
