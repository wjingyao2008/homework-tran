package mavenscala

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class RouteStationTest extends FunSuite with Matchers {

  test("given a route A,when add neighbour B with distance 3,then travel to B will success") {
    val routeA = new RouteStation("A")
    val routeB = new RouteStation("B")
    routeA.addNeighborRoute(routeB, 3)

    val distance = routeA.travelTo("B")
    distance shouldBe 3
  }

  test("given a route A,when add neighbour B with distance 3,then travel to C will be failed") {
    val routeA = new RouteStation("A")
    val routeB = new RouteStation("B")
    routeA.addNeighborRoute(routeB, 3)
    val routeC= new RouteStation("C")
    intercept[IllegalArgumentException]{
      val distance = routeA.travelTo("C")
    }
  }

  test("given a route AB3,BC4,then travel from A to C will be 7") {
    val routeA = new RouteStation("A")
    val routeB = new RouteStation("B")
    val routeC = new RouteStation("C")
    routeA.addNeighborRoute(routeB, 3)
    routeB.addNeighborRoute(routeC, 4)
    val sequence=List("B","C")
    val distance = routeA.travelThroughSeq(sequence.iterator)
    distance shouldBe 7
  }

  test("given a route AB3,BC4,then travel from A to B will be 3,B to C will be 4") {
    val routeA = new RouteStation("A")
    val routeB = new RouteStation("B")
    val routeC = new RouteStation("C")
    routeA.addNeighborRoute(routeB, 3)
    routeB.addNeighborRoute(routeC, 4)
    val sequence=List("B")
    var distance = routeA.travelThroughSeq(sequence.iterator)
    distance shouldBe 3

    val sequence2=List("C")
    distance = routeB.travelThroughSeq(sequence2.iterator)
    distance shouldBe 4

  }



}
