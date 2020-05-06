package org.exercises.scala.info.structure

object StructuringInformation {

  def main(): Unit = {
    caseClassesAggregatingInformation()
    sealedTraitsAndAlternatives()
  }

  def caseClassesAggregatingInformation(): Unit = {
    println("\nCase classes - aggregating information")

    case class Note(name: String, duration: String, octave: Int)
    println("case class Note(name: String, duration: String, octave: Int)")
    println("case class introduces new type \"Note\"")

    val c3 = Note("C", "Quarter", 3)
    println(s"c3: $c3 | c3 = Node{ name: ${c3.name}, duration: ${c3.duration}, c3.octave: ${c3.octave} }")
    println("c3 aggregates arguments from constructor")
    assert(c3 == Note("C", "Quarter", 3), "c3 == Note(\"C\", \"Quarter\", 3)")
    assert(c3.name == "C", "c3.name == \"C\"")
    assert(c3.duration == "Quarter", "c3.duration == \"Quarter\"")
    assert(c3.octave == 3, "c3.octave == 3")
  }

  def sealedTraitsAndAlternatives(): Unit = {
    sealed trait Symbol
    case class Note(name: String, duration: String, octave: Int) extends Symbol
    case class Rest(duration: String) extends Symbol
    case class Start() extends Symbol
    case class End() extends Symbol

    def getSymbolDuration(symbol: Symbol): String = symbol match {
      case Note(name, duration, octave) => duration
      case Rest(duration) => duration
      case Start() | End() => null
      case _ => throw new Exception("Unhandled type")
    }

    def nonExhaustiveDuration(symbol: Symbol): String = symbol match {
      case Rest(duration) => duration
    }

    def sealedTraits(): Unit = {
      println("sealed trait Symbol")
      println("case class Note(name: String, duration: String, octave: Int) extends Symbol")
      println("case class Rest(duration: String) extends Symbol")
      println("case class Start() extends Symbol")
      println("case class End() extends Symbol")

      println("\nsealed trait can be one of its extending case classes but nothing else")
      println("Symbol is embodied by fixed set of alternatives (extending case classes: Note or Rest)")
      println("(Musical) Symbol(s) can be Note(s) or Rest(s) but nothing else")
      println("sealed trait introduces new type but no constructor")
      println("constructors are defined by alternatives extending sealed trait")

      val symbol1: Symbol = Note("C", "Quarter", 3)
      val symbol2: Symbol = Rest("Whole")
      println("symbol1: " + symbol1)
      println("symbol2: " + symbol2)
    }

    def patternMatching(): Unit = {
      println("\nPatter matching")
      println("Symbol has no members or useful methods, so we can't (really) manipulated one")
      println("To manipulated Symbol we need to distinguish between different case classes extending it")
      println("This can be done via Pattern matching")
      println()

      println("def getSymbolDuration(symbol: Symbol): String = symbol match {")
      println("\tcase Note(name, duration, octave) => duration")
      println("\tcase Rest(duration) => duration")
      println("\tcase Start() | End() => null")
      println("\tcase _ => throw new Exception(\"Unhandled type\")")
      println("}")

      val symbol1: Symbol = Note("C", "Quarter", 3)
      val symbol2: Symbol = Rest("Whole")
      println("symbol1: " + symbol1)
      println("symbol2: " + symbol2)
      println("getSymbolDuration(symbol1): " + getSymbolDuration(symbol1))
      println("getSymbolDuration(symbol2): " + getSymbolDuration(symbol2))

      println("\nmatch checks if variable symbol is of type Note, extracts its fields: name, duration, octave, evaluates expression after arrow (=>)")
      println("otherwise checks if symbol is Rest (extracts fields, evaluates expression on the right side of the arrow)")
      println("otherwise checks if symbol is Start or End (fields, evaluation)")
      println("otherwise matches everything with _ and throws exception (evaluation)")

      println("\"Note(name, duration, octave)\" - is a constructor pattern")
      println("Constructor pattern - matches all values of type Note (given type) constructed with arguments matching pattern (name, duration, octave)")
      println("Pattern: \"name, duration, octave\" is called a variable pattern")
      println("Variable pattern - matches any value(s) and binds its name(s) to given value")
    }

    def generalPatternMatching(): Unit = {
      val e: Int = 0
      val p0 = 0
      val p1 = 1
      val matchExpression = e match {
        case `p0` => 0
        case `p1` => 1
        // ...
        case _ => Double.PositiveInfinity
      }
      println()
      println("val e: Int = 0")
      println("val p0 = 0")
      println("val p1 = 1")
      println("val matchExpression = e match {")
      println("\tcase `p0` => 0")
      println("\tcase `p1` => 1")
      println("\t// ...")
      println("\tcase _ => Double.PositiveInfinity")
      println("}")
      assert(matchExpression == e, "matchExpression == e")

      println("Above expression matches the value of the SELECTOR e with PATTERNS p1, p2, ..., _ in the ORDER in which they are written")
      println("WHOLE match expression is rewritten (evaluated) to the first case where the pattern matches the selector e (to the expression on the right side of the arrow)")
      println("References to pattern variables are replaced by the corresponding parts in selector")
      println("\"duration\" reference in \"case Note(name, duration, octave)\" is replaced by reference to \"symbol.duration\"")
    }

    def exhaustivity(): Unit = {
      println("\nExhaustivity\n")
      println("def nonExhaustiveDuration(symbol: Symbol): String = symbol match {")
      println("\tcase Rest(duration) => duration")
      println("}")
      println("nonExhaustiveDuration(Rest(\"Whole\")): " + nonExhaustiveDuration(Rest("Whole")))
      println("Compiler will give us a warning:")
      println("\"Warning:(105, 9) match may not be exhaustive.\nIt would fail on the following inputs: End(), Note(_, _, _), Start()\n        symbol match {\"")
      println("Because Symbol is a SEALED trait. It guarantees that number of possible cases of symbol (the number is fixed).")
    }

    def equals(): Unit = {
      println("\nEquals")
      println("case classes aggregate values")
      println("comparing case class instances compares their values")
      val c3 = Note("C", "Quarter", 3)
      val otherC3 = Note("C", "Quarter", 3)
      val f3 = Note("F", "Quarter", 3)
      assert(c3 == Note("C", "Quarter", 3), "c3 == otherC3")
      assert(c3 == otherC3, "c3 == otherC3")
      assert(c3 != f3, "c3 != f3")
    }

    def enumerations(): Unit = {
      println("\nEnumerations")
      println("Previous implementation of type Note allowed for invalid names and durations")
      val noteWithInvalidFieldValues = Note("not a name", "not a duration", 3)
      println("val noteWithInvalidFieldValues = Note(\"not a name\", \"not a duration\", 3)")
      println("noteWithInvalidFieldValues: " + noteWithInvalidFieldValues)
      println("The solution to this problem is enumeration")
      println("Enumeration is implemented using sealed trait but instead of using case classes we use objects because they aggregate no information\n")

      def enumerationReImplementation(): Unit = {
        sealed trait NoteName
        case object A extends NoteName
        case object B extends NoteName
        case object C extends NoteName
        case object D extends NoteName
        case object E extends NoteName
        case object F extends NoteName
        case object G extends NoteName

        println("sealed trait NoteName")
        println("case object A extends NoteName")
        println("case object B extends NoteName")
        println("case object C extends NoteName")
        println("case object D extends NoteName")
        println("case object E extends NoteName")
        println("case object F extends NoteName")
        println("case object G extends NoteName")
        println()

        sealed trait NoteDuration
        object NoteDuration {

          case object Whole extends NoteDuration

          case object Half extends NoteDuration

          case object Quarter extends NoteDuration

          case object Eighth extends NoteDuration

          case object Sixteenth extends NoteDuration

          case object ThirtySecond extends NoteDuration

          case object SixtyFourth extends NoteDuration

        }

        /**
         * Source: https://human.libretexts.org/Bookshelves/Music/Understanding_Basic_Music_Theory_(OpenSTAX)/2%3A_Notation/2.2%3A_Time/2.2.1%3ADuration%3A_Note_Lengths_in_Written_Music
         * Licence: https://creativecommons.org/licenses/by/4.0/
         * Adaptation changes were made by Jacek Oleś
         */

        println("sealed trait NoteDuration")
        println("object NoteDuration {")
        println("case object Whole extends NoteDuration")
        println("case object Half extends NoteDuration")
        println("case object Quarter extends NoteDuration")
        println("}\n")

        case class Note(name: NoteName, duration: NoteDuration, octave: Int) extends Symbol
        println("case class Note(name: NoteName, duration: NoteDuration, octave: Int) extends Symbol")

        val c3 = Note(C, NoteDuration.Quarter, 3)
        println("val c3 = Note(C, NoteDuration.Quarter, 3)")
        println(s"c3: $c3 | c3 = { name: ${c3.name}, duration: ${c3.duration}, octave: ${c3.octave} }")
      }

      enumerationReImplementation()
    }

    def algebraicDataTypes(): Unit = {
      println("\nAlgebraic Data Types")
      println("Data types defined as Sealed traits and Case classes are called ALGEBRAIC DATA TYPES")
      println("Algebraic data type can be thought of as a set of possible values")

      isRelationship()
      hasRelationShip()

      def isRelationship() {
        println("\nIf program's domain can be formulated as X is Y (or Z) it can be expressed as sealed tait (and case classes)")
        println("For example: Symbol is Note or Rest")

        sealed trait Symbol
        case class Note() extends Symbol
        case class Rest() extends Symbol

        println("sealed trait Symbol")
        println("case class Note() extends Symbol")
        println("case class Rest() extends Symbol")
      }

      def hasRelationShip(): Unit = {
        println("\nIf program's domain can be formulated as X has Y (and Z) it can be expressed as case class")
        println("For example: Note has Name and Duration and Octave")

        case class NewNote(name: String, duration: String, octave: Int) extends Symbol
      }
    }

    def exercise(): Unit = {
      sealed trait NoteDuration
      object NoteDuration {

        case object Whole extends NoteDuration

        case object Half extends NoteDuration

        case object Quarter extends NoteDuration

        case object Eighth extends NoteDuration

        case object Sixteenth extends NoteDuration

        case object ThirtySecond extends NoteDuration

        case object SixtyFourth extends NoteDuration

      }

      def fractionOfWhole(duration: NoteDuration): Double = duration match {
        case NoteDuration.Whole => 1.0
        case NoteDuration.Half => 1.0/2
        case NoteDuration.Quarter => 1.0/4
        case NoteDuration.Eighth => 1.0/8
        case NoteDuration.Sixteenth => 1.0/16
        case NoteDuration.ThirtySecond => 1.0/32
        case NoteDuration.SixtyFourth => 1.0/64
      }

      assert(1.0 == fractionOfWhole(NoteDuration.Whole), "1.0 == fractionOfWhole(NoteDuration.Whole)")
      assert(0.5 == fractionOfWhole(NoteDuration.Half), "0.5 == fractionOfWhole(NoteDuration.Half)")
      assert(0.25 == fractionOfWhole(NoteDuration.Quarter), "0.25 == fractionOfWhole(NoteDuration.Quarter)")
      assert(0.125 == fractionOfWhole(NoteDuration.Eighth), "0.125 == fractionOfWhole(NoteDuration.Eighth)")
      assert(0.0625 == fractionOfWhole(NoteDuration.Sixteenth), "0.0625 == fractionOfWhole(NoteDuration.Sixteenth)")
      assert(0.03125 == fractionOfWhole(NoteDuration.ThirtySecond), "0.03125 == fractionOfWhole(NoteDuration.ThirtySecond)")
      assert(0.015625 == fractionOfWhole(NoteDuration.SixtyFourth), "0.015625 == fractionOfWhole(NoteDuration.SixtyFourth)")
    }

    println("Sealed Traits and Alternatives")
    sealedTraits()
    patternMatching()
    generalPatternMatching()
    exhaustivity()
    equals()
    enumerations()
    algebraicDataTypes()
    exercise()
  }
}
