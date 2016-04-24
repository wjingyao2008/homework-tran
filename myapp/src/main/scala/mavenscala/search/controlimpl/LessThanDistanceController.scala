package mavenscala.search.controlimpl

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class LessThanDistanceController(startStationName: String, toStationName: String, val lessThanDistance: Int) extends AbstractDistanceController(startStationName, toStationName) {

  override def getControllingDistance: Int = lessThanDistance


  override def keepTravel: Boolean = {
    val isLessThanMaxDistance = distanceNotExceed
    if (currentStationIsDestination && isLessThanMaxDistance) {
      saveRoutes
    }
    isLessThanMaxDistance
  }

}
