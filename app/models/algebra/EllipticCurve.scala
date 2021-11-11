package models.algebra

object EllipticCurve {
  def apply(a4: Rational, a6: Rational): EllipticCurve = EllipticCurve(0, 0, 0, a4, 0, a6)
  def apply(a4: Int, a6: Int): EllipticCurve = EllipticCurve(0, 0, 0, a4, 0, a6)
}

case class EllipticCurve(a1: Rational, a2: Rational, a3: Rational, a4: Rational, a5: Rational, a6: Rational) {

}
