package mavenscala.Controller

import mavenscala.{Edge, SimpleStation}
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class ShortestDistanceControllerTest extends FunSuite with Matchers {

  test("when route with A-C,then the path can be get") {
    val startRoute: Edge = Edge(new SimpleStation("A"), 5)
    val endRoute: Edge = Edge(new SimpleStation("C"), 4)
    val controller = new ShortestDistanceController("A", "C")
    controller.moveToNext(startRoute)
    controller.moveToNext(endRoute)
    controller.keepTravel
    controller.moveBack
    controller.getControllingDistance shouldBe 9
  }


  test("given the route A-B-C,A-D-C,then the shortest path can be get") {
    val routeA: Edge = Edge(new SimpleStation("A"), 5)
    val routeB: Edge = Edge(new SimpleStation("B"), 5)
    val routeC: Edge = Edge(new SimpleStation("C"), 4)
    val routeD: Edge = Edge(new SimpleStation("D"), 2)
    val controller = new ShortestDistanceController("A", "C")
    controller.moveToNext(routeB)
    controller.keepTravel shouldBe true
    controller.moveToNext(routeC)
    controller.keepTravel shouldBe false
    controller.moveBack
    controller.moveBack

    controller.moveToNext(routeD)
    controller.keepTravel shouldBe true
    controller.moveToNext(routeC)
    controller.keepTravel shouldBe false
    controller.getControllingDistance shouldBe 6
  }



  test("given the route B-C-B,B-A-B,then the shortest path can be get") {
    val routeA: Edge = Edge(new SimpleStation("A"), 5)
    val routeB: Edge = Edge(new SimpleStation("B"), 5)
    val routeC: Edge = Edge(new SimpleStation("C"), 4)
    val controller = new ShortestDistanceController("B", "B")
    controller.moveToNext(routeC)
    controller.keepTravel shouldBe true
    controller.moveToNext(routeB)
    controller.keepTravel shouldBe false
    controller.moveBack
    controller.moveBack

    controller.moveToNext(routeA)
    controller.keepTravel shouldBe true
    controller.moveToNext(routeB)
    controller.keepTravel shouldBe false
    controller.getControllingDistance shouldBe 9
  }


}
