package mavenscala.search

import mavenscala.RailroadService
import mavenscala.search.controllerimpl.{ExactStepController, LessThanDistanceController, MaximumStepsController, ShortestDistanceController}
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class RouteDeepSearcherTest extends FunSuite with Matchers {
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


  test("given all controller,then Controller startName and endName shall be override") {

    assertStartAndEndStationName(new MaximumStepsController("A", "B", 3))
    assertStartAndEndStationName(new ExactStepController("A", "B", 3))
    assertStartAndEndStationName(new LessThanDistanceController("A", "B", 3))
    assertStartAndEndStationName(new ShortestDistanceController("A", "B"))
  }

  def assertStartAndEndStationName(controller: Controller) = {
    controller.startStationName shouldBe "A"
    controller.endStationName shouldBe "B"
  }


  test("given search from C to C in the route,then the result will be get") {
    val strartRoute = service.getStation("C")
    val endRoute = service.getStation("C")
    val controler = new MaximumStepsController(strartRoute.name, endRoute.name, 3)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(strartRoute)
    controler.getValidRoutes should equal (Array("C-E-B-C","C-D-C"))
  }


  test("given exact step 4,then search all valid sequence") {
    val strartRoute = service.getStation("A")
    val endRoute = service.getStation("C")
    val controler = new ExactStepController(strartRoute.name, endRoute.name, 4)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(strartRoute)
    controler.getValidRoutes should equal (Array("A-B-C-D-C","A-D-E-B-C","A-D-C-D-C"))
  }


  test("given route A-C,then search shortest length of sequence will be A-B-C") {
    val startRoute = service.getStation("A")
    val endRoute = service.getStation("C")
    val controler = new ShortestDistanceController(startRoute.name, endRoute.name)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    controler.getValidRoutes should equal (Array("A-B-C"))
    controler.getControllingDistance shouldBe 9
  }


  test("given route B-B,search shortest length of sequence") {
    val startRoute = service.getStation("B")
    val endRoute = service.getStation("B")
    val controler = new ShortestDistanceController(startRoute.name, endRoute.name)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)

    controler.getValidRoutes should equal (Array("B-C-E-B"))
    controler.getControllingDistance shouldBe 9
  }

  test("given route C-C,search all routes which distance is less than 30,then output the number of routes") {
    val startRoute = service.getStation("C")
    val endRoute = service.getStation("C")
    val controler = new LessThanDistanceController(startRoute.name, endRoute.name, 30)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
//    println(controler.getValidRoutes.mkString(","))
    controler.getValidRoutes.size shouldBe 7
  }
}
