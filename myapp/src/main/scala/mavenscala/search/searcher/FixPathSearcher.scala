package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator
/**
  * Created by y28yang on 4/25/2016.
  */
abstract class FixPathSearcher(val path:String) extends Searcher{

   val stations=path.split("-")

  override def search(iterator: RouteIterator): String = {
    while (iterator.hasNext){
      val route=iterator.next

      val isNotSame=isLastNameSameWithPredict(route)
      if(isNotSame) {
        iterator.dropCurrentStation
      }else{
        if(route.size==stations.length) {
            return getResult(route)
          }
      }
    }

    return "NO SUCH ROUTE"
  }

  def getResult(route: Seq[Edge]): String

  def isLastNameSameWithPredict(route: Seq[Edge]): Boolean = {
    val lastIndex=route.length-1
    route.last.toStation.name != stations(lastIndex)
  }





}
