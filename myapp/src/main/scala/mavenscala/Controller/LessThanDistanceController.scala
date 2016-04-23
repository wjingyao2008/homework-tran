package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class LessThanDistanceController(startRoute: String, stopRoute: String,val lessThanDistance: Int) extends AbstractDistanceController(startRoute, stopRoute) {

  override def getControllingDistance: Int = lessThanDistance


  override def keepTravel: Boolean = {
    var found = currentRouteIsSameWithStopRoute
    val isLessThanMaxDistance=currentDistanceNotExceed
    if (found && isLessThanMaxDistance) {
      saveRoutes
    }
    isLessThanMaxDistance
  }

}
