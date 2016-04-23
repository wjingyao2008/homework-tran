package mavenscala.Controller

import mavenscala.{Edge, RouteStation}
import org.scalatest.FunSuite

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class DepthControllerTest extends FunSuite {

  test("given 5 search depth,then ") {
    val deepControler=new DepthController("A","C",5)
    deepControler.moveToNextRoute(Edge(new RouteStation("A"),1))
    deepControler.moveToNextRoute(Edge(new RouteStation("B"),1))
    deepControler.moveToNextRoute(Edge(new RouteStation("C"),1))
    deepControler.saveRoutes
    deepControler.moveBack()
    deepControler.saveRoutes
    println(deepControler.getAllSeq.toString())

  }

}
