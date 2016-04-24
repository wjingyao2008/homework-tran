package mavenscala.search.controlimpl

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ExactStepController(startStationName: String, endStationName: String, count: Int) extends DepthController(startStationName, endStationName, count) {

  override def keepTravel: Boolean = {
    var foundRoute = false
    if (searchPath.nonEmpty)
      foundRoute = (endStationName == currentStationName) && (searchPath.size == count)
    if (foundRoute)
      saveRoutes
    val noNeed = foundRoute | (searchPath.size >= count)
    !noNeed
  }


}
