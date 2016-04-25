package mavenscala.railroad

import org.scalatest.FunSuite

/**
  * Created by y28yang on 4/25/2016.
  */
class RouteTest extends FunSuite {

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


  test("testBeforeTravelTo") {

  }

  test("testTravelTo") {

  }

  test("testKeepGoing") {

  }

}
