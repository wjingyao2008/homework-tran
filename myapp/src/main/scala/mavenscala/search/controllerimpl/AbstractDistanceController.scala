package mavenscala.search.controllerimpl

import mavenscala.search.Controller

abstract class AbstractDistanceController(val startStationName: String, val endStationName: String) extends Controller {

  def getControllingDistance: Int

  override def keepTravel: Boolean = {
    var isEndStation = hasReachedEndStation
    if (hasReachedEndStation && distanceNotExceed) {
      saveRoutes
    }
    !isEndStation
  }


  def distanceNotExceed: Boolean = sumSearchPathDistance < getControllingDistance


}
