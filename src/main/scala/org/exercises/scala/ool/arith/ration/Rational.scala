package org.exercises.scala.ool.arith.ration

class Rational(x: Int, y: Int) extends IRational {
  def numerator: Int = x

  def denominator: Int = y

  def apply(x: Int, y: Int): Rational = this (x, y)

  override def getNumerator(): Int = this.numerator

  override def getDenominator(): Int = this.denominator

  def reduce(): Rational = {
    val reduced = iRational.reduce(this)
    this (reduced.getNumerator, reduced.getDenominator)
  }
}
