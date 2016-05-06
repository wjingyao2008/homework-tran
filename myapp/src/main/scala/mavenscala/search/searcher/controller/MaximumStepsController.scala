package mavenscala.search.searcher.controller

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
class MaximumStepsController(maxStops: Int) extends Controller{
  val maxRouteNbr = maxStops + 1

  override def isNeeded(route: Seq[Edge]): Boolean = {
    val size = route.size
    size <= maxRouteNbr && size > 1
  }


  override def needDrop(route: Seq[Edge]): Boolean = {
    route.length>maxRouteNbr
  }

}
