package mavenscala.Controller

import mavenscala.Edge

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ShortestDistanceController(startRoute: String, endAtRoute: String) extends AbstractDistanceController(startRoute, endAtRoute) {

  private var routeSet = Set[String]()
  private var alreadyVisited = false
  private var minimalDistance = Int.MaxValue

  override def getControllingDistance: Int = minimalDistance

  override def moveToNext(route: Edge): Unit = {
    super.moveToNext(route)
    val routeName = route.toStation.name
    if (routeSet.contains(routeName))
      alreadyVisited = true
    else
      routeSet += routeName
  }

  override def moveBack() = {
    if (searchPath.nonEmpty) {
      val poped = searchPath.pop()
      routeSet.-=(poped.toStation.name)
      alreadyVisited = false
    }
  }

  override def keepTravel: Boolean = {
    if (alreadyVisited)
      return false
    return super.keepTravel
  }


  override def saveRoutes() = {
    minimalDistance = sumSearchPathDistance
    validRoutes.clear()
    super.saveRoutes
  }


}
