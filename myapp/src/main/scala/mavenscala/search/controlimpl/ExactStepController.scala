package mavenscala.search.controlimpl

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ExactStepController(startStationName: String, endStationName: String, steps: Int) extends MaximumStepsController(startStationName, endStationName, steps) {

  override def keepTravel: Boolean = {
    val foundRoute = hasReachedEndStation && (searchPath.size == steps)
    if (foundRoute)
      saveRoutes
    val noNeed = foundRoute | (searchPath.size >= steps)
    !noNeed
  }


}
