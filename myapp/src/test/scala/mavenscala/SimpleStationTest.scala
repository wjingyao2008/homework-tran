package mavenscala

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class SimpleStationTest extends FunSuite with Matchers {

  test("given a route A,when add neighbour B with distance 3,then travel to B will success") {
    val routeA = new SimpleStation("A")
    val routeB = new SimpleStation("B")
    routeA.addRoute(routeB, 3)

    val distance = routeA.travelTo("B")
    distance shouldBe 3
  }

  test("given a route A,when add neighbour B with distance 3,then travel to C will be failed") {
    val routeA = new SimpleStation("A")
    val routeB = new SimpleStation("B")
    routeA.addRoute(routeB, 3)
    val routeC= new SimpleStation("C")
    intercept[IllegalArgumentException]{
      val distance = routeA.travelTo("C")
    }
  }

  test("given a route AB3,BC4,then travel from A to C will be 7") {
    val routeA = new SimpleStation("A")
    val routeB = new SimpleStation("B")
    val routeC = new SimpleStation("C")
    routeA.addRoute(routeB, 3)
    routeB.addRoute(routeC, 4)
    val sequence=List("B","C")
    val distance = routeA.getDistanceFrom(sequence.iterator)
    distance shouldBe 7
  }

  test("given a route AB3,BC4,then travel from A to B will be 3,B to C will be 4") {
    val routeA = new SimpleStation("A")
    val routeB = new SimpleStation("B")
    val routeC = new SimpleStation("C")
    routeA.addRoute(routeB, 3)
    routeB.addRoute(routeC, 4)
    val sequence=List("B")
    var distance = routeA.getDistanceFrom(sequence.iterator)
    distance shouldBe 3

    val sequence2=List("C")
    distance = routeB.getDistanceFrom(sequence2.iterator)
    distance shouldBe 4

  }



}
