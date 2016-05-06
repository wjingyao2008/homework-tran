package mavenscala.search.searcher

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
class FixPathDurationSearcher(path: String) extends FixPathSearcher(path){


  override def getResult(route: Seq[Edge]): String = {
    calcDuration(route).toString
  }

  def calcDuration(route: Seq[Edge]):Int={
    route.map(_.distance).sum+(route.size-2)*2
  }
}
