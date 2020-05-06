package org.exercises.scala.lib.standard.option

class NegativeNumberToSqrtException(msg: String, cause: Throwable) extends Exception(msg, cause) {
  def this(obj: Any) = {
    this(s"${NegativeNumberToSqrtException.staticMsgPart}$obj.", null)
  }
}

private object NegativeNumberToSqrtException {
  val staticMsgPart = "Could not calculate square root for negative number: "
}
