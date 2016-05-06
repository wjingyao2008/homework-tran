package mavenscala.search.searcher

import mavenscala.railroad.Edge
import mavenscala.search.RouteIterator
import mavenscala.search.searcher.calculator.RouteCalculator
/**
  * Created by y28yang on 4/25/2016.
  */
class FixPathSearcher(val path:String,resultGetter:RouteCalculator) extends Searcher{

   val stations=path.split("-")

  override def search(iterator: RouteIterator): String = {
    while (iterator.hasNext){
      val route=iterator.next

      val isNotSame=isLastNameSameWithPredict(route)
      if(isNotSame) {
        iterator.dropCurrentStation
      }else{
        if(route.size==stations.length) {
            return resultGetter.getResult(route)
          }
      }
    }

    return "NO SUCH ROUTE"
  }


  def isLastNameSameWithPredict(route: Seq[Edge]): Boolean = {
    val lastIndex=route.length-1
    route.last.toStation.name != stations(lastIndex)
  }





}
