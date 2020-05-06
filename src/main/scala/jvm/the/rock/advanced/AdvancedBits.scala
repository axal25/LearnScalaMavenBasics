package jvm.the.rock.advanced

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object AdvancedBits {

  def main(): Unit = {
    println()
    lazyEvaluations()
    optionAndTryTypes()
    asynchronousProgramming()
    implicitBasics()
  }

  def lazyEvaluations(): Unit = {
    println("LAZY EVALUATIONS are not evaluated until they are used for the first time")
    lazy val aLazyValue = 2
    println("before aLazyValueWithSideEffect definition {")
    lazy val aLazyValueWithSideEffect = {
      println("aLazyValueWithSideEffect used")
      43
    }
    println("} after aLazyValueWithSideEffect definition")
    val notLazyValue = aLazyValueWithSideEffect
    println("LAZY EVALUATIONS are useful in infinite collections")
  }

  def optionAndTryTypes(): Unit = {
    println("\nOption, Try types are their own types")
    println("They are kind of \"pseudo-collections\"")
    options()
    tries()
  }

  def options(): Unit = {
    def methodWhichCanReturnNull(): String = "Hello scala"
    // normally
    if (methodWhichCanReturnNull() == null) {
      // defensive action
    }
    // in Scala
    val anOption = Option(methodWhichCanReturnNull())
    println("\nOption = \"collection\" which contains at most one element, 1 or 0 elements, Some(value) or None")
    val processedString = anOption match {
      case Some(string) => println(s"I have obtained a valid string: $string")
      case None => println("I have obtained None")
    }
  }

  def tries(): Unit = {
    def methodWhichCanThrowException(): String = throw new RuntimeException
    // normally
    try {

    } catch {
      case e: Exception => s"Caught exception $e"
    }

    //in Scala
    val aTry: Try[String] = Try(methodWhichCanThrowException())
    println("\nTry1 = \"collection\" which contains exactly 1 element, Value or Exception")

    val stringProcessing = aTry match {
      case Success(validValue) => s"I have obtained a valid string: $validValue"
      case Failure(exception) => s"I have obtained an Exception: $exception"
    }
    println(stringProcessing)
  }

  def asynchronousProgramming(): Unit = {
    println("\nAsynchronous programming in Scala")
    println("evaluating something on another thread")
    println("using \"pseudo-collection\" Future")
    println("use of Future requires import of: ")
    println("\timport scala.concurrent.ExecutionContext.Implicits.global")

    println("\nFuture type is \"pseudo-collection\"");
    println("Future is compatible with map, flatMap, filter")
    println("Those \"pseudo-collections\" (Option, Try, Future) are monads\n")

    val aFuture1 = Future({ // Future.apply({...})
      println("Loading... On another thread... #1")
      Thread.sleep(1000)
      println("I have evaluated something on another thread. #1")
      67
    })

    val aFuture2: Future[Int] = Future {
      println("Loading... On another thread... #2")
      Thread.sleep(1000)
      println("I have evaluated something on another thread. #2")
      67
    }

    println("aFuture1: " + aFuture1)
    println("waiting...")
    Thread.sleep(1500)
    println("aFuture1: " + aFuture1)
  }

  def implicitBasics(): Unit = {
    println("\nImplicit arguments")

    def aMethodWithImplicitArg(implicit arg: Int) = arg + 1

    implicit val myImplicitInt = 46
    println("aMethodWithImplicitArg: " + aMethodWithImplicitArg)

    println("\nImplicit conversions")
    implicit class MyRichInteger(n: Int) {
      def isEven() = n % 2 == 0
    }
    println("23.isEven(): " + 23.isEven())
  }
}
