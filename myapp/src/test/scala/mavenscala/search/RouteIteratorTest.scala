package mavenscala.search

import mavenscala.RailroadService
import mavenscala.railroad.Edge
import mavenscala.railroad.{SimpleStation, Station}
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by y28yang on 4/25/2016.
  */
class RouteIteratorTest extends FunSuite with Matchers{

  val rootStation=new SimpleStation("root")
  val aStation=new SimpleStation("A")
  val b=new SimpleStation("B")
  val c=new SimpleStation("C")
  val d=new SimpleStation("D")

  rootStation.addRoute(aStation,0)
  rootStation.addRoute(b,0)
  rootStation.addRoute(c,0)
  rootStation.addRoute(d,0)

  aStation.addRoute(b,5)
  b.addRoute(c,4)
  aStation.addRoute(d,6)

  test("given the route,all route A,A-B,A-B-C,A-D can be iterated") {

    val iterator=new RouteIterator(rootStation)
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B-C"
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-D"

  }

  def toString(iterator: RouteIterator)=iterator.next.map(_.toStation.name).mkString("-")

  test("two branch,can verify A-B,A-C,A,B,C") {
    val service = new RailroadService
    service.addRoute("AB5")
    service.addRoute("AC4")

    val iterator=new RouteIterator(service.rootStation)

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-C"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "C"

    iterator.hasNext shouldBe false
  }


  def printAll(rootStation:Station)={
    val iterator=new RouteIterator(rootStation)
    while(iterator.hasNext)
      {
        println(toString(iterator))
      }
  }


  test("two branch A-B,A-C-D,can be iterated") {
    val service = new RailroadService
    service.addRoute("AB5")
    service.addRoute("AC4")
    service.addRoute("CD4")

    val iterator=new RouteIterator(service.rootStation)
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-C"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-C-D"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "C"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "C-D"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "D"

    iterator.hasNext shouldBe false
  }


  test("two branch A-B-C and A-C,drop at A-B,then other route can be found") {
    val service = new RailroadService
    service.addRoute("AB5")
    service.addRoute("AC4")
    service.addRoute("BC4")

    val iterator=new RouteIterator(service.rootStation)
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"

    iterator.dropCurrentStation

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-C"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B-C"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "C"

    iterator.hasNext shouldBe false
  }

  test("two branch A-B,drop at A,then other route can be found") {
    val service = new RailroadService
    service.addRoute("AB5")

    val iterator=new RouteIterator(service.rootStation)

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.dropCurrentStation

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B"

    iterator.hasNext shouldBe false
  }

  test("two branch A-B,drop at A-B,then still have route 'B'") {
    val service = new RailroadService
    service.addRoute("AB5")

    val iterator=new RouteIterator(service.rootStation)

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"

    iterator.dropCurrentStation

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B"

    iterator.hasNext shouldBe false
  }



  test("test single root node,should has no next") {
    val rootStation = new SimpleStation("root")

    val iterator=new RouteIterator(rootStation)

    iterator.hasNext shouldBe false
  }



  test("test root-A,should has A") {
    val rootStation = new SimpleStation("root")
    val a = new SimpleStation("A")
    rootStation.addRoute(a,3)

    val iterator=new RouteIterator(rootStation)
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"
  }





  test("test A-B, drop at B, then there will be no route anymore") {
    val rootStation = new SimpleStation("root")
    val a = new SimpleStation("A")
    val b = new SimpleStation("B")
    rootStation.addRoute(a,3)
    rootStation.addRoute(b,3)
    a.addRoute(b,3)

    val iterator=new RouteIterator(rootStation)

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"

    iterator.dropCurrentStation
    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "B"

    iterator.hasNext shouldBe false
  }


  test("test A-B and A-C,when drop at B,then there will still have A-C") {
    val rootStation = new SimpleStation("root")
    val a = new SimpleStation("A")
    val b = new SimpleStation("B")
    val c = new SimpleStation("C")
    rootStation.addRoute(a,3)
    rootStation.addRoute(b,3)
    rootStation.addRoute(c,3)
    a.addRoute(b,4)
    a.addRoute(c,4)

    val iterator=new RouteIterator(rootStation)

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A"

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-B"
    iterator.dropCurrentStation

    iterator.hasNext shouldBe true
    toString(iterator) shouldBe "A-C"

  }



}
