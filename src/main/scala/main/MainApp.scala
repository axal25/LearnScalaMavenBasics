package main

import jvm.the.rock.ScalaAtLightSpeed
import org.exercises.scala.ScalaExercisesOrg

/**
 * @author ${user.name}
 */
// extends App - have already main method implemented, which acts as main static method in Java
// this object body is then run
object MainApp extends App { // object body start

  myMain(args)

  def stringArrayToString(strings: Array[String]) = strings.foldLeft("")((a, b) => a + ", " + b)

  def myMain(args: Array[String]) {
    println("Hello World!")
    println("stringArrayToString: " + stringArrayToString(args))
    ScalaAtLightSpeed.main()
    ScalaExercisesOrg.main()
  }

  def announceSourceName(name: String): Unit = {
    val upperCaseNameArray: Array[String] = for {
      letter <- name.replace("$", "").toCharArray
      lowerCaseLetterOrSpaceAndUpperCaseLetter <- getLowerCaseLetterOrSpaceAndUppercaseLetter(letter)
    } yield s"$lowerCaseLetterOrSpaceAndUpperCaseLetter"
    val upperCaseNameString: String = (">>>" +: upperCaseNameArray :+ " <<<").mkString("").toUpperCase()
    val atLeastMinimumLengthUpperCaseNameString: String = f"$upperCaseNameString%1s"
    val amountOfDashes: Int = 90 - (atLeastMinimumLengthUpperCaseNameString.length / 2);
    val dashes: String = "-" * amountOfDashes;
    println(f"$dashes $atLeastMinimumLengthUpperCaseNameString $dashes")
  }

  def getLowerCaseLetterOrSpaceAndUppercaseLetter(letter: Char): String = letter match {
    case letter if (letter > 64 & letter < 91) => s" $letter"
    case _ => s"$letter"
  }

} // object body end
