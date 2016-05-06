package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator

/**
  * Created by y28yang on 4/25/2016.
  */
trait Searcher {

  def search(iterator:RouteIterator):String

}
