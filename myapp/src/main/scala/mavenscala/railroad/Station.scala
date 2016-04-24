package mavenscala.railroad

/**
  * Created by Administrator on 2016/4/23 0023.
  */
trait Station {

  def name: String

  @throws(classOf[IllegalArgumentException])
  def getDistanceFrom(stationIterator: Iterator[String]): Int

  def addRoute(station: Station, distance: Integer): Edge

  def allConnectedRoute: Map[String, Edge]

  protected def tryTravelTo(toStationName: String): Int

}
