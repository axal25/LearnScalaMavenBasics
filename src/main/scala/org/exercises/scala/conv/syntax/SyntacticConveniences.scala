package org.exercises.scala.conv.syntax

import org.exercises.scala.lib.standard.UnimplementedCaseException
import util.MyAssertions

object SyntacticConveniences {

  def main(): Unit = {
    println("\nSyntactic conveniences")
    stringInterpolation()
    tuples()
    functionsAsObjects()
    functionsAndMethod()
    fors()
    methodParameters()
  }

  def stringInterpolation(): Unit = {
    def genHello(obj: Any): Any = obj match {
      case string: String => hello(string)
    }

    def hello(name: String): String = s"Hello, $name!"

    def genHelloUpperCase(obj: Any): Any = obj match {
      case string: String => helloUpperCase(string)
    }

    def helloUpperCase(name: String): String = s"Hello, ${name.toUpperCase}!"

    println("\nString interpolation")
    MyAssertions.myAssert("Jacek Oleś", "Hello, Jacek Oleś!", genHello, "hello")
    MyAssertions.myAssert("Jacek Oleś", "Hello, JACEK OLEŚ!", genHelloUpperCase, "helloUpperCase")
  }

  def tuples(): Unit = {
    def tupleOf3(int: Int, string: String, double: Double): (Int, String, Double) = (int, string, double)

    def tupleOf3Transform1(tupleOf3: Any): Any = tupleOf3 match {
      case (int: Int, string: String, double: Double) => (int + 1, s"$string +1", double + 3.5)
    }

    def tupleOf3Transform2(tupleOf3: Any): Any = tupleOf3 match {
      case tupleOf3: (Int, String, Double) => (tupleOf3._1 + 2, s"${tupleOf3._2} +2", tupleOf3._3 + 4.5)
    }

    println("\nTuples")
    MyAssertions.myAssert(tupleOf3(42, "foo", 1.5), (42, "foo", 1.5), x => x, "x => x")
    MyAssertions.myAssert(tupleOf3(0, "bar", 3.5), (0, "bar", 3.5), x => x, "x => x")
    MyAssertions.myAssert(tupleOf3(42, "foo", 1.5), (43, "foo +1", 5.0), tupleOf3Transform1, "tupleOf3Transform1")
    MyAssertions.myAssert(tupleOf3(42, "foo", 1.5), (44, "foo +2", 6.0), tupleOf3Transform2, "tupleOf3Transform2")
  }

  def functionsAsObjects(): Unit = {
    println("\nFunctions as objects")
    println("Function values are treated as objects in Scala")
    println("function type: (A => B) is equal to scala.Function1[A, B]")
    println("package scala")
    println("trait Function1[A, B] {")
    println("\tdef apply(x: A): B")
    println("}")
    println("Function are objects with with apply methods")
    println("If Traits are like Interfaces in Java they are Function Interfaces with Single Abstract Methods")
    println("There are Traits: Function1, Function2, Function3, ..., Function22 (for 1 - 22 argument functions)")

    println("\nExpansion of function values")
    val anonymousFunction = (x: Int) => x * x
    println("val anonymousFunction = (x: Int) => x * x")
    println(s"val anonymousFunction: ${anonymousFunction}")
    println("is expanded to:")
    val expandedAnonymousFunction1 = {
      class AnonFun extends Function1[Int, Int] {
        def apply(x: Int) = x * x
      }
    }
    println("val expandedAnonymousFunction1 = {")
    println("\tclass AnonFun extends Function1[Int, Int] {")
    println("\t\tdef apply(x: Int) = x * x")
    println("\t}")
    println("}")
    println(s"val expandedAnonymousFunction1: ${expandedAnonymousFunction1}")
    println("or, shorter, using anonymous class syntax, expended to:")
    val expandedAnonymousFunction2 = {
      new Function1[Int, Int] {
        def apply(x: Int) = x * x
      }
    }
    println("val expandedAnonymousFunction2 = {")
    println("\tnew Function1[Int, Int] {")
    println("\t\tdef apply(x: Int) = x * x")
    println("\t}")
    println("}")
    println(s"val expandedAnonymousFunction2: ${expandedAnonymousFunction2}")

    println("\nExpansion of function calls")
    println("Function call: f(a, b)")
    println("where: f - value of some class type")
    println("is expanded to:")
    println("f.apply(a, b)")

    println("For declaration in form of:")
    println("val foo = (x: Int) => x * x")
    println("foo(7)")
    println("Object oriented translation would be:")
    val foo = new Function1[Int, Int] {
      def apply(x: Int) = x * x
    }
    println("val foo = new Function1[Int, Int] {")
    println("\tdef apply(x: Int) = x * x")
    println("}")
    foo.apply(7)
    println("foo.apply(7)")
  }

  def functionsAndMethod(): Unit = {
    println("\nFunctions and Methods")

    def f(x: Int): Boolean = if (x < 0) false else true

    println("Method of form:")
    println("def f(x: Int): Boolean = if(x < 0) false else true")
    println("is not a function value")
    println("But if f method is used in a place where function type is expected like in:")

    def v = (x: Int) => f(x)

    println("def v = (x: Int) => f(x)")
    println("The f method is converted to function value")
  }

  def fors(): Unit = {
    println("\nFor expressions")
    println("Several data types have methods: map, filter, flatMap")
    println("These methods are so common that there is dedicated syntax for them - FOR expressions")

    println("\nMAP")
    val list = List(0, 1, 2, 3, 4, 5, 6)
    val expectedMappedList = List(1, 2, 3, 4, 5, 6, 7)

    def map1(obj: Any): Any = obj match {
      case list: List[Int] => list.map(element => element + 1)
      case _ => new UnimplementedCaseException(this, "map1", obj)
    }

    MyAssertions.myAssert(list, expectedMappedList, map1, "map1{ list.map(element => element + 1) }")

    def map2(obj: Any): Any = obj match {
      case list: List[Int] => for (element <- list) yield element + 1
      case _ => new UnimplementedCaseException(this, "map2", obj)
    }

    MyAssertions.myAssert(list, expectedMappedList, map1, "map1{ for(element <- list) yield element + 1 }")

    println("\nFILTER")
    val expectedFilteredList = List(1, 3, 5, 7)

    def filter1(obj: Any): Any = obj match {
      case list: List[Int] => list.filter(element => element % 2 == 0).map(element => element + 1)
      case _ => new UnimplementedCaseException(this, "filter1", obj)
    }

    MyAssertions.myAssert(list, expectedFilteredList, filter1, "filter1{ list.filter(element => element % 2 == 0).map(element => element + 1) }")

    def filter2(obj: Any): Any = obj match {
      case list: List[Int] => for (element <- list if element % 2 == 0) yield element + 1
      case _ => new UnimplementedCaseException(this, "filter2", obj)
    }

    MyAssertions.myAssert(list, expectedFilteredList, filter2, "filter2{ for(element <- list if element % 2 == 0) yield element + 1 }")

    println("\nFLAT MAP")
    val anotherList = List("zero", "one", "two")
    val expectedFlatMappedList = List(
      (0, "zero"), (0, "one"), (0, "two"),
      (1, "zero"), (1, "one"), (1, "two"),
      (2, "zero"), (2, "one"), (2, "two"),
      (3, "zero"), (3, "one"), (3, "two"),
      (4, "zero"), (4, "one"), (4, "two"),
      (5, "zero"), (5, "one"), (5, "two"),
      (6, "zero"), (6, "one"), (6, "two"),
    )

    def flatMap1(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
      case (list1: List[Any], list2: List[Any]) => list1.flatMap(element1 => list2.map(element2 => (element1, element2)))
      case _ => new UnimplementedCaseException(this, "flatMap1{ list1.flatMap(element1 => list2.map(element2 => (element1, element2))) } ", obj1, obj2)
    }

    MyAssertions.myAssert2Arg(list, anotherList, expectedFlatMappedList, flatMap1, "flatMap1")

    def flatMap2(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
      case (list1: List[Any], list2: List[Any]) => for (element1 <- list1; element2 <- list2) yield (element1, element2)
      case _ => new UnimplementedCaseException(this, "flatMap1{ (element1 <- list1; element2 <- list2) yield (element1, element2) }", obj1, obj2)
    }

    MyAssertions.myAssert2Arg(list, anotherList, expectedFlatMappedList, flatMap2, "flatMap2")

    println("\nCombined power of FORs")
    val expectedCombinedPowerOfForsList = List(
      (0, "zero"), (0, "one"), (0, "two"),
      (2, "zero"), (2, "one"), (2, "two"),
      (4, "zero"), (4, "one"), (4, "two"),
      (6, "zero"), (6, "one"), (6, "two"),
    )
    val expectedCombinedPowerOfForsListVarName = "expectedCombinedPowerOfForsList"
    println(f"$expectedCombinedPowerOfForsListVarName%80s: $expectedCombinedPowerOfForsList%80s")

    val combinedPowerOfFors = for {
      element1 <- list if element1 % 2 == 0
      element2 <- anotherList
    } yield (element1, element2)
    println("val combinedPowerOfFors = for {")
    println("\telement1 <- list if element1 % 2 == 0")
    println("\telement2 <- anotherList")
    println("} yield (element1, element2)")
    val combinedPowerOfForsVarName = "combinedPowerOfFors"
    println(f"$combinedPowerOfForsVarName%80s: $combinedPowerOfFors%80s")
    assert(expectedCombinedPowerOfForsList == combinedPowerOfFors, "expectedCombinedPowerOfForsList == combinedPowerOfFors")

    val desugaredCode = list
      .filter(element1 => element1 % 2 == 0)
      .flatMap(
        element1 => {
          anotherList.map(element2 => (element1, element2))
        }
      )
    println("val desugaredCode = list")
    println("\t.filter(element1 => element1 % 2 == 0)")
    println("\t.flatMap(")
    println("\t\telement1 => {")
    println("\t\t\tanotherList.map(element2 => (element1, element2))")
    println("\t\t}")
    println("\t)")
    val desugaredCodeVarName = "desugaredCode"
    println(f"$desugaredCodeVarName%80s: $desugaredCode%80s")
    assert(expectedCombinedPowerOfForsList == desugaredCode, "expectedCombinedPowerOfForsList == desugaredCode")
  }

  def methodParameters(): Unit = {
    println("\nMethod Parameters")
    val range1: Range = Range(1, 10, 2)
    println("val range1: Range = Range(1, 10, 2)")
    println(s"range1: $range1")
    println("case class Range(start: Int, end: Int, step: Int)")
    val range2: Range = Range(start = 1, end = 10, step = 2)
    println("val range2: Range = Range(start = 1, end = 10, step = 2)")
    println(s"range2: $range2")

    println("\nDefault Values")
    println("case class Range(start: Int, end: Int, step: Int = 1")
    println("Range class constructor has a default value = 1 for parameter step, so it can be omitted")
    val range3: Range = Range(start = 1, end = 10)
    println(s"range3: $range3")

    println("\nRepeated Parameters - varargs")

    def average(x: Int, xs: Int*): Double = (x :: xs.toList).sum.toDouble / (xs.size + 1)

    println("def average(x: Int, xs: Int*): Double = (x :: xs.toList).sum.toDouble / (xs.size + 1)")

    def assertAverage(expectedAverage: Double, x: Int, xs: Int*): Unit = {
      val actualAverage = average(x, xs: _*) // stupidest syntax ever
      val assertion = s"(actualAverage) $actualAverage == $expectedAverage (expectedAverage)"
      assert(actualAverage == expectedAverage, assertion)
      println(assertion)
    }

    assertAverage(1, 1)
    assertAverage(1.5, 1, 2)
    assertAverage(2, 1, 2, 3)
    val listWithout0 = List(1, 2, 3, 4)
    assertAverage(2, 0, listWithout0: _*)

    println("\nType Aliases")
    type Result = Either[String, (Int, Int)]
    val divByZero = Left("Division by zero")
    def divide(dividend: Int, divisor: Int): Result = {
      if(divisor == 0) divByZero
      else Right(dividend / divisor, dividend % divisor)
    }
    println("type Result = Either[String, (Int, Int)]")
    println("val divByZero = Left(\"Division by zero\")")
    println("def divide(dividend: Int, divisor: Int): Result = {")
    println("\tif(divisor == 0) divByZero")
    println("\telse Right(dividend / divisor, dividend % divisor)")
    println("}")

    def assertDivide(expectedResult: Result, dividend: Int, divisor: Int): Unit = {
      val actualResult = divide(dividend, divisor)
      val assertion = s"(actualResult) $actualResult == $expectedResult (expectedResult)"
      assert(actualResult == expectedResult, assertion)
      println(assertion)
    }
    assertDivide(Right(1, 2), 6, 4)
    assertDivide(divByZero, 2, 0)
    assertDivide(Right(2, 0), 8 ,4)
  }
}
