package models.algebra

import scala.annotation.tailrec
import scala.language.implicitConversions

object Rational {
  val zero = new Rational(0, 1)
  val one = new Rational(1, 1)

  def apply(x: Int): Rational = Rational(x, 1)

  implicit def convert(x: Int): Rational = Rational(x)

}

case class Rational(n: Int, d: Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer: Int = (if (d < 0) -n else n) / g
  val denom: Int = d.abs / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def + (i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (i: Int): Rational =
    Rational(numer - i * denom, denom)

  def neg : Rational =
    Rational( -numer, denom)

  def * (that: Rational): Rational =
    Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational =
    Rational(numer * i, denom)

  def / (that: Rational): Rational =
    Rational(numer * that.denom, denom * that.numer)

  def / (i: Int): Rational =
    Rational(numer, denom * i)

  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def equals(other: Any): Boolean =
    other match {

      case that: Rational =>
        (that canEqual this) &&
          numer == that.numer &&
          denom == that.denom

      case _ => false
    }

  override def toString: String =
    if (denom == 1) numer.toString else numer.toString +"/"+ denom.toString
}