package mavenscala.search.searcher.controller

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
class ExactStepsController(exactSteps:Int) extends Controller{

  val exactRouteNbr=exactSteps+1

  override def isNeeded(route: Seq[Edge]): Boolean = {
    val size=route.size
    size==exactRouteNbr && size>1
  }

  override def needDrop(route: Seq[Edge]): Boolean = {
    route.length > exactRouteNbr
  }


}
