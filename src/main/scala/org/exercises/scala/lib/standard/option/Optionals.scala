package org.exercises.scala.lib.standard.option

import org.exercises.scala.lib.standard.UnimplementedCaseException
import util.MyAssertions

import scala.annotation.tailrec

object Optionals {

  def main(): Unit = {
    println("\nOption values")
    println("Option[A] - optional value of type A")
    println("Option[T] can be used to implement partially defined functions")
    returningOptions()
    matchingOptions()
    manipulatingOptions()
    commonOptionOperations()
  }

  val expectedSqrt2Aprox = 1.414213562373095
  val expectedSqrt3Aprox = 1.7320508075688772

  def returningOptions(): Unit = {
    println("\nReturning Option(s)")

    MyAssertions.myAssert1Arg(1.0, Some(1.0), sqrtGeneralization, "sqrt")
    MyAssertions.myAssert1Arg(1, Some(1), sqrtGeneralization, "sqrt")

    MyAssertions.myAssert1Arg(2.0, Some(expectedSqrt2Aprox), sqrtGeneralization, "sqrt")
    MyAssertions.myAssert1Arg(2, Some(expectedSqrt2Aprox), sqrtGeneralization, "sqrt")

    MyAssertions.myAssert1Arg(3.0, Some(expectedSqrt3Aprox), sqrtGeneralization, "sqrt")
    MyAssertions.myAssert1Arg(3, Some(expectedSqrt3Aprox), sqrtGeneralization, "sqrt")

    MyAssertions.myAssert1Arg(-1.0, None, sqrtGeneralization, "sqrt")
    MyAssertions.myAssert1Arg(-2.0, None, sqrtGeneralization, "sqrt")
    MyAssertions.myAssert1Arg(-3.0, None, sqrtGeneralization, "sqrt")
  }

  def matchingOptions(): Unit = {
    println("\nMatching Option(s)")

    MyAssertions.myAssert1Arg(sqrt(2), s"Got Some: ${expectedSqrt2Aprox}", matchOption, "matchOption")
    MyAssertions.myAssert1Arg(sqrt(-2), s"Got None", matchOption, "matchOption")

    MyAssertions.myAssert1Arg(sqrt(3), s"Got Some: ${expectedSqrt3Aprox}", matchOption, "matchOption")
    MyAssertions.myAssert1Arg(sqrt(-3), s"Got None", matchOption, "matchOption")
  }

  def manipulatingOptions(): Unit = {
    println("\nManipulating Option(s)")

    MyAssertions.myAssert1Arg(sqrt(2), expectedSqrt2Aprox + 1, manipulateOption, "manipulateOption")
    MyAssertions.myAssert1Arg(sqrt(-2), Nil, manipulateOption, "manipulateOption")
    MyAssertions.myAssert1Arg(Option(extractValue(sqrt(2)).toString), s"${expectedSqrt2Aprox.toString} 1", manipulateOption, "manipulateOption")

    MyAssertions.myAssert1Arg(sqrt(3), expectedSqrt3Aprox + 1, manipulateOption, "manipulateOption")
    MyAssertions.myAssert1Arg(sqrt(-3), Nil, manipulateOption, "manipulateOption")
    MyAssertions.myAssert1Arg(Option(extractValue(sqrt(3)).toString), s"${expectedSqrt3Aprox.toString} 1", manipulateOption, "manipulateOption")
  }

  def commonOptionOperations(): Unit = {
    println("\nCommon operations on Option")

    println("\nMap - transforms:")
    println("val ot: Option[T] match = ot {")
    println("\tcase Some(t) => val t: T => transform(t): T")
    println("\tcase None => ?Nil?")
    println("} => Option[T] => Some(transform(t)) | None")
    println("transform: (int: Int => int + 1)")
    MyAssertions.myAssert1Arg(Some(1), Some(2), OptionsImpl.map, "OptionsImpl.map")
    MyAssertions.myAssert1Arg(Some(2), Some(3), OptionsImpl.map, "OptionsImpl.map")
    MyAssertions.myAssert1Arg(None, None, OptionsImpl.map, "OptionsImpl.map")

    println("\nFilter - filters:")
    println("val ot: Option[T] = {")
    println("\tval t: T")
    println("\tif(condition(t)) Some(t)")
    println("\telse None")
    println("}")
    println("condition: (int: Int => int % 2 == 0)")
    MyAssertions.myAssert1Arg(Some(1), None, OptionsImpl.filter, "OptionsImpl.filter")
    MyAssertions.myAssert1Arg(Some(2), Some(2), OptionsImpl.filter, "OptionsImpl.filter")
    MyAssertions.myAssert1Arg(None, None, OptionsImpl.filter, "OptionsImpl.filter")

    println("\nFlatMap - transforms:")
    println("val ot: Option[T] = match ot {")
    println("\tcase Some(t) => val t: T => transform(t) => Some(transform(T)): Option[T]")
    println("\tcase None => None")
    println("} => Option[T] => Some(transform(t)) | None")
    println("transform: (int: Int => int + 5)")
    MyAssertions.myAssert1Arg(Some(1), Some(6), OptionsImpl.flatMap, "OptionsImpl.flatMap")
    MyAssertions.myAssert1Arg(Some(2), Some(7), OptionsImpl.flatMap, "OptionsImpl.flatMap")
    MyAssertions.myAssert1Arg(None, None, OptionsImpl.flatMap, "OptionsImpl.flatMap")
  }

  object OptionsImpl {

    object Transformations {
      def mapTransform(obj: Any): Any = obj match {
        case int: Int => int + 1
        case _ => throw new UnimplementedCaseException(this, "mapTransform", obj)
      }

      def filterTransform(obj: Any): Boolean = obj match {
        case int: Int => int % 2 == 0
        case _ => throw new UnimplementedCaseException(this, "filterTransform", obj)
      }

      def flatMapTransform(obj: Any): Some[Any] = obj match {
        case int: Int => Some(int + 5)
        case _ => throw new UnimplementedCaseException(this, "flatMapTransform", obj)
      }
    }

    def map(obj: Any): Any = Options.map(obj, Transformations.mapTransform)

    def filter(obj: Any): Any = Options.filter(obj, Transformations.filterTransform)

    def flatMap(obj: Any): Any = Options.flatMap(obj, Transformations.flatMapTransform)
  }

  object Options {

    def map(obj: Any, transform: Any => Any): Any = obj match {
      case opt: Option[Any] => opt.map(transform)
      case _ => throw new UnimplementedCaseException(this, "map", obj)
    }

    def filter(obj: Any, transform: Any => Boolean): Any = obj match {
      case opt: Option[Any] => opt.filter(transform)
      case _ => throw new UnimplementedCaseException(this, "filter", obj)
    }

    def flatMap(obj: Any, transform: Any => Option[Any]): Any = obj match {
      case opt: Option[Any] => opt.flatMap(transform)
      case _ => throw new UnimplementedCaseException(this, "flatmap", obj)
    }
  }

  def sqrtGeneralization(obj: Any): Any = obj match {
    case double: Double => sqrt(double)
    case int: Int => sqrt(int)
    case _ => throw new Exception("Unhandled case")
  }

  def sqrt(x: Double): Option[Double] = {
    val acceptAproxDiff = 0.000000000000001

    @tailrec
    def sqrtIter(x: Double, prevApprox: Double): Double = isApproxGoodEnough(x, prevApprox) match {
      case true => prevApprox
      case _ => {
        val curAprox = (x / prevApprox + prevApprox) / 2
        sqrtIter(x, curAprox)
      }
    }

    def isApproxGoodEnough(x: Double, prevApprox: Double): Boolean = {
      abs(prevApprox * prevApprox - x) <= acceptAproxDiff
    }

    if (x < 0) None
    else Some(sqrtIter(x, 1))
  }

  def abs(x: Double): Double = {
    if (x < 0) -x
    else x
  }

  def matchOption(obj: Any): Any = obj match {
    case opt: Option[Any] => opt match {
      case None => "Got None"
      case Some(obj) => s"Got Some: ${obj}"
    }
    case _ => throw new UnimplementedCaseException(this, "matchOption", obj)
  }

  def extractValue(obj: Any): Any = obj match {
    case None => Nil
    case Some(obj) => obj
    case _ => throw new UnimplementedCaseException(this, "extractValue", obj)
  }

  def manipulateOption(obj: Any): Any = obj match {
    case opt: Option[Any] => opt match {
      case None => Nil
      case Some(obj) => obj match {
        case double: Double => double + 1
        case int: Int => int + 1
        case string: String => string + " 1"
        case _ => throw new UnimplementedCaseException(this, "manipulateOption", obj)
      }
    }
    case _ => throw new UnimplementedCaseException(this, "manipulateOption", obj)
  }
}
