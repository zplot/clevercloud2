// Number Fields

package models.algebra

case class NF(m: Int) extends Field {
  type T1 = (Rational, Rational)
  type T2 = NFE
  def builder(x: T1): NFE = NFE(x)
  val structureId: String = "Q[√" + "m" + "]"
  val zero: NFE = builder((Rational.zero, Rational.zero))
  override val one: NFE = builder((Rational.one, Rational.zero))
  val finite = false
  val identity: NFE = one

  object NFE {
    def apply(k: T1): NFE = {
      new NFE(k)
    }
  }

  class NFE(val k: T1) extends FieldElement {
    val a: Rational = k._1
    val b: Rational = k._2
    val elementId: String = k.toString()
    val fatherQuadraticField: NF = NF.this
    val isZero: Boolean = k == (0, 0)
    def add(other: NFE): NFE =
      NFE(a + other.a, b + other.b)
    def minus(other: NFE): NFE =
      NFE(a.neg, b.neg)
    def multiply(other: NFE): NFE = {
      val mm = Rational(m, 1)
      val tmp1: Rational = a * other.a + mm * (b * other.b)
      val tmp2: Rational = a * other.b + b * other.a
      NFE(tmp1, tmp2)
    }
    def neg: NFE = zero - this
    def inverse: NFE = {
      val denom = a * a - b * b * m
      NFE((a / denom, b.neg / denom))
    }

    def N: Rational = this.a * this.a - Rational(m, 1) * (this.b * this.b)

    override def toString: String = a.toString + " + " + b.toString + "√" + m.toString


    final override def equals(other: Any): Boolean = {
      val that = other.asInstanceOf[NFE]
      if (that == null) false
      else this.k == that.k
    }
  }
}