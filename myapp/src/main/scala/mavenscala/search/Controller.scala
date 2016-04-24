package mavenscala.search

import mavenscala.railroad.Edge

import scala.collection.mutable.{ArrayBuffer, Stack}

/**
  * Created by Administrator on 2016/4/23 0023.
  */
abstract class Controller {
  protected var searchPath = new Stack[Edge]
  protected var validRoutes = new ArrayBuffer[String]()

  def moveToNext(edge: Edge): Unit = {
    searchPath.push(edge)
  }

  def moveBack() = {
    if (searchPath.nonEmpty) {
      searchPath.pop()
    }
  }

  def currentStationName = searchPath.top.toStation.name

  def keepTravel: Boolean

  def saveRoutes:Unit

  def getValidRoutes: Seq[String] = validRoutes

  def sumSearchPathDistance: Int = {
    searchPath.map(_.distance).sum
  }

}
