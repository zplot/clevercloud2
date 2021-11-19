package models.algebra



case class PolynomialGraph(prime: Int, polynomial: PolynomialsOverFp ) {
  implicit val p: Int = prime
  implicit val field: Fp = Fp(p)
  val nodes: List[Node] = (0 until p).toList.map(x => Node(x))
  val edges: List[Edge] = (0 until p).toList.map(x => Edge(Node(x), Node(x)))





}