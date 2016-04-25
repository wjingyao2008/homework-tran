package mavenscala.railroad

import java.util.NoSuchElementException

/**
  * Created by Administrator on 2016/4/23 0023.
  */
trait Station {

  def name: String

  def addRoute(station: Station, distance: Integer): Edge

  def allConnectedRoute: Map[String, Edge]

}
