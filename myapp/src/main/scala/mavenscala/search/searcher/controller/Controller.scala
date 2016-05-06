package mavenscala.search.searcher.controller

import mavenscala.railroad.Edge

/**
  * Created by y28yang on 5/6/2016.
  */
trait Controller {

  def isNeeded(route: Seq[Edge]):Boolean


  def needDrop(route:Seq[Edge]):Boolean


}
