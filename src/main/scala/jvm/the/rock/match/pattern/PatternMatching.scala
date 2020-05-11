package jvm.the.rock.`match`.pattern

object PatternMatching {

  def main(): Unit = {
    switches()
    println("Pattern Matching Is An Expression");
    switches()
    caseClassDecomposition()
    deconstructingTuples()
  }

  def switches(): Unit = {
    println()
    val anInteger: Int = 55
    val order = anInteger match {
      case 1 => anInteger + "st"
      case 2 => anInteger + "nd"
      case 3 => anInteger + "rd"
      case _ => anInteger + "th"
    }
    println(order)
  }

  def caseClassDecomposition(): Unit = {
    case class Person(name: String, age: Int) {
      def this(name: String) = this(name, -1)
      def this() = this(null)
    }

    val bob = Person("Bob", 43)
    val angela = Person("Angela", 19)
    val bob2 = new Person("Bob 2")
    val bob3 = new Person()
    assert(Person("Bob", 43) == new Person("Bob", 43), "Person(\"Bob\", 43) == new Person(\"Bob\", 43)")
    assert(Person("Bob", 43) == Person.apply("Bob", 43), "Person(\"Bob\", 43) == Person.apply(\"Bob\", 43)")
    assert(angela == new Person("Angela", 19), "angela == new Person(\"Angela\", 19)")
    assert(bob2 == new Person("Bob 2", -1), "bob2 == new Person(\"Bob2\", -1)")
    assert(bob3 == new Person(null, -1), "bob3 == new Person(null, -1)")

    def personGreeting(p: Person): String = p match {
      case Person(n, a) => s"Hi, my name is $n and I am $a years old."
      case _ => "I don't really know what to say..."
    }

    assert(personGreeting(bob) == "Hi, my name is Bob and I am 43 years old.", "personGreeting(bob) == \"Hi, my name is Bob and I am 43 years old.\"")
    println(personGreeting(bob))
    assert(personGreeting(angela) == "Hi, my name is Angela and I am 19 years old.", "personGreeting(angela) == \"Hi, my name is Angela and I am 19 years old.\"")
    println(personGreeting(angela))
    assert(personGreeting(bob2) == "Hi, my name is Bob 2 and I am -1 years old.", "personGreeting(bob2) == \"Hi, my name is Bob 2 and I am -1 years old.\"")
    println(personGreeting(bob2))
    assert(personGreeting(bob3) == "Hi, my name is null and I am -1 years old.", "personGreeting(bob3) == \"Hi, my name is null and I am -1 years old.\"")
    println(personGreeting(bob3))
  }

  def deconstructingTuples(): Unit = {
    val aTuple = ("Bon Jovi", "Rock")
    val bandDescription = aTuple match {
      case (band, genre) => s"$band belongs to the genre $genre"
      case _ => "I don't know what you are talking about"
    }
    println(bandDescription)

    val a5List = List(1, 2, 3, 4, 5)
    val a6List = a5List :+ 6
    val a7List = a6List :+ 7

    def listDescription(aList: List[Int]): String = aList match {
      case List(_, 2, _) => "List has 5 elements, contains \"2\" on its second position"
      case List(_, 2, _, _, _, _) => "List has 6 elements, contains \"2\" on its second position"
      case List(_, 2, _, _, _) => "List has 5 elements, contains \"2\" on its second position"
      case _ => "unknown list"
    }

    print("listDescription(a5List): ")
    println(listDescription(a5List))
    print("listDescription(a6List): ")
    println(listDescription(a6List))
    print("listDescription(a7List): ")
    println(listDescription(a7List))
    // with case _ => ... program will not throw MatchError
    // matching is done in order, from top to bottom
  }

}
