package mavenscala.Controller

import mavenscala.{Edge, Route, RouteStation}
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class ShortestDistanceControllerTest extends FunSuite with Matchers{

  test("when route with A-C,then the path can be get") {
    val startRoute:Edge=Edge(new RouteStation("A"),5)
    val endRoute:Edge=Edge(new RouteStation("C"),4)
    val controller=new ShortestDistanceController("A","C")
    controller.moveToNextRoute(startRoute)
    controller.moveToNextRoute(endRoute)
    controller.keepTravel
    controller.moveBack
    controller.getControllingDistance shouldBe 9
  }


  test("given the route A-B-C,A-D-C,then the shortest path can be get") {
    val routeA:Edge=Edge(new RouteStation("A"),5)
    val routeB:Edge=Edge(new RouteStation("B"),5)
    val routeC:Edge=Edge(new RouteStation("C"),4)
    val routeD:Edge=Edge(new RouteStation("D"),2)
    val controller=new ShortestDistanceController("A","C")
    controller.moveToNextRoute(routeB)
    controller.keepTravel shouldBe true
    controller.moveToNextRoute(routeC)
    controller.keepTravel shouldBe false
    controller.moveBack
    controller.moveBack

    controller.moveToNextRoute(routeD)
    controller.keepTravel shouldBe true
    controller.moveToNextRoute(routeC)
    controller.keepTravel shouldBe false
    controller.getControllingDistance shouldBe 6
  }



  test("given the route B-C-B,B-A-B,then the shortest path can be get") {
    val routeA:Edge=Edge(new RouteStation("A"),5)
    val routeB:Edge=Edge(new RouteStation("B"),5)
    val routeC:Edge=Edge(new RouteStation("C"),4)
    val controller=new ShortestDistanceController("B","B")
    controller.moveToNextRoute(routeC)
    controller.keepTravel shouldBe true
    controller.moveToNextRoute(routeB)
    controller.keepTravel shouldBe false
    controller.moveBack
    controller.moveBack

    controller.moveToNextRoute(routeA)
    controller.keepTravel shouldBe true
    controller.moveToNextRoute(routeB)
    controller.keepTravel shouldBe false
    controller.getControllingDistance shouldBe 9
  }


}
