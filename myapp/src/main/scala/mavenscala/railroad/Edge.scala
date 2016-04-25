package mavenscala.railroad

/**
  * Created by Administrator on 2016/4/23 0023.
  */
case class Edge(toStation: Station,distance: Int){
  override def toString: String ={
    val name=toStation.name
    s"[$name,$distance]"
  }
}
