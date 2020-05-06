package jvm.the.rock.func.prog

object FunctionalProgramming {

  def main(): Unit = {
    oolScala()

    /**
     * Scala runs on JVM - build for OOL
     *
     * Functional programming wants to be able to:
     * - compose functions
     * - pass functions as args
     * - return functions as results
     *
     * Conclusion: FunctionX = Function1, Function2, ... Function 22
     * 22 arguments is maximum
     */
    defineAnIteratorFunction()
    defineAConcatenatorFunction()
    // All Scala functions are instances of these FunctionX types
    syntaxSugars()
    higherOrderFunctions()
    // Collections
    lists()
    sequences()
    sets()
    vectors()
    ranges()
    tuples()
    maps()
  }

  def oolScala(): Unit = {
    // Scala is OOL
    class Person(name: String) {
      def apply(age: Int): Unit = {
        println(s"I have aged $age")
      }
    }

    println("\r\nval bob = new Person(\"Bob\")")
    val bob = new Person("Bob")
    print("bob.apply(2): ")
    bob.apply(2)
    print("bob(1): ")
    bob(1)
  }

  def defineAnIteratorFunction(): Unit = {
    val simpleIncrementer = new Function1[Int, Int] {
      override def apply(arg: Int): Int = arg + 1
    }
    assert(
      24 == simpleIncrementer.apply(23),
      "24 == simpleIncrementer.apply(23)"
    )
    assert(
      simpleIncrementer.apply(23) == simpleIncrementer(23),
      "simpleIncrementer.apply(23) == simpleIncrementer(23)"
    )
    // defined a function
  }

  def defineAConcatenatorFunction(): Unit = {
    val stringConcatenator = new Function2[String, String, String] {
      override def apply(v1: String, v2: String): String = v1 + v2
    }

    assert("I love Scala" == stringConcatenator("I love", " Scala"), "\"I love Scala\" == stringConcatenator(\"I love\", \" Scala\")")
  }

  def syntaxSugars(): Unit = {

    val doubler1 = new Function1[Int, Int] {
      override def apply(v1: Int): Int = 2 * v1
    }

    assert(doubler1(1) == 2, "doubler1(1) == 2")
    assert(doubler1(2) == 4, "doubler1(2) == 4")
    assert(doubler1(3) == 6, "doubler1(3) == 6")

    val doubler2: Function1[Int, Int] = (x: Int) => 2 * x


    assert(doubler1(1) == doubler2(1), "doubler1(1) == doubler2(1)")
    assert(doubler1(2) == doubler2(2), "doubler1(2) == doubler2(2)")
    assert(doubler1(3) == doubler2(3), "doubler1(3) == doubler2(3)")

    val doubler3: Int => Int = (x: Int) => 2 * x

    assert(doubler1(1) == doubler3(1), "doubler1(1) == doubler3(1)")
    assert(doubler1(2) == doubler3(2), "doubler1(2) == doubler3(2)")
    assert(doubler1(3) == doubler3(3), "doubler1(3) == doubler3(3)")

    val doubler4 = (x: Int) => 2 * x

    assert(doubler1(1) == doubler4(1), "doubler1(1) == doubler4(1)")
    assert(doubler1(2) == doubler4(2), "doubler1(2) == doubler4(2)")
    assert(doubler1(3) == doubler4(3), "doubler1(3) == doubler4(3)")
  }

  def higherOrderFunctions(): Unit = {
    // take functions as arguments or/and return functions as results
    println("\r\nval aList = List(1, 2, 3, 4, 5)")
    val aList = List(1, 2, 3, 4, 5)
    println("aList: " + aList)
    val aMappedList = aList.map(x => x + 1) // map is Higher order function
    println("aMappedList: " + aMappedList)

    println("\r\naList: " + aList)
    val aFlatMappedList: List[Int] = aList.flatMap(x => List(x, 2 * x))
    println("aFlatMappedList: " + aFlatMappedList)
    val aFlatMappedListWithAlterSyntax = aList.flatMap { x =>
      List(x, 2 * x)
    }
    println("aFlatMappedListWithAlterSyntax: " + aFlatMappedListWithAlterSyntax)
    assert(aFlatMappedList == aFlatMappedListWithAlterSyntax, "aFlatMappedList == aFlatMappedListWithAlterSyntax")

    println("\r\naList: " + aList)
    val aFilteredList = aList.filter(x => x < 3)
    println("aFilteredList: " + aFilteredList)

    println("aList: " + aList)
    val aFilteredListShortSyntax = aList.filter(_ < 3)
    println("aFilteredListShortSyntax: " + aFilteredListShortSyntax)
    assert(aFilteredList == aFilteredListShortSyntax, "aFilteredList == aFilteredListShortSyntax")

    println("\r\nval aLetterList = List('a', 'b', 'c', 'd', 'e')")
    val aLetterList = List('a', 'b', 'c', 'd', 'e')
    val allPossiblePermutationsOfPairsFrom2Lists1 = aList.flatMap(aNumber => aLetterList.map(aLetter => s"$aNumber$aLetter"))
    println("allPossiblePermutationsOfPairsFrom2Lists1: " + allPossiblePermutationsOfPairsFrom2Lists1)

    val allPossiblePermutationsOfPairsFrom2Lists2 = aList.flatMap { aNumber =>
      aLetterList.map { aLetter =>
        s"$aNumber$aLetter"
      }
    }
    assert(
      allPossiblePermutationsOfPairsFrom2Lists1 == allPossiblePermutationsOfPairsFrom2Lists2,
      "allPossiblePermutationsOfPairsFrom2Lists1 == allPossiblePermutationsOfPairsFrom2Lists2"
    )

    // for comprehensions
    val allPossiblePermutationsOfPairsFrom2Lists3 = for { // for is not a loop
      number <- aList
      letter <- aLetterList
                                                          } yield s"$number$letter"
    assert(
      allPossiblePermutationsOfPairsFrom2Lists1 == allPossiblePermutationsOfPairsFrom2Lists3,
      "allPossiblePermutationsOfPairsFrom2Lists1 == allPossiblePermutationsOfPairsFrom2Lists3"
    )
  }

  def lists(): Unit = {
    val aNumbList = List(1, 2, 3, 4, 5)
    val aNumbListFirstElement: Int = aNumbList.head
    val aNumbListRest: List[Int] = aNumbList.tail
    val aPrependedList1 = 0 :: aNumbList
    assert(aPrependedList1 == List(0, 1, 2, 3, 4, 5), "aPrependedList1 == List(0,1,2,3,4,5)")
    val aPrependedList2 = 0 +: aNumbList
    assert(aPrependedList1 == aPrependedList2, "aPrependedList1 == aPrependedList2")
    val anAppendedList = aNumbList :+ 6
    assert(anAppendedList == List(1, 2, 3, 4, 5, 6), "anAppendedList == List(1,2,3,4,5,6)")
    val anExtendedList = 0 +: aNumbList :+ 6
    assert(anExtendedList == List(0, 1, 2, 3, 4, 5, 6), "anExtendedList == List(0,1,2,3,4,5,6)")
  }

  def sequences(): Unit = {
    val aSequence: Seq[Int] = Seq(1, 2, 3, 4) // Seq - trait
    assert(aSequence == Seq.apply(1, 2, 3, 4), "aSequence == Seq.apply(1, 2, 3, 4)")
    assert(aSequence(1) == aSequence.apply(1), "aSequence(1) == aSequence.apply(1)")
    assert(aSequence(1) == 2, "aSequence(1) == 2")
  }

  def vectors(): Unit = {
    // super fast sequences for large data
    // has same methods as lists or sequences
    val aVector = Vector(1, 2, 3, 4, 5)
  }

  def sets(): Unit = {
    val aSet = Set(1, 2, 3, 4, 5, 1, 2, 3)
    assert(aSet == Set(1, 2, 3, 4, 5), "aSet == Set(1,2,3,4,5)")
    assert(aSet.contains(5), "aSet.contains(5)")
    assert(!aSet.contains(6), "!aSet.contains(6)")
    val anAppendedSet = aSet + 6
    assert(anAppendedSet.contains(6), "anAppendedSet.contains(6)")
    val abatedSet = aSet - 3
    assert(!abatedSet.contains(3), "!abatedSet.contains(3)")
  }

  def ranges(): Unit = {
    println("\r\nval aRange = 1 to 1000")
    val aRange = 1 to 1000 // does not really contain every element like List(1, 2, 3, 4, ..., 1000)
    val aListFrom2To2000WithStep2 = aRange.map(_ * 2).toList // List(2, 4, 6, 8, ..., 2000)
    println(s"aRange: $aRange")
    println(s"aListFrom2To2000WithStep2: $aListFrom2To2000WithStep2")
  }

  def tuples(): Unit = {
    // groups of values under same values
    val aTuple = ("Bon Jovi", "Rock", 1982)
  }

  def maps(): Unit = {
    val aPhoneBook: Map[String, Int] = Map(
      ("Daniel", 123123121),
      ("Jane", 783463413),
      "Ada" -> 1765675321
    )
  }
}
