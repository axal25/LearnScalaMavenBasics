package org.exercises.scala.ool.arith.ration

import org.exercises.scala.lib.standard.UnimplementedCaseException

trait IRational {
  def getNumerator(): Int

  def getDenominator(): Int

  def add(other: IRational): IRational = iRational.add(this, other)

  def sub(other: IRational): IRational = iRational.sub(this, other)

  def mul(other: IRational): IRational = iRational.mul(this, other)

  def div(other: IRational): IRational = iRational.div(this, other)

  def +(other: IRational): IRational = this.add(other)

  def -(other: IRational): IRational = this.sub(other)

  def *(other: IRational): IRational = this.mul(other)

  def /(other: IRational): IRational = this.div(other)

  override def toString(): String = iRational.toString(this)

  def toStringInterpretation(): String = iRational.toStringInterpretation(this)

  override def equals(obj: Any): Boolean = obj match {
    case other: IRational => this.getNumerator.equals(other.getNumerator) && this.getDenominator.equals(other.getDenominator)
    case _ => false
  }
}

object iRational {
  def tellTheStoryOfConstructor(): Unit = {
    println("Definition of class combining numerator and denominator of a rational number")
    println("class Rational(x: Int, y: Int) {")
    println("\tdef numerator = x")
    println("\tdef denominator = y")
    println("}")
    println("This definition creates new type named Rational")
    println("And creates a constructor for it Rational(x: Int, y: Int)")
    println("The type and values are kept in different namespaces")
    println("There is no conflict between two definitions of Rational")
  }

  def +(first: IRational, second: IRational): IRational = iRational.add(first, second)

  def -(first: IRational, second: IRational): IRational = iRational.sub(first, second)

  def *(first: IRational, second: IRational): IRational = iRational.mul(first, second)

  def /(first: IRational, second: IRational): IRational = iRational.div(first, second)

  def add(first: IRational, second: IRational): IRational = {
    constructorImpl(
      first.getNumerator * second.getDenominator + first.getDenominator * second.getNumerator,
      first.getDenominator * second.getDenominator,
      first,
      second
    )
  }

  def mul(first: IRational, int: Int): IRational = {
    constructorImpl(
      int * first.getNumerator,
      first.getDenominator,
      first
    )
  }

  def sub(first: IRational, second: IRational): IRational = {
    add(
      first,
      iRational.mul(second, -1)
    )
  }

  def mul(first: IRational, second: IRational): IRational = {
    constructorImpl(
      first.getNumerator * second.getNumerator,
      first.getDenominator * second.getDenominator,
      first,
      second
    )
  }

  def div(first: IRational, second: IRational): IRational = {
    constructorImpl(
      first.getNumerator * second.getDenominator,
      first.getDenominator * second.getNumerator,
      first,
      second
    )
  }

  def reduce(irational: IRational): IRational = {
    val gcd = greatestCommonDivisor(irational.getNumerator, irational.getDenominator)
    val numerator = irational.getNumerator / gcd
    val denominator = irational.getDenominator / gcd
    constructorImpl(numerator, denominator, irational)
  }

  def constructorImpl(numerator: Int, denominator: Int, first: IRational, irationals: IRational*): IRational = {
    println(s"first class: ${first.getClass.getSimpleName}, irationals class: ${irationals.getClass.getSimpleName}")

    @scala.annotation.tailrec
    def isSeqElementsOfTypeSameAsFirst(first: Any, irationals: Any*): Boolean = irationals match {
      case Seq() => true
      case Seq(head, tail@_*) => {
        if (first.getClass == head.getClass) isSeqElementsOfTypeSameAsFirst(first, tail: _*)
        else false
      }
      case _ => false
    }

    if (isSeqElementsOfTypeSameAsFirst(first, irationals: _*)) {
      first match {
        case rational: Rational => new Rational(numerator, denominator)
        case abstraction: RationalAbstraction => new RationalAbstraction(numerator, denominator)
        case _ => throw new UnimplementedCaseException(this, "constructorImpl", first +: irationals: _*)
      }
    }
    else throw new MixingIRationalImplementationException(first, irationals: _*)
  }

  def greatestCommonDivisor(first: Int, second: Int): Int = {
    @scala.annotation.tailrec
    def iter(bigger: Int, smaller: Int): Int = {
      val div = bigger / smaller
      val mod = bigger % smaller
      if (mod == 0) smaller
      else iter(smaller, mod)
    }

    def abs(int: Int): Int = {
      if (int < 0) -int
      else int
    }

    if (first == 0) second
    else if (second == 0) first
    else {
      val absFirst = abs(first)
      val absSecond = abs(second)
      if (absSecond > absFirst) iter(absSecond, absFirst)
      else iter(absFirst, absSecond)
    }
  }

  def toString(irational: IRational): String = {
    s"${irational.getClass.getSimpleName}{ numerator: ${irational.getNumerator}, denominator: ${irational.getDenominator}}"
  }

  def toStringInterpretation(irational: IRational): String = {
    s"${irational.getNumerator} / ${irational.getDenominator}"
  }
}
