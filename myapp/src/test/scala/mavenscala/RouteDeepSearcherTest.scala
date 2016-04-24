package mavenscala

import mavenscala.Controller.{DepthController, ExactStepController, LessThanDistanceController, ShortestDistanceController}
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


  test("testDfs") {
    val strartRoute = service.getStation("C")
    val endRoute = service.getStation("C")

    val controler = new DepthController(strartRoute.name, endRoute.name, 3)

    val searcher = new RouteDeepSearcher(controler)
    searcher.search(strartRoute)
    println(controler.getAllSeq.mkString(","))
  }


  test("given exact step 4,search all valid sequence") {
    val strartRoute = service.getStation("A")
    val endRoute = service.getStation("C")
    val controler = new ExactStepController(strartRoute.name, endRoute.name, 4)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(strartRoute)
    println(controler.getAllSeq.mkString(","))
  }


  test("given route A-C,search shortest length of sequence") {
    val startRoute = service.getStation("A")
    val endRoute = service.getStation("C")
    val controler = new ShortestDistanceController(startRoute.name, endRoute.name)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    println(controler.getAllSeq.mkString(","))
    controler.getControllingDistance shouldBe 9
  }


  test("given route B-B,search shortest length of sequence") {
    val startRoute = service.getStation("B")
    val endRoute = service.getStation("B")
    val controler = new ShortestDistanceController(startRoute.name, endRoute.name)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    println(controler.getAllSeq.mkString(","))
    controler.getControllingDistance shouldBe 9
  }

  test("given route C-C,search all routes which distance is less than 30,then output the number of routes") {
    val startRoute = service.getStation("C")
    val endRoute = service.getStation("C")
    val controler = new LessThanDistanceController(startRoute.name, endRoute.name, 30)
    val searcher = new RouteDeepSearcher(controler)
    searcher.search(startRoute)
    println(controler.getAllSeq.mkString(","))
    controler.getAllSeq.size shouldBe 7
  }
}
