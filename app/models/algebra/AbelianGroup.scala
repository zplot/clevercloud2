package models.algebra

trait AbelianGroup {

  type T1
  type T2 <: AbelianGroupElement

  def builder(x: T1): T2

  val structureId: String
  val zero: T2


  override def toString: String = structureId

  trait AbelianGroupElement {

    val fatherAbelianGroup = AbelianGroup.this
    val isZero: Boolean

    def neg: T2
    def add(other: T2): T2
    def +(other: T2): T2 = this.add(other)
    def minus(other: T2): T2
    def -(other: T2): T2 = this.minus(other)




    override def toString: String
  }
}

