package mavenscala.search

import mavenscala.railroad.Edge

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2016/4/23 0023.
  */
abstract class Controller {
  protected var searchPath = new scala.collection.mutable.Stack[Edge]()
  protected var validRoutes = new ArrayBuffer[String]()

  def moveToNext(edge: Edge): Unit = {
    searchPath.push(edge)
  }

  def moveBack() = {
    if (searchPath.nonEmpty) {
      searchPath.pop()
    }
  }

  def keepTravel: Boolean

  def saveRoutes = {
    val path = searchPath.map(_.toStation.name).reverse.mkString("-")
    val str = s"$startStationName-$path"
    validRoutes += str
  }

  def getValidRoutes: Seq[String] = validRoutes

  def startStationName: String

  def endStationName: String

  protected def hasReachedEndStation = searchPath.nonEmpty && (endStationName == currentStationName)

  def currentStationName = searchPath.top.toStation.name

  protected def sumSearchPathDistance: Int = {
    searchPath.map(_.distance).sum
  }

}
