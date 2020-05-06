package org.exercises.scala.lib.standard

class UnimplementedCaseException(msg: String) extends Exception(msg) {
  def this(callingClassObject: Any, functionName: String, objs: Any*) {
    this(s"${callingClassObject.getClass.getSimpleName} - ${functionName} - Unimplemented case for object(s): ${UnimplementedCaseException.objToString(objs: _*)}.")
  }
}

object UnimplementedCaseException {
  def objToString(objs: Any*): String = {
    @scala.annotation.tailrec
    def iter(prevString: String, objs: Any*): String = objs match {
      case Seq(head) => appendString(prevString, head)
      case Seq(head, tail @ _*) => {
        val curString = appendString(prevString, head)
        iter(curString, tail: _*)
      }
      case _ => throw new Error("Unhandled case.")
    }

    //todo rework
    @scala.annotation.tailrec
    def iter2(prevString: String, objs: Any*): String = objs match {
      case Seq(head) => appendString(prevString, head)
      case Seq(head, tail @ _*) => {
        val curString = appendString(prevString, head)
        iter2(curString, tail: _*)
      }
      case _ => throw new Error("Unhandled case.")
    }

    def appendString(prevString: String, obj: Any): String = {
      val objRepresentation = s"${obj.toString}: ${obj.getClass.getName}"
      if(isEmpty(prevString)) s"${objRepresentation}"
      else s"$prevString, $objRepresentation"
    }

    def isEmpty(string: String): Boolean = string == null || string.isEmpty

    iter(null, objs: _*)
  }
}
