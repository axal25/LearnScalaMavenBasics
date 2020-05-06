package org.exercises.scala.func.loops

import scala.annotation.tailrec

object FunctionalLoops {
  val e = getNaturalLogBase()
  val maxApproxErrorAllowed = 0.0000000000001;

  def main(): Unit = {
    conditionalExpressions()
    booleanExpressions()
    methodImplementation()
    semicolonsInfixOperatorsMultipleLineOperations()
  }

  def conditionalExpressions(): Unit = {
    println("\r\nconditional expression == if-else")
    println("it is expression - not statement like in Java")
    println("\t\tdef abs(x: Double): Double = if (x >= 0) x else -x")
    println("(x >= 0) is PREDICATE of type Boolean")
    println("abs(-44): " + abs(-44))
    println("abs(66): " + abs(66))
  }

  def booleanExpressions(): Unit = {
    println("\r\nboolean expressions composition")
    val b1 = true
    val b2 = false
    val b3 = !b2
    val b4 = b1 && b3
    val b5 = b1 || b2
    assert(b1 == true, "true == true")
    assert(b2 == false, "false == false")
    assert(b3 == true, "(!false) == true")
    assert(b4 == true, "(true && !false) == true")
    assert(b5 == true, "(true || false) == true")

    val e = FunctionalLoops.e
    val eTailRec = getNaturalLogBaseTailRec()
    println("e: " + e)
    println("eTailRec: " + eTailRec)
    assert(e == e, "e == e")
    assert(e == eTailRec, "e == eTailRec")
    println("e - 2.718281828459: " + (e - 2.718281828459))
    assert(e - 2.718281828459 < 0.0000000035277, "e - 2.718281828459 < 0.0000000035277")
    val b6 = e == e
    val b7 = e != e
    val b8 = e < e
    val b9 = e > e
    val b10 = e <= e
    val b11 = e >= e
    assert(b6 == true, "(e == e) == true")
    assert(b7 == false, "(e != e) == false")
    assert(b8 == false, "(e < e) == false")
    assert(b8 == false, "(e < e) == false")
    assert(b9 == false, "(e > e) == false")
    assert(b10 == true, "(e <= e) == false")
    assert(b11 == true, "(e >= e) == false")

    println("boolean expression reduction rules")
    assert((!true) == false, "!true == false")
    assert((!false) == true, "!false == true")
    assert((true && b1) == b1, "(true && b1) == b1 // b1 == true")
    assert((true && b2) == b2, "(true && b2) == b1 // b2 == false")
    assert((false && b1) == false, "(false && b1) == false // b1 == true")
    assert((false && b2) == false, "(false && b2) == false // b2 == false")
    assert((true || b1) == true, "(true || b1) == true")
    assert((true || b2) == true, "(true || b2) == true")
    assert((false || b1) == b1, "(false || b1) == b1")
    assert((false || b2) == b2, "(false || b2) == b2")
  }

  def methodImplementation(): Unit = {
    val numberToSqrt = 2
    val sqrt2 = sqrt(numberToSqrt);
    val sqrt2Msg = s"sqrt($numberToSqrt): "
    val flatSqrt2 = "1.414213562373095048801688724209698078569671875376948073176"
    val flatSqrt2Msg = "flat square root of 2 (according to Wolframalpha.com): "
    println(f"${sqrt2Msg}%60s$sqrt2%e50")
    println(f"${sqrt2Msg}%60s$sqrt2%f50")
    println(f"$sqrt2Msg%60s$sqrt2%s50")
    println(f"$flatSqrt2Msg%60s$flatSqrt2%s50")
    assert(
      abs(sqrt2 * sqrt2 - numberToSqrt) < FunctionalLoops.maxApproxErrorAllowed,
      "abs(sqrt2 * sqrt2 - numberToSqrt) < FunctionalLoops.maxApproxErrorAllowed"
    )

    assert(factorial(0) == 1, "factorial(0) == 1")
    assert(factorial(1) == 1, "factorial(1) == 1")
    assert(factorial(2) == 2, "factorial(2) == 2")
    assert(factorial(3) == 6, "factorial(3) == 6")
    assert(factorial(4) == 24, "factorial(4) == 24")
  }

  def semicolonsInfixOperatorsMultipleLineOperations(): Unit = {
    println("lines can end with semicolon but do not have to")
    val x1 = 1
    val x2 = 2;

    println("lines with multiple statement in the same line have to be separated by semicolon")
    val y1 = x1 + 1; val y2 = y1 * y1
    val y3 = x2 + 1
    val y4 = y2 * y2

    val someVeryLongExpression = 1
    val svle1 = someVeryLongExpression
    + someVeryLongExpression
    assert(svle1 == someVeryLongExpression, "svle1 == someVeryLongExpression")

    val svle2 = someVeryLongExpression;
    + someVeryLongExpression
    assert(svle2 == someVeryLongExpression, "svle2 == someVeryLongExpression")
    assert(svle2 == svle1, "svle2 == svle1")

    println("it is possible to continue expression in next line by using parentheses: \n\"(\n \tbegin_expression\n \tcontinue_expression\n \tend_expression\n)\" ")

    val svle3 = (someVeryLongExpression
    + someVeryLongExpression)
    assert(svle3 == someVeryLongExpression + someVeryLongExpression, "svle3 == someVeryLongExpression + someVeryLongExpression")

    println("it is possible to continue expression in next line by leaving operator at the end of line: \nbegin_expression +\n \tcontinue_expression")

    val svle4 = someVeryLongExpression +
      someVeryLongExpression
    assert(svle4 == someVeryLongExpression + someVeryLongExpression, "svle4 == someVeryLongExpression + someVeryLongExpression")
  }

  def abs(x: Double): Double = if (x >= 0) x else -x

  def getNaturalLogBase(): Double = {
    def getNaturalLogBaseForPrecisionDepth(precisionDepth: Int): Double = {
      getNaturalLogBaseIter(0, 1, precisionDepth)
    }

    def getNaturalLogBaseIter(prevDepth: Int, prevFactorial: Int, precisionDepth: Int): Double = prevDepth match {
      case `precisionDepth` => 1
      case _ => {
        val currentDepth = prevDepth + 1
        val currentFactorial = prevFactorial * currentDepth
        getNaturalLogBaseIter(currentDepth, currentFactorial, precisionDepth) + (1.0 / currentFactorial)
      }
    }

    getNaturalLogBaseForPrecisionDepth(33)
  }

  def getNaturalLogBaseTailRec(): Double = {
    def getNaturalLogBaseForPrecisionDepthTailRec(precisionDepth: Int): Double = {
      getNaturalLogBaseIterTailRec(0, 1, precisionDepth, 0)
    }

    @tailrec
    def getNaturalLogBaseIterTailRec(prevDepth: Int, prevFactorial: Int, precisionDepth: Int, curAprox: Double): Double = prevDepth match {
      case `precisionDepth` => curAprox + 1 // curAprox + (1.0 / 1) // assert(currentFactorial == 1)
      case _ => {
        val currentDepth = prevDepth + 1
        val currentFactorial = prevFactorial * currentDepth
        getNaturalLogBaseIterTailRec(currentDepth, currentFactorial, precisionDepth, curAprox + (1.0 / currentFactorial))
      }
    }

    getNaturalLogBaseForPrecisionDepthTailRec(33)
  }

  def sqrt(numberToSqrt: Double): Double = {

    @tailrec
    def sqrtIter(numberToSqrt: Double, prevApprox: Double): Double = {
      isSqrtApproxAcceptable(numberToSqrt, prevApprox) match {
        case true => prevApprox
        case _ => {
          val curApprox = (numberToSqrt / prevApprox + prevApprox) / 2
          sqrtIter(numberToSqrt, curApprox)
        }
      }
    }


    def isSqrtApproxAcceptable(numberToSqrt: Double, sqrtApprox: Double): Boolean = {
      val numberApproxAndNumberAbsDiff = abs(sqrtApprox * sqrtApprox - numberToSqrt)
      numberApproxAndNumberAbsDiff < FunctionalLoops.maxApproxErrorAllowed
    }

    sqrtIter(numberToSqrt, 1)
  }

  def factorial(n: Int): Int = {
    if (n < 0) throw new Exception("Not able to calculate factorial for negative numbers. N: " + n)
    else {
      n match {
        case 0 => 1
        case _ => factorial(n - 1) * n
      }
    }
  }
}
