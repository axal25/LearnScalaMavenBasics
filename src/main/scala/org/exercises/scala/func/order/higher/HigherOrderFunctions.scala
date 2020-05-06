package org.exercises.scala.func.order.higher

import scala.annotation.tailrec

object HigherOrderFunctions {

  def main(): Unit = {
    higherOrderFunctionsTheory()
    nonHigherOrderFunctionDefinitions()
    higherOrderFunctionDefinitions()
    functionTypes()
    anonymousFunctions()
    exercise()
  }

  def cube(x: Int): Int = x * x * x

  def factorial(x: Int): Int = {
    @tailrec
    def iter(x: Int, prevResult: Int): Int = {
      if (x == 0) prevResult
      else iter(x - 1, prevResult * x)
    }

    if (x < 0) throw new Exception(s"Factorial can not be calculated for negative number: $x")
    else iter(x, 1)
  }

  def higherOrderFunctionsTheory(): Unit = {
    println("\nHigher-order functions")
    println("Functional languages treat methods as FIRST-CLASS VALUES")
    println("Methods can be passed as parameters and returned as result")
    println("First-class values - can be passed as parameter and returned as a result")
    println("Methods taking other methods as parameters or returning them as result are called HIGHER-ORDER FUNCTIONS")
  }

  def nonHigherOrderFunctionDefinitions(): Unit = {

    def sumRange(from: Int, to: Int): Int = {
      if (from > to) 0
      else from + sumRange(from + 1, to)
    }

    def sumRangeOfCubes(from: Int, to: Int): Int = {
      if (from > to) 0
      else cube(from) + sumRangeOfCubes(from + 1, to)
    }

    def sumRangeOfFactorials(from: Int, to: Int): Int = {
      if (from > to) 0
      else factorial(from) + sumRangeOfFactorials(from + 1, to)
    }

    println("\nDefinitions of methods - Non-higher-order functions")
    println()
    println("def sumRange(from: Int, to: Int): Int = {")
    println("\tif(from > to) 0")
    println("\telse from + sumRange(from+1, to)")
    println("}")
    println()
    println("def sumRangeOfCubes(from: Int, to: Int): Int = {")
    println("\tif(from > to) 0")
    println("\telse cube(from) + sumRangeOfCubes(from+1, to)")
    println("}")
    println()
    println("def sumRangeOfFactorials(from: Int, to: Int): Int = {")
    println("\tif(from > to) 0")
    println("\telse factorial(from) + sumRangeOfFactorials(from+1, to)")
    println("}")

    println("\nAll of these function are almost the same")
    println("The only difference is the method they call on the \"from\" variable")
    println("We can leverage this using higher-order functions")

    testing()

    def testing(): Unit = {
      assert(sumRange(-3, -1) == -6, "sumRange(-3, -1) == -6")
      assert(sumRange(-2, -2) == -2, "sumRange(-2, -2) == -2")
      assert(sumRange(-1, -1) == -1, "sumRange(-1, -1) == -1")
      assert(sumRange(0, 0) == 0, "sumRange(0, 0) == 0")
      assert(sumRange(1, 1) == 1, "sumRange(1, 1) == 1")
      assert(sumRange(2, 2) == 2, "sumRange(2, 2) == 2")
      assert(sumRange(1, 3) == 6, "sumRange(1, 3) == 6")

      assert(cube(-4) == -64, "cube(-4) == -64")
      assert(cube(-3) == -27, "cube(-3) == -27")
      assert(cube(-2) == -8, "cube(-2) == -8")
      assert(cube(-1) == -1, "cube(-1) == -1")
      assert(cube(0) == 0, "cube(0) == 0")
      assert(cube(1) == 1, "cube(1) == 1")
      assert(cube(2) == 8, "cube(2) == 8")
      assert(cube(3) == 27, "cube(3) == 27")
      assert(cube(4) == 64, "cube(4) == 64")

      assert(sumRangeOfCubes(-4, 4) == 0, "sumRangeOfCubes(-4, 4) == 0")
      assert(sumRangeOfCubes(-3, 3) == 0, "sumRangeOfCubes(-3, 3) == 0")
      assert(sumRangeOfCubes(-2, 2) == 0, "sumRangeOfCubes(-2, 2) == 0")
      assert(sumRangeOfCubes(-1, 1) == 0, "sumRangeOfCubes(-1, 1) == 0")
      assert(sumRangeOfCubes(0, 0) == 0, "sumRangeOfCubes(0, 0) == 0")
      assert(sumRangeOfCubes(-1, 0) == -1, "sumRangeOfCubes(-1, 0) == -1")
      assert(sumRangeOfCubes(-2, 0) == -9, "sumRangeOfCubes(-2, 0) == -28")
      assert(sumRangeOfCubes(-3, 0) == -36, "sumRangeOfCubes(-3, 0) == -36")
      assert(sumRangeOfCubes(-4, 0) == -100, "sumRangeOfCubes(-4, 0) == -100")

      assert(factorial(0) == 1, "factorial(0) == 1")
      assert(factorial(1) == 1, "factorial(1) == 1")
      assert(factorial(2) == 2, "factorial(2) == 2")
      assert(factorial(3) == 6, "factorial(3) == 6")
      assert(factorial(4) == 24, "factorial(4) == 24")
      assert(factorial(5) == 120, "factorial(5) == 120")

      assert(sumRangeOfFactorials(0, 0) == 1, "sumRangeOfFactorials(0, 0) == 1")
      assert(sumRangeOfFactorials(0, 1) == 2, "sumRangeOfFactorials(0, 1) == 2")
      assert(sumRangeOfFactorials(0, 2) == 4, "sumRangeOfFactorials(0, 2) == 4")
      assert(sumRangeOfFactorials(0, 3) == 10, "sumRangeOfFactorials(0, 3) == 10")
      assert(sumRangeOfFactorials(0, 4) == 34, "sumRangeOfFactorials(0, 4) == 34")
      assert(sumRangeOfFactorials(0, 5) == 154, "sumRangeOfFactorials(0, 5) == 154")
    }
  }

  def higherOrderFunctionDefinitions(): Unit = {

    def sum(method: Int => Int, from: Int, to: Int): Int = {
      if (from > to) 0
      else method(from) + sum(method, from + 1, to)
    }

    def value(x: Int): Int = x

    def sumRange(from: Int, to: Int): Int = sum(value, from, to)

    def sumRangeOfCubes(from: Int, to: Int): Int = sum(cube, from, to)

    def sumRangeOfFactorials(from: Int, to: Int): Int = sum(factorial, from, to)

    println("\nDefinitions of methods - Higher-order functions\n")

    println("def sum(method: Int => Int, from: Int, to: Int): Int = {")
    println("\tif(from > to) 0")
    println("\tmethod(from) + sum(method, from+1, to)")
    println("}")
    println()
    println("def value(x: Int): Int = x")
    println("def sumRange(from: Int, to: Int): Int = sum(value, from, to)")
    println("def sumRangeOfCubes(from: Int, to: Int): Int = sum(cube, from, to)")
    println("def sumRangeOfFactorials(from: Int, to: Int): Int = sum(factorial, from, to)")

    testing()

    def testing(): Unit = {
      assert(sumRange(-3, -1) == -6, "sumRange(-3, -1) == -6")
      assert(sumRange(-2, -2) == -2, "sumRange(-2, -2) == -2")
      assert(sumRange(-1, -1) == -1, "sumRange(-1, -1) == -1")
      assert(sumRange(0, 0) == 0, "sumRange(0, 0) == 0")
      assert(sumRange(1, 1) == 1, "sumRange(1, 1) == 1")
      assert(sumRange(2, 2) == 2, "sumRange(2, 2) == 2")
      assert(sumRange(1, 3) == 6, "sumRange(1, 3) == 6")

      assert(sumRangeOfCubes(-4, 4) == 0, "sumRangeOfCubes(-4, 4) == 0")
      assert(sumRangeOfCubes(-3, 3) == 0, "sumRangeOfCubes(-3, 3) == 0")
      assert(sumRangeOfCubes(-2, 2) == 0, "sumRangeOfCubes(-2, 2) == 0")
      assert(sumRangeOfCubes(-1, 1) == 0, "sumRangeOfCubes(-1, 1) == 0")
      assert(sumRangeOfCubes(0, 0) == 0, "sumRangeOfCubes(0, 0) == 0")
      assert(sumRangeOfCubes(-1, 0) == -1, "sumRangeOfCubes(-1, 0) == -1")
      assert(sumRangeOfCubes(-2, 0) == -9, "sumRangeOfCubes(-2, 0) == -9")
      assert(sumRangeOfCubes(-3, 0) == -36, "sumRangeOfCubes(-3, 0) == -36")
      assert(sumRangeOfCubes(-4, 0) == -100, "sumRangeOfCubes(-4, 0) == -100")

      assert(sumRangeOfFactorials(0, 0) == 1, "sumRangeOfFactorials(0, 0) == 1")
      assert(sumRangeOfFactorials(0, 1) == 2, "sumRangeOfFactorials(0, 1) == 2")
      assert(sumRangeOfFactorials(0, 2) == 4, "sumRangeOfFactorials(0, 2) == 4")
      assert(sumRangeOfFactorials(0, 3) == 10, "sumRangeOfFactorials(0, 3) == 10")
      assert(sumRangeOfFactorials(0, 4) == 34, "sumRangeOfFactorials(0, 4) == 34")
      assert(sumRangeOfFactorials(0, 5) == 154, "sumRangeOfFactorials(0, 5) == 154")
    }
  }

  def functionTypes(): Unit = {
    println("\nFunction types")
    println("In definition of high-order method")
    println("\"def sum(method: Int => Int, from: Int, to: Int): Int = {...}\"")
    println("Notation \"Int => Int\" means this argument is a method mapping (transforming in some way) Int to Int")
    println("So \"A => B\" would be a method taking A type as argument and returning type B")
    println("\"(A1, A2) => B\" would be a method taking 2 arguments - 1 of type A1 and 2nd of type A2 and returning type B")
    println("In general \"(A1, A2, ..., An) => B\" is method taking n arguments of type A1, A2 through to An and returning type B")
  }

  def anonymousFunctions(): Unit = {

    def sum(method: Int => Int, from: Int, to: Int): Int = {
      if (from > to) 0
      else method(from) + sum(method, from + 1, to)
    }

    def sumRange(from: Int, to: Int): Int = sum((x: Int) => x, from, to)

    def sumRangeOfCubes(from: Int, to: Int): Int = sum((x: Int) => x * x * x, from, to)

    println("\nAnonymous functions")
    println("Methods do not have to have a name")
    println("If they are short and are not going to be reused we can specify them as anonymous methods like so:\n")

    println("def sum(method: Int => Int, from: Int, to: Int): Int = {")
    println("\tif (from > to) 0")
    println("\telse method(from) + sum(method, from + 1, to)")
    println("}")
    println()
    println("def sumRange(from: Int, to: Int): Int = sum( (x: Int) => x, from, to)")
    println("def sumRangeOfCubes(from: Int, to: Int): Int = sum( (x: Int) => x * x * x, from, to)")

    println("\nAnonymous methods can have multiple arguments separated by commas (\",\") like so:\n")

    def sumUsingCurAndMax(method: (Int, Int) => Int, from: Int, to: Int): Int = {
      if (from > to) 0
      else method(from, to) + sumUsingCurAndMax(method, from + 1, to)
    }

    def sumRangeOfCurAndMax(from: Int, to: Int): Int = sumUsingCurAndMax((from: Int, to: Int) => from + to, from: Int, to: Int)

    println("def sumUsingCurAndMax(method: (Int, Int) => Int, from: Int, to: Int): Int = {")
    println("if (from > to) 0")
    println("else method(from, to) + sumUsingCurAndMax(method, from + 1, to)")
    println("}")
    println()
    println("def sumRangeOfCurAndMax(from: Int, to: Int): Int = sumUsingCurAndMax((from: Int, to: Int) => from + to, from: Int, to: Int)")

    println("\nIn the scope of defining anonymous function exist completely new set of variable names")
    println("Meaning we can use the same variable names as the names of other variables inside method which we define our anonymous method")

    println("\nAnonymous methods can be written inside a code clock using normal definition and after semicolon returning its reference (name of method):")

    def sumRangeOfCurAndMax2(from: Int, to: Int): Int = sumUsingCurAndMax(
      {
        def addition(from: Int, to: Int): Int = from + to;
        addition
      },
      from: Int,
      to: Int
    )

    println("def sumRangeOfCurAndMax2(from: Int, to: Int): Int = sumUsingCurAndMax(")
    println("\t{")
    println("\t\tdef addition(from: Int, to: Int): Int = from + to;")
    println("\t\taddition")
    println("\t}")
    println("\tfrom: Int,")
    println("\tto: Int")
    println(")")

    println("\nAnonymous methods do not have to have specified parameter types")
    println("Because they are already specified by the method taking anonymous method as parameter\n")

    def sumRangeOfCurAndMax3(from: Int, to: Int): Int = sumUsingCurAndMax((from, to) => from + to, from, to)

    println("def sumRangeOfCurAndMax3(from: Int, to: Int): Int = sumUsingCurAndMax((from, to) => from + to, from, to)")

    println()

    def sumRange2(from: Int, to: Int): Int = sum(from => from, from, to)

    println("def sumRange2(from: Int, to: Int): Int = sum(from => from, from, to)")

    def sumRangeOfCubes2(from: Int, to: Int): Int = sum(x => x * x * x, from, to)

    println("def sumRangeOfCubes2(from: Int, to: Int): Int = sum(x => x * x * x, from, to)")

    testing()

    def testing(): Unit = {
      assert(sumRange(-3, -1) == -6, "sumRange(-3, -1) == -6")
      assert(sumRange(-2, -2) == -2, "sumRange(-2, -2) == -2")
      assert(sumRange(-1, -1) == -1, "sumRange(-1, -1) == -1")
      assert(sumRange(0, 0) == 0, "sumRange(0, 0) == 0")
      assert(sumRange(1, 1) == 1, "sumRange(1, 1) == 1")
      assert(sumRange(2, 2) == 2, "sumRange(2, 2) == 2")
      assert(sumRange(1, 3) == 6, "sumRange(1, 3) == 6")

      assert(sumRangeOfCubes(-4, 4) == 0, "sumRangeOfCubes(-4, 4) == 0")
      assert(sumRangeOfCubes(-3, 3) == 0, "sumRangeOfCubes(-3, 3) == 0")
      assert(sumRangeOfCubes(-2, 2) == 0, "sumRangeOfCubes(-2, 2) == 0")
      assert(sumRangeOfCubes(-1, 1) == 0, "sumRangeOfCubes(-1, 1) == 0")
      assert(sumRangeOfCubes(0, 0) == 0, "sumRangeOfCubes(0, 0) == 0")
      assert(sumRangeOfCubes(-1, 0) == -1, "sumRangeOfCubes(-1, 0) == -1")
      assert(sumRangeOfCubes(-2, 0) == -9, "sumRangeOfCubes(-2, 0) == -9")
      assert(sumRangeOfCubes(-3, 0) == -36, "sumRangeOfCubes(-3, 0) == -36")
      assert(sumRangeOfCubes(-4, 0) == -100, "sumRangeOfCubes(-4, 0) == -100")

      assert(sumRangeOfCurAndMax(-4, 4) == 36, "sumRangeOfCurAndMax(-4, 4) == 36")
      assert(sumRangeOfCurAndMax(-4, 0) == -10, "sumRangeOfCurAndMax(-4, 0) == -10")
      assert(sumRangeOfCurAndMax(0, 4) == 30, "sumRangeOfCurAndMax(0, 4) == 30")

      assert(sumRangeOfCurAndMax2(-4, 4) == 36, "sumRangeOfCurAndMax2(-4, 4) == 36")
      assert(sumRangeOfCurAndMax2(-4, 0) == -10, "sumRangeOfCurAndMax2(-4, 0) == -10")
      assert(sumRangeOfCurAndMax2(0, 4) == 30, "sumRangeOfCurAndMax2(0, 4) == 30")

      assert(sumRangeOfCurAndMax3(-4, 4) == 36, "sumRangeOfCurAndMax3(-4, 4) == 36")
      assert(sumRangeOfCurAndMax3(-4, 0) == -10, "sumRangeOfCurAndMax3(-4, 0) == -10")
      assert(sumRangeOfCurAndMax3(0, 4) == 30, "sumRangeOfCurAndMax3(0, 4) == 30")

      assert(sumRange2(-3, -1) == -6, "sumRange2(-3, -1) == -6")
      assert(sumRange2(-2, -2) == -2, "sumRange2(-2, -2) == -2")
      assert(sumRange2(-1, -1) == -1, "sumRange2(-1, -1) == -1")
      assert(sumRange2(0, 0) == 0, "sumRange2(0, 0) == 0")
      assert(sumRange2(1, 1) == 1, "sumRange2(1, 1) == 1")
      assert(sumRange2(2, 2) == 2, "sumRange2(2, 2) == 2")
      assert(sumRange2(1, 3) == 6, "sumRange2(1, 3) == 6")

      assert(sumRangeOfCubes2(-4, 4) == 0, "sumRangeOfCubes2(-4, 4) == 0")
      assert(sumRangeOfCubes2(-3, 3) == 0, "sumRangeOfCubes2(-3, 3) == 0")
      assert(sumRangeOfCubes2(-2, 2) == 0, "sumRangeOfCubes2(-2, 2) == 0")
      assert(sumRangeOfCubes2(-1, 1) == 0, "sumRangeOfCubes2(-1, 1) == 0")
      assert(sumRangeOfCubes2(0, 0) == 0, "sumRangeOfCubes2(0, 0) == 0")
      assert(sumRangeOfCubes2(-1, 0) == -1, "sumRangeOfCubes2(-1, 0) == -1")
      assert(sumRangeOfCubes2(-2, 0) == -9, "sumRangeOfCubes2(-2, 0) == -9")
      assert(sumRangeOfCubes2(-3, 0) == -36, "sumRangeOfCubes2(-3, 0) == -36")
      assert(sumRangeOfCubes2(-4, 0) == -100, "sumRangeOfCubes2(-4, 0) == -100")
    }
  }

  def exercise(): Unit = {
    def sum(method: Int => Int, from: Int, to: Int): Int = {

      @tailrec
      def loop(curNumb: Int, curSum: Int): Int = {
        if (curNumb > to) curSum
        else loop(curNumb + 1, curSum + method(curNumb))
      }

      loop(from, 0)
    }

    def originalWithWorkingButBadAnswer(): Unit = {
      def originalSum(f: Int => Int, a: Int, b: Int): Int = {
        @tailrec
        def loop(x: Int, acc: Int): Int =
          if (x > b) acc
          else loop(x + 1, acc + f(x))

        val unknown = b - 10
        loop(a, unknown)
      }

      val unknown = 10
      println(s"originalSum(x => x, 1, $unknown): ${originalSum(x => x, 1, unknown)}")
      assert(originalSum(x => x, 1, unknown) == 55, "originalSum(x => x, 1, unknown) == 55")
    }

    println("\r\nExercise")
    println(s"sum(x => x, 1, 10): ${sum(x => x, 1, 10)}")
    assert(sum(x => x, 1, 10) == 55, "sum(x => x, 1, 10) == 55")
    originalWithWorkingButBadAnswer()
  }
}
