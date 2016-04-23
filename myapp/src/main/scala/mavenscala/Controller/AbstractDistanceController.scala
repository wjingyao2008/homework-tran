package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
abstract class AbstractDistanceController(val startRoute: String, val stopRoute: String) extends Controller {

  def getControllingDistance: Int

  override def keepGoingDown: Boolean = {
    var found = currentRouteIsSameWithStopRoute
    if (found && currentDistanceNotExceed) {
      preseaveSequence
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

  override def preseaveSequence: Unit = {
    val path = searchPath.map(_.toRoute.routeName).reverse.mkString("-")
    val str = s"$startRoute-$path"
    validSequence += str
  }


}
