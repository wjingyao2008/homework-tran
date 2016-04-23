package mavenscala.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class ExactStepController(startRoute: String, endAtRoute: String,count: Int) extends DepthController(startRoute, endAtRoute,count) {

  override def keepTravel: Boolean = {
    var found = false
    if (searchPath.nonEmpty)
      found = (endAtRoute ==topStackRouteName)&&(searchPath.size == count)
    if (found)
      saveRoutes
    val noNeed = found | (searchPath.size >= count)
    !noNeed
  }


}
