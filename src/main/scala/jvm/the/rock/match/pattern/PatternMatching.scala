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
    case class Person1(name: String, age: Int)
    val bob = Person1("Bob", 43)
    assert(Person1("Bob", 43) == new Person1("Bob", 43), "Person(\"Bob\", 43) == new Person(\"Bob\", 43)")
    assert(Person1("Bob", 43) == Person1.apply("Bob", 43), "Person(\"Bob\", 43) == Person.apply(\"Bob\", 43)")
    val angela = Person1("Angela", 19)

    def personGreeting(p: Person1): String = p match {
      case Person1(n, a) => s"Hi, my name is $n and I am $a years old."
      case _ => "I don't really know what to say..."
    }
    //todo
    //    generic?
    //    case class Person2(name2: String, age2: Int) {}
    //    val bob2 = Person2("Bob 2", 432)
    //
    //    case class Person3(name3: String)
    //    val bob3 = Person3("Bob3")
    //
    println(personGreeting(bob))
    println(personGreeting(angela))
    //    println(personGreeting(bob2))
    //    println(personGreeting(bob3))
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
