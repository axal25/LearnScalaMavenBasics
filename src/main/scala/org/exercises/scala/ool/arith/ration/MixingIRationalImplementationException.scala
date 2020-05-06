package org.exercises.scala.ool.arith.ration

import org.exercises.scala.lib.standard.UnimplementedCaseException

class MixingIRationalImplementationException(msg: String, cause: Throwable = null) extends Exception(msg, cause) {
  def this(first: IRational, irationals: IRational*) {
    this(s"Could not determinate which implementation's constructor to use. Cannot mix implementation of IRational. Implementations' object: ${UnimplementedCaseException.objToString(first +: irationals: _*)}")
  }
}

