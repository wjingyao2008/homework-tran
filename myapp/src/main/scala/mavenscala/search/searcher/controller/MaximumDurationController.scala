package mavenscala.search.searcher.controller

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
class MaximumDurationController(maxDuration:Int) extends Controller{


  def calcDuration(route:Seq[Edge])=route.map(_.distance).sum+(route.size-2)*2

  override def isNeeded(route: Seq[Edge]): Boolean = {
    calcDuration(route)<=maxDuration
  }

  override def needDrop(route: Seq[Edge]): Boolean = {
    calcDuration(route)>=maxDuration
  }


}
