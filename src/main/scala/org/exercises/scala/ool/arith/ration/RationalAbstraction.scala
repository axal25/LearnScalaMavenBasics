package org.exercises.scala.ool.arith.ration

class RationalAbstraction(x: Int, y: Int) extends IRational {
  private val gcd = iRational.greatestCommonDivisor(x, y)
  val numerator = x / gcd
  val denominator = y / gcd

  def apply(x: Int, y: Int): RationalAbstraction = this (x, y)

  override def getNumerator(): Int = this.numerator

  override def getDenominator(): Int = this.denominator
}
