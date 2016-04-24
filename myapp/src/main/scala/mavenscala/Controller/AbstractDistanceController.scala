package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
abstract class AbstractDistanceController(val startStaName: String, val stopStaName: String) extends Controller {

  def getControllingDistance: Int

  override def keepTravel: Boolean = {
    var isDestination = currentStationIsDestination
    if (isDestination && distanceNotExceed) {
      saveRoutes
    }

    val noNeed = isDestination
    !noNeed
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
