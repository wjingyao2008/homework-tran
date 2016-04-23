package mavenscala.Controller

import mavenscala.{Edge, Route}

import scala.collection.mutable.{ArrayBuffer, Stack}

/**
  * Created by Administrator on 2016/4/23 0023.
  */
abstract class Controller {
  protected var searchPath = new Stack[Edge]
  protected var validSequence = new ArrayBuffer[String]()

  def moveToNextRoute(edge:Edge): Unit = {
    searchPath.push(edge)
  }

  def moveBack() = {
    if (searchPath.nonEmpty) {
      searchPath.pop()
    }
  }

  def topStackRouteName=searchPath.top.toRoute.routeName

  def keepTravel: Boolean

  def saveRoutes

  def getAllSeq: Seq[String] = validSequence

  def sumCurrentSearchPathDistance: Int = {
    searchPath.map(_.distance).sum
  }

}
