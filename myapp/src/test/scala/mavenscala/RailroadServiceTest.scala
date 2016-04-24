package mavenscala

import org.scalatest.{BeforeAndAfterEach, FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class RailroadServiceTest extends FunSuite with Matchers with BeforeAndAfterEach {

  val service = new RailroadService
  service.addRoute("AB5")
  service.addRoute("BC4")
  service.addRoute("CD8")
  service.addRoute("DC8")
  service.addRoute("DE6")
  service.addRoute("AD5")
  service.addRoute("CE2")
  service.addRoute("EB3")
  service.addRoute("AE7")


  test("given service,then A-B distance will be 5") {
    service.getDistance("A-B") shouldBe "5"
  }

  test("given service,then A G distance will be NO SUCH ROUTE") {
    service.getDistance("A-G") shouldBe "NO SUCH ROUTE"
  }

  test("given service,then A-B-C distance will be 9") {
    service.getDistance("A-B-C") shouldBe "9"
  }

  test("given service,then distance will be correct") {
    service.getDistance("A-D") shouldBe "5"
    service.getDistance("A-D-C") shouldBe "13"
   service.getDistance("A-E-B-C-D") shouldBe "22"
  }

  test("given A-E-D,then other route sequence distance will be NO SUCH ROUTE") {
    service.getDistance("A-E-D") shouldBe "NO SUCH ROUTE"
  }

  test("given start from C and ending at C with a maximum of 3 stops,then number of trips will be 2") {
    service.getTripNumberInMaximumStops("C", "C", 3) shouldBe "2"
  }


  test("given start from C and ending at C with a maximum of 1 stops,then the result will be 0") {
    service.getTripNumberInMaximumStops("C", "C", 1) shouldBe "0"
  }

  test("given unknown end route F,then the result will be NO SUCH ROUTE") {
    service.getTripNumberInMaximumStops("C", "F", 1) shouldBe "NO SUCH ROUTE"
  }

  test("given unknown start route F ,then the result will be NO SUCH ROUTE") {
    service.getTripNumberInMaximumStops("F", "A", 1) shouldBe "NO SUCH ROUTE"
  }

  test("given start from A to C ,then the shortest distance will be 9") {
    service.getShortestDistanceBetween("A", "C") shouldBe "9"
  }

  test("given start from B to B ,then the shortest distance will be 9") {
    service.getShortestDistanceBetween("B", "B") shouldBe "9"
  }

  test("given start from C to C with a distance less than 30,then the number of different routes will be 7") {
    service.getTripNumberLessThan("C", "C", 30) shouldBe "7"
  }

}
