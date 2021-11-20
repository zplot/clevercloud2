package models.algebra

object PolynomialGraph {

}

case class PolynomialGraph(prime: Int, polynomialMap: Map[Int, Int]) {

  val p: Int = prime
  val cuerpo: Fp = Fp(p)
  val GFdeX: PolynomialsOverFp = PolynomialsOverFp(cuerpo)
  def util(x: Int): GFdeX.field.T2 = GFdeX.field.builder(x)
  val polynomial: GFdeX.T2 = GFdeX.builder(polynomialMap.map(x => (x._1, util(x._2))))

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



