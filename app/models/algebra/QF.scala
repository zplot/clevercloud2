// Quadratic Fields

package models.algebra

case class QF(m: Int) extends Field {
  type T1 = (Rational, Rational)
  type T2 = QFE
  def builder(x: T1): QFE = QFE(x)
  val structureId: String = "Q[√" + "m" + "]"
  val zero: QFE = builder((Rational.zero, Rational.zero))
  override val one: QFE = builder((Rational.one, Rational.zero))
  val finite = false
  val identity: QFE = one

  object QFE {
    def apply(k: T1): QFE = {
      new QFE(k)
    }
  }

  class QFE(val k: T1) extends FieldElement {
    val a: Rational = k._1
    val b: Rational = k._2
    val elementId: String = k.toString()
    val fatherQuadraticField: QF = QF.this
    val isZero: Boolean = k == (0, 0)
    def add(other: QFE): QFE =
      QFE(a + other.a, b + other.b)
    def minus(other: QFE): QFE =
      QFE(a.neg, b.neg)
    def multiply(other: QFE): QFE = {
      val mm = Rational(m, 1)
      val tmp1: Rational = a * other.a + mm * (b * other.b)
      val tmp2: Rational = a * other.b + b * other.a
      QFE(tmp1, tmp2)
    }
    def neg: QFE = zero - this
    def inverse: QFE = {
      val denom = a * a - b * b * m
      QFE((a / denom, b.neg / denom))
    }

    def N: Rational = this.a * this.a - (this.b * this.b) * m

    override def toString: String = a.toString + " + " + b.toString + "√" + m.toString


    final override def equals(other: Any): Boolean = {
      val that = other.asInstanceOf[QFE]
      if (that == null) false
      else this.k == that.k
    }
  }
}
