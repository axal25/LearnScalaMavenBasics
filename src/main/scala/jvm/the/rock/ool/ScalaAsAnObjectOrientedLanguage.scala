package jvm.the.rock.ool

import jvm.the.rock.ool.hierarchy.{Animal, Carnivore, Cat, Crocodile, Dog, MySingleton}

object ScalaAsAnObjectOrientedLanguage {

  def main() {
    animalOperations()
    singletons()
    companionsObjectAndClass()
    caseClasses()
    exceptions()
    generics()
    whatsScalaAbout()
  }

  def animalOperations(): Unit = {
    println()
    val anAnimal: Animal = new Animal
    val aCat: Cat = new Cat
    val aDog: Dog = new Dog("Latek", "FieldLatek") // Constructor from definition
    //    aDog.name   // does not have field Name, needs keyword * val *
    val aDogsFieldName = aDog.fieldName // this is field, have * val * keyword before it in Constructor / Definition
    val aPolyAnimal: Animal = new Dog("Hachi", "FieldHachi")
    print("val aPolyAnimal: Animal = new Dog(\"Hachi\", \"FieldHachi\"); aPolyAnimal.eat(): ")
    aPolyAnimal.eat() // will be called the most derived method at runtime // most derived === most overridden method

    val aCroc: Crocodile = new Crocodile
    print("aCroc.eat(aDog): ")
    aCroc.eat(aDog)
    print("aCroc eat aCat: ")
    aCroc eat aCat // only available for methods with 1 argument
    print("aCroc ?! \"What if we could fly?\": ")
    aCroc ?! "What if we could fly?" // operators are methods
    val sum1 = 1 + 2
    val sum2 = 1.+(2)
    assert(sum1 == sum2, "sum1 == sum2")

    // anonymous class
    val dinosaur = new Carnivore {
      override def eat(animal: Animal): Unit = println(s"anon dinosaur: Carnivore.eat($animal: Animal)")
    }
    print("dinosaur.eat(aCroc): ")
    dinosaur.eat(aCroc)
  }

  def singletons(): Unit = {
    println()
    val mySingletonsSpecialValue = MySingleton.mySpecialValue
    MySingleton.mySpecialMethod()
    print("MySingleton.apply(65): ")
    val apply1 = MySingleton.apply(65)
    print("MySingleton(65): ")
    val apply2 = MySingleton(65)
    assert(apply1 == apply2, "apply1 == apply2")
  }

  def companionsObjectAndClass(): Unit = {
    println("\r\nAnimal.interactWithCompanionClass(): ")
    Animal.interactWithCompanionClass()
    println(s"\r\nAnimal.canLiveIndefinitely: $Animal.canLiveIndefinitely")
    val aCat: Cat = new Cat
    println("\r\nval aCat: Cat = new Cat; aCat.interactWithCompanionObject(): ")
    aCat.interactWithCompanionObject()
  }

  def caseClasses(): Unit = {
    /*
    case classes = lightweight data structures with some boilerplate
    - sensible equals and hash code
    - serialization
    - companion with apply
    - pattern matching
     */
    case class Person(name: String, age: Int)

    val bob1 = new Person("Bob", 1)
    val bob2 = Person("Bob", 2) // without keyword new
    val bob3 = Person.apply("Bob", 3)
    assert(bob1.name == bob2.name, "bob1.name == bob2.name")
    assert(bob1.age == 1, "bob1.age == 1")
    assert(bob2.age == 2, "bob3.age == 2")
    assert(bob2.name == bob3.name, "bob2.name == bob3.name")
    assert(bob3.age == 3, "bob3.age == 3")
  }

  def exceptions(): Unit = {
    try {
      // code that can throw exception
      val x: String = null
      x.length
    } catch {
      case e: Exception => println("exception handling")
    } finally {
      println("code executed no matter what")
    }
  }

  def generics(): List[Int] = {
    // generics
    abstract class MyList[T] {
      def head: T

      def tail: MyList[T]
    }

    val aList: List[Int] = List(1, 2, 3) // List.apply(1, 2, 3)
    val first: Int = aList.head
    val tail: List[Int] = aList.tail
    val aStringList = List("hello", "Scala")
    val firstString: String = aStringList.head
    val tailString: List[String] = aStringList.tail
    aList
  }

  def whatsScalaAbout(): Unit = {
    val aList: List[Int] = List(1, 2, 3)

    // Scala has immutable values
    // Modifications should return another object
    // Benefits:
    // 1) multithreaded/distributed env
    // 2) "reasoning about" - help make sense of the code
    val reversedList = aList.reverse // returns a new List

    // Scala is close to an ideal of OOP, everything is inside objects (methods and values)
  }

}
