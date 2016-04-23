package mavenscala

import mavenscala.Controller.Controller

/**
  * Created by Administrator on 2016/4/23 0023.
  */
class RouteDeepSearcher(controler: Controller) {


  def search(currentRoute: Route): Unit = {
    if (!controler.keepGoingDown) {
      controler.moveBack()
      return
    }
    for(edge<-currentRoute.allConnectedRoute.values){
      controler.moveToNextRoute(edge)
      search(edge.toRoute)
    }
    controler.moveBack()
  }
}


