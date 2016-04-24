package mavenscala.search.controlimpl

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ExactStepController(startStationName: String, endStationName: String, count: Int) extends DepthController(startStationName, endStationName, count) {

  override def keepTravel: Boolean = {
    var found = false
    if (searchPath.nonEmpty)
      found = (endStationName == currentStationName) && (searchPath.size == count)
    if (found)
      saveRoutes
    val noNeed = found | (searchPath.size >= count)
    !noNeed
  }


}
