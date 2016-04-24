package mavenscala.railroad

import java.util.NoSuchElementException

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class SimpleStationTest extends FunSuite with Matchers {

  test("given a route A,when add neighbour B with distance 3,then travel to B will success") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    stationA.addRoute(stationB, 3)

    val distance = stationA.getDistanceBy(Iterator("B"))
    distance shouldBe 3
  }

  test("given a route A,when add neighbour B with distance 3,then travel to C will be failed") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    stationA.addRoute(stationB, 3)
    val routeC = new SimpleStation("C")
    intercept[NoSuchElementException] {
      val distance = stationA.getDistanceBy(Iterator("C"))
    }
  }

  test("given a route AB3,BC4,then travel from A to C will be 7") {
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    val routeC = new SimpleStation("C")
    stationA.addRoute(stationB, 3)
    stationB.addRoute(routeC, 4)
    val distance = stationA.getDistanceBy(Iterator("B", "C"))
    distance shouldBe 7
  }

  test("given a route AB3,BC4,then travel from A to B will be 3,B to C will be 4") {
    val stationRoot = new SimpleStation("root")
    val stationA = new SimpleStation("A")
    val stationB = new SimpleStation("B")
    val routeC = new SimpleStation("C")
    stationRoot.addRoute(stationA, 0)
    stationRoot.addRoute(stationB, 0)
    stationA.addRoute(stationB, 3)
    stationB.addRoute(routeC, 4)
    var distance = stationRoot.getDistanceBy(Iterator("A", "B"))
    distance shouldBe 3

    distance = stationRoot.getDistanceBy(Iterator("B", "C"))
    distance shouldBe 4

  }


}
