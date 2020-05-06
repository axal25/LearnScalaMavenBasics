package org.exercises.scala.evals.defs

import scala.annotation.showAsInfix

object DefinitionsAndEvaluations {
  val pi: Double = 3.14159

  def main(): Unit = {
    discAreaExplanation()
    methodExplanation()
    substitutionModel()
    evaluationStrategy()
  }

  def discAreaExplanation(): Unit = {
    val discArea = 3.14159 * 10 * 10
    val radius = 10
    val pi = 3.14159
    assert(pi * radius * radius == discArea)
    assert(3.14159 * radius * radius == discArea)
    assert(3.14159 * 10 * radius == discArea)
    assert(31.4159 * radius == discArea)
    assert(31.4159 * 10 == discArea)
    assert(314.159 == discArea)
  }

  def methodExplanation(): Unit = {
    assert(getSquare(3) == 9)

    assert(getDiscArea(10) == 314.159)

    assert(getSimplePower(3, 2) == 9)
    assert(getSimplePower(3, 3) == 27)
    assert((getSimplePower(3, -3) - 1.0 / 27).abs < 0.00001)
  }

  def substitutionModel(): Unit = {
    val sumOfSquares = (3 * 3) + ((2 + 2) * (2 + 2))
    assert(getSumOfSquares(3, 2 + 2) == sumOfSquares)
    assert(getSumOfSquares(3, 4) == sumOfSquares)
    assert(getSquare(3) + getSquare(4) == sumOfSquares)
    assert(3 * 3 + getSquare(4) == sumOfSquares)
    assert(9 + getSquare(4) == sumOfSquares)
    assert(9 + 4 * 4 == sumOfSquares)
    assert(9 + 16 == sumOfSquares)
    assert(25 == sumOfSquares)

    println("\r\nexpression evaluation - substitution model")
    println("reduce expression to a value")
    println("can be applied to all expressions (if they have no side effects)")
    println("substitution model is formalized in lambda-calculus")
    println("lambda-calculus is foundation for functional programming")
    println("of course there are loops (expressions) that do not reduce to value (in finite number of steps)")
    println("\t\tdef infiniteLoopyLoop: Int = infiniteLoopyLoop")

    def infiniteLoopyLoop: Int = infiniteLoopyLoop

    def willNotAllowRestOfTheProgramToRun(): Unit = {
      val anInt: Int = infiniteLoopyLoop
    }
  }

  def evaluationStrategy(): Unit = {
    println("\r\nevaluation strategies: call-by-value vs. call-by-name")
    println("call-by-value - previous illustrated in substitutionModel()")
    println("call-by-name - illustrated here")
    val sumOfSquares = (3 * 3) + ((2 + 2) * (2 + 2))
    assert(getSumOfSquares(3, 2 + 2) == sumOfSquares)
    assert( (getSquare(3) + getSquare(2 + 2) ) == sumOfSquares)
    assert( ((3 * 3) + getSquare(2 + 2) ) == sumOfSquares)
    assert( (9 + getSquare(2 + 2) ) == sumOfSquares)
    assert( ( 9 + ((2 + 2) * (2 + 2)) ) == sumOfSquares)
    assert( ( 9 + (4 * (2 + 2)) ) == sumOfSquares)
    assert(9 + (4 * 4) == sumOfSquares)
    assert(9 + 16 == sumOfSquares)
    assert(25 == sumOfSquares)
    println("both strategies reduce to the same final value as long as:")
    println("\t\t1) the reduced expression consists of pure functions")
    println("\t\t2) both evaluations terminate")
    println("call-by-value +++ evaluates every (function) argument only once")
    println("call-by-name +++ does not evaluate parameter that is not used in the evaluation fo the function body")
    println("Scala usually uses call-by-value")
  }

  def triangleAreaExercise(): Unit = {
    assert(getTriangleArea(3, 4) == 6.0)
    assert(getTriangleArea(5, 6) == 15)
  }

  def getDiscArea(radius: Double): Double = pi * getSquare(radius)

  def getSquare(x: Double): Double = x * x

  def getSimplePower(x: Double, y: Int): Double = {
    if (y == 0) 1
    else if (y > 0) x * getSimplePower(x, y - 1)
    else 1 / getSimplePower(x, -y)
  }

  def getSumOfSquares(x: Double, y: Double): Double = getSquare(x) + getSquare(y)

  def getTriangleArea(base: Double, height: Double): Double = base * height / 2

  //  def getDiscArea(Radius: Double): Unit = pi
  //
  //  def pow(x: Double, n: Int): Option[Double] = n match {
  //    case 0 => Some(0)
  //    case 1 => Some(1)
  //    case _ => calcPow(x, n)
  //  }
  //
  //  def calcPow(x: Double, n: Int): Option[Double] = n match {
  //    case 0 => Some(1)
  //    case n % 2 == 0 => {
  //      val newN: Double = n
  //      square(calcPow(x, n))
  //    }
  //    case _ => 0
  //  }
  //
  //  def square(x: Option[Double]): Option[Double] = Some(x) * Some(x)
}


