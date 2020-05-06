package org.exercises.scala.ool.arith.ration

import org.exercises.scala.lib.standard.UnimplementedCaseException

object iRationalGen {
  def +(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.+(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.+", obj1, obj2)
  }

  def -(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.-(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.-", obj1, obj2)
  }

  def *(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.*(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.*", obj1, obj2)
  }

  def /(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational./(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen./", obj1, obj2)
  }

  def add(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.add(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.add", obj1, obj2)
  }

  def sub(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.sub(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.sub", obj1, obj2)
  }

  def mul(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.mul(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.mul", obj1, obj2)
  }

  def div(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (rational1: IRational, rational2: IRational) => iRational.div(rational1, rational2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.div", obj1, obj2)
  }

  def reduce(obj1: Any): Any = obj1 match {
    case rational1: IRational => iRational.reduce(rational1)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.reduce", obj1)
  }

  def greatestCommonDivisor(obj1: Any, obj2: Any): Any = (obj1, obj2) match {
    case (int1: Int, int2: Int) => iRational.greatestCommonDivisor(int1, int2)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.greatestCommonDivisor", obj1, obj2)
  }

  def toString(obj1: Any): Any = obj1 match {
    case rational1: IRational => iRational.toString(rational1)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.toString", obj1)
  }

  def toStringInterpretation(obj1: Any): Any = obj1 match {
    case rational1: IRational => iRational.toStringInterpretation(rational1)
    case _ => throw new UnimplementedCaseException(this, "iRationalGen.toStringInterpretation", obj1)
  }
}
