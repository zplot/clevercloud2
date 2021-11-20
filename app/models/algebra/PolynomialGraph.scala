package models.algebra

import models.algebra.PolynomialGraph.GFdeX

case class PolyGraph(prime: Int, polynomial: GFdeX.T2) {

  val p: Int = prime

  case class NodeFp(x: GFdeX.field.T2) extends Node[GFdeX.field.T2] {
    override def toString: String = x.toString
  }
  case class EdgeFp(n1: NodeFp, n2: NodeFp) extends Edge[GFdeX.field.T2] {
    override def toString: String = n1.toString + " --> " + n2.toString
  }

  def evalPoly(x: GFdeX.field.T2): GFdeX.field.T2 = polynomial.evaluate(x)

  val nodes: Set[NodeFp] = (0 until p).toList.map(x => NodeFp(GFdeX.field.builder(x))).toSet
  val edges: Set[EdgeFp] = (0 until p).toList.map(x => EdgeFp(NodeFp(GFdeX.field.builder(x)), NodeFp(evalPoly(GFdeX.field.builder(x))))).toSet

}

object PolynomialGraph extends App {

  val p = 11
  val cuerpo: Fp = Fp(p)

  val GFdeX: PolynomialsOverFp = PolynomialsOverFp(cuerpo)

  def util(x: Int): GFdeX.field.T2 = GFdeX.field.builder(x)

  val f: GFdeX.T2 = GFdeX.builder(Map(
    2 -> util(1))
  )

  println(PolyGraph(5, f))
  println(PolyGraph(5, f).nodes)
  println(PolyGraph(5, f).edges)





}

