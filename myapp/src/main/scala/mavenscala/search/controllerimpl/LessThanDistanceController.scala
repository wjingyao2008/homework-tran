package mavenscala.search.controllerimpl

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class LessThanDistanceController(startStationName: String, endStationName: String, val lessThanDistance: Int) extends AbstractDistanceController(startStationName, endStationName) {

  override def getControllingDistance: Int = lessThanDistance


  override def keepTravel: Boolean = {
    val isLessThanMaxDistance = distanceNotExceed
    if (hasReachedEndStation && isLessThanMaxDistance) {
      saveRoutes
    }
    isLessThanMaxDistance
  }

}
