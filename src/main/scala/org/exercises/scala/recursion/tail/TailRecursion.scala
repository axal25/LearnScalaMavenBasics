package org.exercises.scala.recursion.tail

import scala.annotation.tailrec

object TailRecursion {

  def main(): Unit = {
    recursiveMethodsEvaluation()
  }

  def recursiveMethodsEvaluation(): Unit = {
    println("\nRecursive Method Evaluation")
    greatestCommonDivisorEvaluation()
    factorialEvaluation()
    println("Tail recursion vs. normal recursion")
    println("Difference in writing expression evaluation rules => difference in ACTUAL evaluation (computer instruction execution)")
    println("Recursive method calling itself as last action => reusing method stack frame <=> TAIL RECURSION")
    println("Method's call for itself as last action => Method constant stack space => Iterative process")
    println("Tail recursive methods <=> functional form of a loop (executes as efficiently as loop)")

    println("\nfactorial(n: Int): Int - normal recursion")
    println("\ngreatestCommonDivisor(a: Int, b: Int): Int - tail recursion")
    println("factorialTailRec(n: Int): Int [USING: factorialTailRec(n: Int, prevRecResult: Int): Int ] - tail call [USING: tail recursion]")

    println("\ngreatestCommonDivisor:")
    println("calls itself as last action => rewrites sequence constant in size => actual execution: tail recursive call (executing in constant space)")

    println("\nfactorial (normal):")
    println("after call to factorial(n-1) there is \"* n\" operation left to be done (after evaluating another recursion of factorial method)")
    println("That is why this RECURSIVE call is NOT tail recursive.")
    println("There is a built-up of results we have to keep until computer evaluates final value (final call of factorial method).")
    println("[This can be noticed in reduction sequence shown in \"factorialEvaluation()\" method.")

    println("\nThere is generalization of TAIL RECURSION called TRAIL CALL.")
    println("It is calling some method (same or other) as last action.")
    println("This allows (just as in case of TAIL RECURSION) for reuse of stack frame - using same stack frame for both methods.")
  }

  def greatestCommonDivisorEvaluation(): Unit = {
    val gcd = greatestCommonDivisor(14, 21)
    assert(gcd == greatestCommonDivisor(14, 21), "gcd == greatestCommonDivisor(14, 21)")
    assert(
      gcd == (
        if (21 == 0) 14
        else greatestCommonDivisor(21, 14 % 21)
        ),
      "gcd == (\n        if (21 == 0) 14\n        else greatestCommonDivisor(21, 14 % 21)\n        )"
    )
    assert(
      gcd == (
        if (false) 14
        else greatestCommonDivisor(21, 14 % 21)
        ),
      "gcd == (\n        if (false) 14\n        else greatestCommonDivisor(21, 14 % 21)\n        )"
    )
    assert(
      gcd == greatestCommonDivisor(21, 14 % 21),
      "gcd == greatestCommonDivisor(21, 14 % 21)"
    )
    assert(
      gcd == greatestCommonDivisor(21, 14),
      "gcd == greatestCommonDivisor(21, 14)"
    )
    assert(
      gcd == (
        if (14 == 0) 21
        else greatestCommonDivisor(14, 21 % 14)
        ),
      "gcd == (\n      if (14 == 0) 21\n      else greatestCommonDivisor(14, 21 % 14)\n    )"
    )
    assert(
      gcd == (
        if (false) 21
        else greatestCommonDivisor(14, 21 % 14)
        ),
      "gcd == (\n      if (false) 21\n      else greatestCommonDivisor(14, 21 % 14)\n    )"
    )
    assert(
      gcd == greatestCommonDivisor(14, 21 % 14),
      "gcd == greatestCommonDivisor(14, 21 % 14)"
    )
    assert(
      gcd == greatestCommonDivisor(14, 7),
      "gcd == greatestCommonDivisor(14, 7)"
    )
    assert(
      gcd == greatestCommonDivisor(7, 14 % 7),
      "gcd == greatestCommonDivisor(7, 14 % 7)"
    )
    assert(
      gcd == greatestCommonDivisor(7, 0),
      "gcd == greatestCommonDivisor(7, 0)"
    )
    assert(
      gcd == (
        if (0 == 0) 7 else greatestCommonDivisor(0, 7 % 0)
        ),
      "gcd == (\n        if(0 == 0) 7 else greatestCommonDivisor(0, 7 % 0)\n      )"
    )
    assert(
      gcd == (
        if (true) 7 else greatestCommonDivisor(0, 7 % 0)
        ),
      "gcd == (\n        if(true) 7 else greatestCommonDivisor(0, 7 % 0)\n      )"
    )
    assert(gcd == 7, "gcd == 7")
  }

  def factorialEvaluation(): Unit = {
    val factorial4 = factorial(4)
    assert(
      factorial4 == (
        if (4 == 0) 1
        else 4 * factorial(4 - 1)
        ),
      "factorial4 == (\n        if(4 == 0) 1\n        else 4 * factorial(4 - 1)\n      )"
    )
    assert(
      factorial4 == (
        4 * factorial(3)
        ),
      "factorial4 == (\n        4 * factorial(3)\n        )"
    )
    assert(
      factorial4 == (
        4 * 3 * factorial(2)
        ),
      "factorial4 == (\n        4 * 3 * factorial(2)\n        )"
    )
    assert(
      factorial4 == (
        4 * 3 * 2 * factorial(1)
        ),
      "factorial4 == (\n        4 * 3 * 2 * factorial(1)\n        )"
    )
    assert(
      factorial4 == (
        4 * 3 * 2 * 1 * factorial(0)
        ),
      "factorial4 == (\n        4 * 3 * 2 * 1 * factorial(0)\n        )"
    )
    assert(
      factorial4 == (
        4 * 3 * 2 * 1 * 1
        ),
      "factorial4 == (\n        4 * 3 * 2 * 1 * 1\n        )"
    )
    assert(factorial4 == 24, "factorial4 == 24")
    assert(factorial4 == factorialTailRec(4), "factorial4 == factorialTailRec(4)")
  }

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  def factorialTailRec(n: Int): Int = {

    @tailrec
    def factorialTailRec(n: Int, prevRecResult: Int): Int = {
      if (n == 0) prevRecResult // * 1 // factorial(0) == 1
      else factorialTailRec(n - 1, prevRecResult * n)
    }

    if(n < 0) throw new Exception(s"Could not evaluate factorial for negative number: $n.")
    factorialTailRec(n, 1)
  }

  @tailrec
  def greatestCommonDivisor(a: Int, b: Int): Int = {
    if (b == 0) a
    else greatestCommonDivisor(b, a % b)
  }

}
