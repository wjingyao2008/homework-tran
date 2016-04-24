package mavenscala.Controller

import mavenscala.railroad.Edge

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ShortestDistanceController(startStationName: String, endStationName: String) extends AbstractDistanceController(startStationName, endStationName) {

  private var visitedStationRecorder = Set[String]()
  private var alreadyVisited = false
  private var minimalDistance = Int.MaxValue

  override def getControllingDistance: Int = minimalDistance

  override def moveToNext(edge: Edge): Unit = {
    super.moveToNext(edge)
    val routeName = edge.toStation.name
    if (visitedStationRecorder.contains(routeName))
      alreadyVisited = true
    else
      visitedStationRecorder += routeName
  }

  override def moveBack() = {
    if (searchPath.nonEmpty) {
      val popped = searchPath.pop()
      visitedStationRecorder.-=(popped.toStation.name)
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
