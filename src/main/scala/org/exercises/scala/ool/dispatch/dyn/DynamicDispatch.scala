package org.exercises.scala.ool.dispatch.dyn

object DynamicDispatch {

  class DynamicMethod[A, R] {
    val methods = scala.collection.mutable.ListBuffer[PartialFunction[A, R]]()

    def defMethod(method: PartialFunction[A, R]) = {
      methods += method
    }

    def apply(args: A): R = {
      methods.reverse.find(_.isDefinedAt(args)) match {
        case Some(f) => f(args)
        case None => throw new Exception("huh?")
      }
    }
  }

  def defMultiMethod[A, R] = new DynamicMethod[A, R]
}
