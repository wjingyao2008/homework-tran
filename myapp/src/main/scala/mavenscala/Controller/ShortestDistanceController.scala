package mavenscala.Controller

import mavenscala.{Edge, Route}

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ShortestDistanceController(startRoute: String, endAtRoute: String) extends AbstractDistanceController(startRoute, endAtRoute) {

  private var routeSet = Set[String]()
  private var alreadyVisited = false
  private var minimalDistance = Int.MaxValue

  override def getControllingDistance: Int = minimalDistance

  override def moveToNextRoute(route:Edge):Unit = {
    super.moveToNextRoute(route)
    val routeName = route.toRoute.routeName
    if (routeSet.contains(routeName))
      alreadyVisited = true
    else
      routeSet += routeName
  }

  override def moveBack() = {
    if (searchPath.nonEmpty) {
      val poped = searchPath.pop()
      routeSet.-=(poped.toRoute.routeName)
      alreadyVisited = false
    }
  }

  override def keepGoingDown: Boolean = {
    if (alreadyVisited)
      return false
    return super.keepGoingDown
  }


  override def preseaveSequence() = {
    minimalDistance = sumCurrentSearchPathDistance
    validSequence.clear()
    super.preseaveSequence
  }


}
