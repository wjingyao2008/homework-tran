package mavenscala.search.searcher

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
class FixPathDistanceSearcher(path: String) extends FixPathSearcher(path) {

  def getResult(route: Seq[Edge]): String = {
    calcDistance(route).toString
  }


}
