package mavenscala.search.controlimpl

import mavenscala.search.Controller

abstract class AbstractDistanceController(val startStaName: String, val stopStaName: String) extends Controller {

  def getControllingDistance: Int

  override def keepTravel: Boolean = {
    var isDestination = currentStationIsDestination
    if (isDestination && distanceNotExceed) {
      saveRoutes
    }
    !isDestination
  }


  protected def currentStationIsDestination = {
    var isSame = false
    if (searchPath.nonEmpty)
      isSame = (stopStaName == currentStationName)
    isSame
  }

  def distanceNotExceed: Boolean = sumSearchPathDistance < getControllingDistance

  override def saveRoutes: Unit = {
    val path = searchPath.map(_.toStation.name).reverse.mkString("-")
    val str = s"$startStaName-$path"
    validRoutes += str
  }


}
