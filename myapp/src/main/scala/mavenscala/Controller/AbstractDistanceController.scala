package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
abstract class AbstractDistanceController(val startRoute: String, val stopRoute: String) extends Controller {

  def getControllingDistance: Int

  override def keepTravel: Boolean = {
    var found = currentRouteIsSameWithStopRoute
    if (found && currentDistanceNotExceed) {
      saveRoutes
    }

    val noNeed = found
    !noNeed
  }


  def currentRouteIsSameWithStopRoute={
    var isSame = false
    if (searchPath.nonEmpty)
      isSame = (stopRoute ==topStackRouteName)
    isSame
  }

  def currentDistanceNotExceed: Boolean = sumCurrentSearchPathDistance < getControllingDistance

  override def saveRoutes: Unit = {
    val path = searchPath.map(_.toStation.name).reverse.mkString("-")
    val str = s"$startRoute-$path"
    validSequence += str
  }


}
