import models.algebra._

object TestPolyGraph extends App {

  val f = Map(2 -> 1)
  val p = 11
  println(PolynomialGraph(p, f))
  println(PolynomialGraph(p, f).nodes)
  println(PolynomialGraph(p, f).edges)

}

