package org.exercises.scala.prog.imper.remind.model.sub

object ReminderSubstitutionModel {
  @scala.annotation.tailrec
  def iterate(iterAmountToDo: Int, func: Int => Int, prevIterResult: Int): Int = {
    if (iterAmountToDo == 0) prevIterResult
    else iterate(iterAmountToDo - 1, func, func(prevIterResult))
  }

  def square(param: Int): Int = param * param
}
