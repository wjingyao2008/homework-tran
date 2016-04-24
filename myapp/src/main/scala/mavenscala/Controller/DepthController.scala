package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class DepthController(val startStationName: String, val endStationName: String, val count: Int) extends Controller {


  override def keepTravel: Boolean = {
    var isDestination = false
    if (searchPath.nonEmpty)
      isDestination = (endStationName == currentStationName)
    if (isDestination)
      saveRoutes

    val noNeedTravel = isDestination | (searchPath.size >= count)
    !noNeedTravel
  }

  override def saveRoutes: Unit = {
    val path = searchPath.map(_.toStation.name).reverse.mkString("-")
    val str = s"$startStationName-$path"
    validRoutes += str
  }


}
