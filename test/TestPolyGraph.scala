import models.algebra._

object TestPolyGraph extends App {

  val f = Map(2 -> 1)
  println(PolynomialGraph(5, f))
  println(PolynomialGraph(5, f).nodes)
  println(PolynomialGraph(5, f).edges)

}

