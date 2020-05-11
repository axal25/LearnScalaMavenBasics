package util

import org.exercises.scala.lib.standard.UnimplementedCaseException

import scala.util.Failure

object MyAssertions {

  def myAssert1Arg(inputObj: Any, expectedOutput: Any, transform: Any => Any, transformation: String): Unit = {
    val actualOutcome = transform(inputObj)
    val assertion = s"${transformation}( ${inputObj} ) == \n\t\t${expectedOutput}"
    myAssert(expectedOutput, actualOutcome, assertion)
  }

  def myAssert2Arg(inputObj1: Any, inputObj2: Any, expectedOutput: Any, transform: (Any, Any) => Any, transformation: String): Unit = {
    val actualOutcome = transform(inputObj1, inputObj2)
    val assertion = s"${transformation}( ${inputObj1}, ${inputObj2} ) == \n\t\t${expectedOutput}"
    myAssert(expectedOutput, actualOutcome, assertion)
  }

  @scala.annotation.tailrec
  def myAssertSeq(inputObjs: Seq[Any], expectedOutputs: Seq[Any], transform: Any => Any, transformation: String): Unit = {
    (inputObjs, expectedOutputs) match {
      case (Seq(inputObjsHead, inputObjsTail@_*), Seq(expectedOutputsHead, expectedOutputsTail@_*)) => {
        myAssert1Arg(inputObjsHead, expectedOutputsHead, transform, transformation)
        myAssertSeq(inputObjsTail, expectedOutputsTail, transform, transformation)
      }
      case _ => throw new UnimplementedCaseException(this, "myAssert", inputObjs, expectedOutputs)
    }
  }

  def myAssert(expectedOutput: Any, actualOutcome: Any, assertion: String): Unit = {
    def getDifference(actualOutcome: Any, expectedOutput: Any): String = {
      val difference = s"$expectedOutput" diff s"$actualOutcome"
      s""""$difference""""
    }

    def getIntersect(actualOutcome: Any, expectedOutput: Any): String = {
      val intersect = s"$expectedOutput" intersect s"$actualOutcome"
      s""""$intersect""""
    }

    def getAssertionExceptionMsg(actualOutcome: Any, assertion: String, difference: String, intersect: String): String = {
      s"\n\t$assertion\n\tActual outcome: $actualOutcome\n\tDifference: $difference\n\tIntersect: $intersect"
    }

    val difference = getDifference(actualOutcome, expectedOutput)
    val intersect = getIntersect(actualOutcome, expectedOutput)
    val assertionExceptionMsg = getAssertionExceptionMsg(actualOutcome, assertion, difference, intersect)
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
}
