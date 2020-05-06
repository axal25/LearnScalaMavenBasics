package util

import org.exercises.scala.lib.standard.UnimplementedCaseException

import scala.util.Failure

object MyAssertions {

  def myAssert(inputObj: Any, expectedOutput: Any, transform: Any => Any, transformation: String): Unit = {
    val actualOutcome = transform(inputObj)
    val assertion = s"${transformation}( ${inputObj} ) == ${expectedOutput}"
    val difference = s"$expectedOutput" diff s"$actualOutcome"
    val intersect = s"$expectedOutput" intersect s"$actualOutcome"
    val assertionExceptionMsg = s"$assertion Actual outcome: $actualOutcome Difference: $difference Intersect: $intersect"
    (actualOutcome, expectedOutput) match {
      case (actualFailure: Failure[Throwable], expectedFailure: Failure[Throwable]) => {
        // Throwables ARE DIFFERENT
        // toString the same
        assert(actualOutcome.toString == expectedOutput.toString, assertionExceptionMsg)
      }
      case (actualThrowable: Throwable, expectedThrowable: Throwable) => {
        // Throwables ARE DIFFERENT
        assert(actualThrowable != expectedThrowable, s"$assertion Actual outcome: $actualOutcome")
        assert(actualThrowable.toString == expectedThrowable.toString, assertionExceptionMsg)
        // toString the same
        assert(actualOutcome.toString == expectedOutput.toString, assertionExceptionMsg)
      }
      case _ => {
        assert(actualOutcome == expectedOutput, assertionExceptionMsg)
      }
    }
    println(assertion)
  }

  def myAssert2Arg(inputObj1: Any, inputObj2: Any, expectedOutput: Any, transform: (Any, Any) => Any, transformation: String): Unit = {
    val actualOutcome = transform(inputObj1, inputObj2)
    val assertion = s"${transformation}( ${inputObj1}, ${inputObj2} ) == ${expectedOutput}"
    val difference = s"$expectedOutput" diff s"$actualOutcome"
    val intersect = s"$expectedOutput" intersect s"$actualOutcome"
    val assertionExceptionMsg = s"$assertion Actual outcome: $actualOutcome Difference: $difference Intersect: $intersect"
    (actualOutcome, expectedOutput) match {
      case (actualFailure: Failure[Throwable], expectedFailure: Failure[Throwable]) => {
        // Throwables ARE DIFFERENT
        // toString the same
        assert(actualOutcome.toString == expectedOutput.toString, assertionExceptionMsg)
      }
      case (actualThrowable: Throwable, expectedThrowable: Throwable) => {
        // Throwables ARE DIFFERENT
        assert(actualThrowable != expectedThrowable, s"$assertion Actual outcome: $actualOutcome")
        assert(actualThrowable.toString == expectedThrowable.toString, assertionExceptionMsg)
        // toString the same
        assert(actualOutcome.toString == expectedOutput.toString, assertionExceptionMsg)
      }
      case _ => {
        assert(actualOutcome == expectedOutput, assertionExceptionMsg)
      }
    }
    println(assertion)
  }

  def myAssertSeq(inputObjs: Seq[Any], expectedOutputs: Seq[Any], transform: Any => Any, transformation: String): Unit = {
    (inputObjs, expectedOutputs) match {
      case (Seq(inputObjsHead, inputObjsTail@_*), Seq(expectedOutputsHead, expectedOutputsTail@_*)) => {
        myAssert(inputObjsHead, expectedOutputsHead, transform, transformation)
        myAssertSeq(inputObjsTail, expectedOutputsTail, transform, transformation)
      }
      case _ => throw new UnimplementedCaseException(this, "myAssert", inputObjs, expectedOutputs)
    }
  }
}
