package org.exercises.scala.lib.standard.option

class BadNumberTypeToSqrtException(msg: String, cause: Throwable) extends Exception(msg, cause) {
  def this(obj: Any) {
    this(s"${BadNumberTypeToSqrtException.staticMsgPart}${obj.getClass.getSimpleName}", null)
  }
}

private object BadNumberTypeToSqrtException {
  val staticMsgPart = "Bad object type to square root: "
}