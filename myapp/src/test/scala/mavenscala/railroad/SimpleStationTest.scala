package mavenscala.railroad

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class SimpleStationTest extends FunSuite with Matchers {

  test("given a route A,when add neighbour B with distance 3,then travel to B will success") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    stationA.addRoute(stationB, 3)

    val distance = stationA.travelTo("B")
    distance shouldBe 3
  }

  test("given a route A,when add neighbour B with distance 3,then travel to C will be failed") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    stationA.addRoute(stationB, 3)
    val routeC = new SimpleStation("C")
    intercept[IllegalArgumentException] {
      val distance = stationA.travelTo("C")
    }
  }

  test("given a route AB3,BC4,then travel from A to C will be 7") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    val routeC = new SimpleStation("C")
    stationA.addRoute(stationB, 3)
    stationB.addRoute(routeC, 4)
    val sequence = List("B", "C")
    val distance = stationA.getDistanceFrom(sequence.iterator)
    distance shouldBe 7
  }

  test("given a route AB3,BC4,then travel from A to B will be 3,B to C will be 4") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    val routeC = new SimpleStation("C")
    stationA.addRoute(stationB, 3)
    stationB.addRoute(routeC, 4)
    val sequence = List("B")
    var distance = stationA.getDistanceFrom(sequence.iterator)
    distance shouldBe 3

    val sequence2 = List("C")
    distance = stationB.getDistanceFrom(sequence2.iterator)
    distance shouldBe 4

  }


}
