package mavenscala

import mavenscala.Controller.{DepthController, ExactStepController, LessThanDistanceController, ShortestDistanceController}
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Administrator on 2016/4/23 0023.
  */

class RouteDeepSearcherTest extends FunSuite with Matchers {
  val service = new RailroadService
  service.addRouteEdge("AB5")
  service.addRouteEdge("BC4")
  service.addRouteEdge("CD8")
  service.addRouteEdge("DC8")
  service.addRouteEdge("DE6")
  service.addRouteEdge("AD5")
  service.addRouteEdge("CE2")
  service.addRouteEdge("EB3")
  service.addRouteEdge("AE7")


  test("testDfs") {
    val strartRoute = service.getRoute("C")
    val endRoute = service.getRoute("C")

    val controler = new DepthController(strartRoute.routeName, endRoute.routeName, 3)

    val searcher = new RouteDeepSearcher(controler)
    searcher.search(strartRoute)
    println(controler.getAllSeq.mkString(","))
  }


  test("given exact step 4,search all valid sequence") {
    val strartRoute = service.getRoute("A")
    val endRoute = service.getRoute("C")
    val controler = new ExactStepController(strartRoute.routeName, endRoute.routeName, 4)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(strartRoute)
    println(controler.getAllSeq.mkString(","))
  }


  test("given route A-C,search shortest length of sequence") {
    val startRoute = service.getRoute("A")
    val endRoute = service.getRoute("C")
    val controler = new ShortestDistanceController(startRoute.routeName, endRoute.routeName)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    println(controler.getAllSeq.mkString(","))
    controler.getControllingDistance shouldBe 9
  }


  test("given route B-B,search shortest length of sequence") {
    val startRoute = service.getRoute("B")
    val endRoute = service.getRoute("B")
    val controler = new ShortestDistanceController(startRoute.routeName, endRoute.routeName)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    println(controler.getAllSeq.mkString(","))
    controler.getControllingDistance shouldBe 9
  }

  test("given route C-C,search all routes which distance is less than 30,then output the number of routes") {
    val startRoute = service.getRoute("C")
    val endRoute = service.getRoute("C")
    val controler = new LessThanDistanceController(startRoute.routeName, endRoute.routeName, 30)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    println(controler.getAllSeq.mkString(","))
    controler.getAllSeq.size shouldBe 7
  }
}
