package org.exercises.scala

import main.MainApp
import org.exercises.scala.conv.syntax.SyntacticConveniences
import org.exercises.scala.evals.defs.DefinitionsAndEvaluations
import org.exercises.scala.func.loops.FunctionalLoops
import org.exercises.scala.func.order.higher.HigherOrderFunctions
import org.exercises.scala.info.structure.StructuringInformation
import org.exercises.scala.lib.standard.StandardLibrary
import org.exercises.scala.ool.ObjectOrientedProgramming
import org.exercises.scala.prog.imper.ImperativeProgramming
import org.exercises.scala.recursion.tail.TailRecursion
import org.exercises.scala.terms.types.TermsAndTypes

object ScalaExercisesOrg {
  /**
   * Source: https://www.scala-exercises.org/scala_tutorial
   */

  def main() {
    MainApp.announceSourceName(this.getClass.getSimpleName)
    TermsAndTypes.main()
    DefinitionsAndEvaluations.main()
    FunctionalLoops.main()
    TailRecursion.main()
    StructuringInformation.main()
    HigherOrderFunctions.main()
    StandardLibrary.main()
    SyntacticConveniences.main()
    ObjectOrientedProgramming.main()
    ImperativeProgramming.main()
  }

}
