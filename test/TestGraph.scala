import models.algebra._

object TestGraph {

  println("Empezamos")

  val cuerpo: Fp = Fp(43)
  val a = cuerpo.builder(1520)
  val b = cuerpo.builder(7870)


  println(a.minus(b))
  println(a*b)
  println(a.inverse*a)

  println(cuerpo.gcd(a, b))

  val p = 5
  val field = Fp(p)

}
