package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class DepthController(val startRoute:String, val endAtRoute: String,val count: Int) extends Controller {


  override def saveRoutes: Unit = {
    val path = searchPath.map(_.toRoute.routeName).reverse.mkString("-")
    val str=s"$startRoute-$path"
    validSequence+=str
  }

  override def keepTravel: Boolean = {
    var found = false
    if (searchPath.nonEmpty)
      found = endAtRoute == (searchPath.top.toRoute.routeName)
    if (found)
      saveRoutes

    val noNeed = found | (searchPath.size >= count)
    !noNeed
  }


}
