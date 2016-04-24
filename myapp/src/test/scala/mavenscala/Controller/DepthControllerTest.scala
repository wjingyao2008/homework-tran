package mavenscala.Controller

import mavenscala.{Edge, SimpleStation}
import org.scalatest.FunSuite

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class DepthControllerTest extends FunSuite {

  test("given 5 search depth,then ") {
    val deepControler=new DepthController("A","C",5)
    deepControler.moveToNextRoute(Edge(new SimpleStation("A"),1))
    deepControler.moveToNextRoute(Edge(new SimpleStation("B"),1))
    deepControler.moveToNextRoute(Edge(new SimpleStation("C"),1))
    deepControler.saveRoutes
    deepControler.moveBack()
    deepControler.saveRoutes
    println(deepControler.getAllSeq.toString())

  }

}
