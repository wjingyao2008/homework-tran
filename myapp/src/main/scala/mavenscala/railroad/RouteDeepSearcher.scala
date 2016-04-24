package mavenscala.railroad

import mavenscala.Controller.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class RouteDeepSearcher(controler: Controller) {


  def search(currentStation: Station): Unit = {
    if (!controler.keepTravel) {
      controler.moveBack()
      return
    }
    for (edge <- currentStation.allConnectedRoute.values) {
      controler.moveToNext(edge)
      search(edge.toStation)
    }
    controler.moveBack()
  }
}


