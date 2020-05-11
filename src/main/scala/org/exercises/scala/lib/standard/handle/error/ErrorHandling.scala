package org.exercises.scala.lib.standard.handle.error

import org.exercises.scala.lib.standard.UnimplementedCaseException
import org.exercises.scala.lib.standard.handle.error.ErrorHandling.TriesImpl.Transformations
import org.exercises.scala.lib.standard.option.{BadNumberTypeToSqrtException, NegativeNumberToSqrtException}
import util.MyAssertions

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ErrorHandling {
  def main(): Unit = {
    println("\nHandling Errors")
    tries()
    manipulatingMatchingTries()
    eithers()
    manipulatingMatchingEithers()
  }

  val expectedSqrt2Aprox = 1.414213562373095
  val expectedSqrt3Aprox = 1.7320508075688772

  def tries(): Unit = {
    println("\nTry[A] - type, expression trying to evaluate to type A")
    println("Try[A] can be either Success[A] or Failure")
    println("Failure is improvement over None because it provides a reason in a form of Throwable type")

    MyAssertions.myAssert1Arg(2, Success(expectedSqrt2Aprox), sqrt, "sqrt")
    MyAssertions.myAssert1Arg(-2, Failure(new NegativeNumberToSqrtException(-2.0)), sqrt, "sqrt")

    MyAssertions.myAssert1Arg(3, Success(expectedSqrt3Aprox), sqrt, "sqrt")
    MyAssertions.myAssert1Arg(-3, Failure(new NegativeNumberToSqrtException(-3.0)), sqrt, "sqrt")
  }

  def manipulatingMatchingTries(): Unit = {
    println("\nManipulating (matching & common functions) tries")

    MyAssertions.myAssert1Arg(sqrt(2), Success(expectedSqrt2Aprox + 1), TriesImpl.map, "TriesImpl.map")
    MyAssertions.myAssert1Arg(sqrt(-2), Failure(new NegativeNumberToSqrtException(-2.0)), TriesImpl.map, "TriesImpl.map")

    MyAssertions.myAssert1Arg(Success(2), Success(2), TriesImpl.filter, "TriesImpl.filter")
    MyAssertions.myAssert1Arg(sqrt(2), Failure(new NoSuchElementException(s"Predicate does not hold for $expectedSqrt2Aprox")), TriesImpl.filter, "TriesImpl.filter")

    MyAssertions.myAssert1Arg(sqrt(2), Success(expectedSqrt2Aprox + 5), TriesImpl.flatMap, "TriesImpl.flatMap")
    MyAssertions.myAssert1Arg(sqrt(-2), Failure(new NegativeNumberToSqrtException(-2.0)), TriesImpl.flatMap, "TriesImpl.flatMap")

    MyAssertions.myAssert1Arg(sqrt(3), Success(expectedSqrt3Aprox + 1), TriesImpl.map, "TriesImpl.map")
    MyAssertions.myAssert1Arg(sqrt(-3), Failure(new NegativeNumberToSqrtException(-3.0)), TriesImpl.map, "TriesImpl.map")

    MyAssertions.myAssert1Arg(Success(4), Success(4), TriesImpl.filter, "TriesImpl.filter")
    MyAssertions.myAssert1Arg(sqrt(3), Failure(new NoSuchElementException(s"Predicate does not hold for $expectedSqrt3Aprox")), TriesImpl.filter, "TriesImpl.filter")

    MyAssertions.myAssert1Arg(sqrt(3), Success(expectedSqrt3Aprox + 5), TriesImpl.flatMap, "TriesImpl.flatMap")
    MyAssertions.myAssert1Arg(sqrt(-3), Failure(new NegativeNumberToSqrtException(-3.0)), TriesImpl.flatMap, "TriesImpl.flatMap")
  }

  def eithers(): Unit = {
    println("\nEither(s)")
    println("Either[A, B] - value of type A or B. 2 Cases: Left, Right.")
    println("Unlike Try[T] it is possible to choose different type than Throwable to represent exception")
    println("Either transformation resulting in exception does not convert value into Failure")

    MyAssertions.myAssert1Arg(2, Right(expectedSqrt2Aprox), sqrtEither, "sqrtEither")
    MyAssertions.myAssert1Arg(-2, Left(new NegativeNumberToSqrtException(-2.0).getMessage), sqrtEither, "sqrtEither")

    MyAssertions.myAssert1Arg(3, Right(expectedSqrt3Aprox), sqrtEither, "sqrtEither")
    MyAssertions.myAssert1Arg(-3, Left(new NegativeNumberToSqrtException(-3.0).getMessage), sqrtEither, "sqrtEither")
  }

  def manipulatingMatchingEithers(): Unit = {
    println("\nManipulating (matching) Either(s)")

    assert(Right(1).map(x => x + 1) == Right(2), "Right(1).map(x => x + 1) == Right(2)")
    assert(Left("unfortunate case").map((x: Int) => x + 1) == Left("unfortunate case"), "Left(\"unfortunate case\").map((x: Int) => x + 1) == Left(\"unfortunate case\")")

    MyAssertions.myAssert1Arg(sqrt(2), Right(expectedSqrt2Aprox + 1), EitherImpl.map, "EitherImpl.map")
    MyAssertions.myAssert1Arg(sqrt(-2), Left(new NegativeNumberToSqrtException(-2.0).getMessage), EitherImpl.map, "EitherImpl.map")

    MyAssertions.myAssert1Arg(Success(2), Right(2), EitherImpl.filter, "EitherImpl.filter")
    MyAssertions.myAssert1Arg(sqrt(2), Left(new NoSuchElementException(s"Predicate does not hold for $expectedSqrt2Aprox").getMessage), EitherImpl.filter, "EitherImpl.filter")

    MyAssertions.myAssert1Arg(sqrt(2), Right(expectedSqrt2Aprox + 5), EitherImpl.flatMap, "EitherImpl.flatMap")
    MyAssertions.myAssert1Arg(sqrt(-2), Left(new NegativeNumberToSqrtException(-2.0).getMessage), EitherImpl.flatMap, "EitherImpl.flatMap")

    MyAssertions.myAssert1Arg(sqrt(3), Right(expectedSqrt3Aprox + 1), EitherImpl.map, "EitherImpl.map")
    MyAssertions.myAssert1Arg(sqrt(-3), Left(new NegativeNumberToSqrtException(-3.0).getMessage), EitherImpl.map, "EitherImpl.map")

    MyAssertions.myAssert1Arg(Success(4), Right(4), EitherImpl.filter, "EitherImpl.filter")
    MyAssertions.myAssert1Arg(sqrt(3), Left(new NoSuchElementException(s"Predicate does not hold for $expectedSqrt3Aprox").getMessage), EitherImpl.filter, "EitherImpl.filter")

    MyAssertions.myAssert1Arg(sqrt(3), Right(expectedSqrt3Aprox + 5), EitherImpl.flatMap, "EitherImpl.flatMap")
    MyAssertions.myAssert1Arg(sqrt(-3), Left(new NegativeNumberToSqrtException(-3.0).getMessage), EitherImpl.flatMap, "EitherImpl.flatMap")

    println("\nEither.filterOrElse(Boolean, Left(B))")
    assert(Right(1).filterOrElse(x => x % 2 == 0, "Odd value") == Left("Odd value"), "Right(1).filterOrElse(x => x % 2 == 0, \"Odd value\") == Left(\"Odd value\")")

    println("Before Scala 2.12 - unbiased Either - explicit side specification needed - Left or Right")
    def triple(x: Int): Int = 3 * x
    println("def triple(x: Int): Int = 3 * x")
    def tripleEither(x: Either[String, Int]) = x.map(triple)
    println("def tripleEither(x: Either[String, Int]) = x.map(triple)")
    def generalizeTripleEither(obj: Any): Any = obj match {
      case stringOrInt: Either[String, Int] => tripleEither(stringOrInt)
      case _ => throw new UnimplementedCaseException(this, "generalizeTripleEither", obj)
    }
    MyAssertions.myAssert1Arg(Right(1), Right(3), generalizeTripleEither, "tripleEither")
    MyAssertions.myAssert1Arg(Left("Not a number"), Left("Not a number"), generalizeTripleEither, "tripleEither")
  }

  object TriesImpl {

    object Transformations {
      def mapTransform(obj: Any): Any = obj match {
        case int: Int => int + 1
        case double: Double => double + 1
        case _ => throw new UnimplementedCaseException(this, "mapTransform", obj)
      }

      def filterTransform(obj: Any): Boolean = obj match {
        case int: Int => int % 2 == 0
        case double: Double => double % 2 == 0
        case _ => throw new UnimplementedCaseException(this, "filterTransform", obj)
      }

      def flatMapTransform(obj: Any): Success[Any] = obj match {
        case int: Int => Success(int + 5)
        case double: Double => Success(double + 5)
        case _ => throw new UnimplementedCaseException(this, "flatMapTransform", obj)
      }
    }

    def map(obj: Any): Any = Tries.map(obj, Transformations.mapTransform)

    def filter(obj: Any): Any = Tries.filter(obj, Transformations.filterTransform)

    def flatMap(obj: Any): Any = Tries.flatMap(obj, Transformations.flatMapTransform)
  }

  object Tries {
    def map(obj: Any, transform: Any => Any): Any = obj match {
      case aTry: Try[Any] => aTry.map(transform)
      case _ => throw new UnimplementedCaseException(this, "map", obj)
    }

    def filter(obj: Any, transform: Any => Boolean): Any = obj match {
      case aTry: Try[Any] => aTry.filter(transform)
      case _ => throw new UnimplementedCaseException(this, "filter", obj)
    }

    def flatMap(obj: Any, transform: Any => Try[Any]): Any = obj match {
      case aTry: Try[Any] => aTry.flatMap(transform)
      case _ => throw new UnimplementedCaseException(this, "flatMap", obj)
    }
  }

  def sqrt(obj: Any): Any = obj match {
    case double: Double => sqrt(double)
    case int: Int => sqrt(int)
    case obj: Any => Failure(new BadNumberTypeToSqrtException(obj))
  }

  def sqrt(x: Double): Try[Double] = {
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

    if (x < 0) Failure(new NegativeNumberToSqrtException(x))
    else Success(sqrtIter(x, 1))
  }

  def abs(x: Double): Double = {
    if (x < 0) -x
    else x
  }

  def sqrtEither(obj: Any): Any = obj match {
    case double: Double => sqrtEither(double)
    case int: Int => sqrtEither(int)
    case obj: Any => new BadNumberTypeToSqrtException(obj).getMessage
  }

  def sqrtEither(x: Double): Either[String, Double] = {
    if (x < 0) Left(new NegativeNumberToSqrtException(x).getMessage)
    else sqrt(x) match {
      case Success(double: Double) => Right(double)
      case Failure(throwable: Throwable) => Left(throwable.getMessage)
    }
  }

  object EitherImpl {
    def map(obj: Any): Any = matchTryToEither(Tries.map(obj, Transformations.mapTransform), "map")

    def filter(obj: Any): Any = matchTryToEither(Tries.filter(obj, Transformations.filterTransform), "filter")

    def flatMap(obj: Any): Any = matchTryToEither(Tries.flatMap(obj, Transformations.flatMapTransform), "flatMap")

    def matchTryToEither(obj: Any, functionName: String): Any = obj match {
      case aTry: Try[Any] => aTry match {
        case Success(int: Int) => Right(int)
        case Success(double: Double) => Right(double)
        case Failure(throwable: Throwable) => Left(throwable.getMessage)
        case _ => throw new UnimplementedCaseException(this, functionName, obj)
      }
      case _ => throw new UnimplementedCaseException(this, functionName, obj);
    }
  }

}
