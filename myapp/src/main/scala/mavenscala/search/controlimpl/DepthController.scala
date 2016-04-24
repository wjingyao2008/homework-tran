package mavenscala.search.controlimpl

import mavenscala.search.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class DepthController(val startStationName: String, val endStationName: String, val steps: Int) extends Controller {


  override def keepTravel: Boolean = {
    var isDestination = hasReachedEndStation
    if (isDestination)
      saveRoutes

    val noNeedTravel = isDestination || (searchPath.size >= steps)
    !noNeedTravel
  }



}
