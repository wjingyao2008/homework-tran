package mavenscala.search.controlimpl

import mavenscala.search.Controller

abstract class AbstractDistanceController(val startStationName: String, val endStationName: String) extends Controller {

  def getControllingDistance: Int

  override def keepTravel: Boolean = {
    var isEndStation = currentStationIsEnd
    if (isEndStation && distanceNotExceed) {
      saveRoutes
    }
    !isEndStation
  }


  protected def currentStationIsEnd = {
    var isSame = false
    if (searchPath.nonEmpty)
      isSame = (endStationName == currentStationName)
    isSame
  }

  def distanceNotExceed: Boolean = sumSearchPathDistance < getControllingDistance



}
