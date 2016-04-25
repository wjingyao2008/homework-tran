package mavenscala.search

import mavenscala.railroad.{Edge, Station}

import scala.collection.mutable.{ArrayBuffer, Stack}

/**
  * Created by y28yang on 4/25/2016.
  */
class RouteIterator(val rootStation: Station) {
  private var currentEdges = new ArrayBuffer[Edge]
  private val stacks = new Stack[Iterator[Edge]]

  stacks.push(rootStation.allConnectedRoute.valuesIterator)

  def hasNext: Boolean = {
    if (stacks.isEmpty) return false
    if (stacks.top.hasNext) return true
    stacks.pop()
    if (currentEdges.nonEmpty)
      currentEdges.trimEnd(1)
    hasNext
  }


  def next: Seq[Edge] = {
    val edge = stacks.top.next()
    currentEdges.append(edge)
    stacks.push(edge.toStation.allConnectedRoute.valuesIterator)
    currentEdges
  }

  def dropCurrentStation = {
    stacks.pop
    if (currentEdges.nonEmpty)
      currentEdges.trimEnd(1)
  }


}
