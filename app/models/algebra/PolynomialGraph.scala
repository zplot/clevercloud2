package models.algebra

import models.algebra.PolynomialGraph.GFdeX

case class PolyGraph(prime: Int, polynomial: GFdeX.T2) {

  val p: Int = prime

  case class NodeFp(x: GFdeX.field.T2) extends Node[GFdeX.field.T2]
  case class EdgeFp(n1: NodeFp, n2: NodeFp) extends Edge[GFdeX.field.T2]

  def evalPoly(x: GFdeX.field.T2): GFdeX.field.T2 = polynomial.evaluate(x)

  val nodes: List[NodeFp] = (0 until p).toList.map(x => NodeFp(GFdeX.field.builder(x)))
  val edges: List[EdgeFp] = (0 until p).toList.map(x => EdgeFp(NodeFp(GFdeX.field.builder(x)), NodeFp(evalPoly(GFdeX.field.builder(x)))))

}

object PolynomialGraph extends App {

  val p = 5
  val cuerpo: Fp = Fp(p)

  val GFdeX: PolynomialsOverFp = PolynomialsOverFp(cuerpo)

  val prueba: GFdeX.field.T2 = GFdeX.field.builder(7)

  def util(x: Int): GFdeX.field.T2 = GFdeX.field.builder(x)

  val f: GFdeX.T2 = GFdeX.builder(Map(
    2 -> util(1))
  )

  println(PolyGraph(5, f))
  println(PolyGraph(5, f).nodes)
  println(PolyGraph(5, f).edges)





}

