package org.exercises.scala.lib.standard

class UnimplementedCaseException(msg: String) extends Exception(msg) {
  def this(callingClassObject: Any, functionName: String, objs: Any*) {
    this(UnimplementedCaseException.toString(callingClassObject, functionName, objs: _*))
  }
}

object UnimplementedCaseException {
  def toString(callingClassObject: Any, functionName: String, objs: Any*): String = {
    s"${callingClassObject.getClass.getSimpleName} - ${functionName} - Unimplemented case for object(s): ${UnimplementedCaseException.objsToString(objs: _*)}."
  }

  def objsToString(objs: Any*): String = {
    @scala.annotation.tailrec
    def iter(prevResult: String, objs: Any*): String = objs match {
      case Nil => prevResult
      case Seq(head, tail @ _*) => {
        val curString = appendString(prevResult, head)
        iter(curString, tail: _*)
      }
      case _ => throw new Error("Unhandled case.")
    }

    def appendString(prevString: String, obj: Any): String = {
      val objRepresentation = s"${obj.toString}: ${obj.getClass.getSimpleName}"
      if(isEmpty(prevString)) s"${objRepresentation}"
      else s"$prevString, $objRepresentation"
    }

    def isEmpty(string: String): Boolean = string == null || string.isEmpty

    iter(null, objs: _*)
  }
}
