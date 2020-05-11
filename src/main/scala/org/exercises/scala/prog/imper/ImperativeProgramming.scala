package org.exercises.scala.prog.imper

import org.exercises.scala.prog.imper.remind.model.sub.ReminderSubstitutionModel

object ImperativeProgramming {

  def main(): Unit = {
    introductionToImperativeProgramming()
    statefulObjects()
    // todo
    //    https://www.scala-exercises.org/scala_tutorial/imperative_programming

  }

  def introductionToImperativeProgramming(): Unit = {
    println("\nImperative programming")

    println("For programs that do not have any side effects - concept of time is not important")
    println("For programs that terminate - any sequence of actions gives the same result")
    println("This is reflected in substitution model")

    println("\nSubstitution model")
    println("\t1. name --evaluation--> right side of definition")
    println("\t2. function --evaluation--> right side")
    println("\t\t2.1. formal parameters --> values")

    println("def iterate(iterAmountToDo: Int, func: Int => Int, funcParam: Int): Int = {")
    println("\tif (iterAmountToDo == 0) funcParam")
    println("\telse iterate(iterAmountToDo - 1, func, func(funcParam))")
    println("}")
    println
    println("def square(param: Int) = param * param")

    println("\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) -->")
    assert(
      ReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == {
        if (1 == 0) 3
        else ReminderSubstitutionModel.iterate(1 - 1, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(3))
      },
      "\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == {\n" +
        "\tif (1 == 0) 3\n" +
        "\telse ReminderSubstitutionModel.iterate(1 - 1, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(3))\n" +
        "}"
    )
    println("{\n" +
      "\tif (1 == 0) 3\n" +
      "\telse ReminderSubstitutionModel.iterate(1 - 1, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(3))\n" +
      "} -->")

    assert(
      ReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) ==
        ReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(3)),
      "\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == \n" +
        "\tReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(3))"
    )
    println("ReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(3)) -->")

    assert(
      ReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) ==
        ReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, 3 * 3),
      "\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == \n" +
        "\tReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, 3*3)"
    )
    println("ReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, 3*3) -->")

    assert(
      ReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) ==
        ReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, 9),
      "\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == \n" +
        "\tReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, 9)"
    )
    println("ReminderSubstitutionModel.iterate(0, ReminderSubstitutionModel.square, 9) -->")

    assert(
      ReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == {
        if (0 == 0) 9
        else ReminderSubstitutionModel.iterate(0 - 1, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(9))
      },
      "\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == {\n" +
        "\tif (0 == 0) 9\n" +
        "\telse ReminderSubstitutionModel.iterate(0 - 1, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(9))\n" +
        "}"
    )
    println("{\n" +
      "\tif (0 == 0) 9\n" +
      "\telse ReminderSubstitutionModel.iterate(0 - 1, ReminderSubstitutionModel.square, ReminderSubstitutionModel.square(9))\n" +
      "} -->")

    assert(
      ReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == 9,
      "\nReminderSubstitutionModel.iterate(1, ReminderSubstitutionModel.square, 3) == 9"
    )
    println("9;")

    println("Rewriting can be done anywhere in a term, all rewritings which terminate lead to the same solution")
    val lambdaSmallCharCode: Char = 955
    println(s"This is a result of $lambdaSmallCharCode-calculus (LAMBDA) - theory behind functional programming")
  }

  def statefulObjects(): Unit = {
    println("\nStateful Objects")
    println("World inside of program can be described as a set of objects")
    println("Some of those objects can change states over course of time")
    println("Object has state if its behaviour is influenced by its history")
    println("Bank account - answer to withdraw money can be declined after few withdrawals, and may be again approved after monthly deposit")
  }
}
