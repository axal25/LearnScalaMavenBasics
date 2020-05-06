package jvm.the.rock.ool.hierarchy

class Animal { // Companion Class (Animal) to existing object Animal
  val age: Int = 0 // by default public
  private var classPrivateField: Int = -999;

  def eat() = println("Animal.eat()") // by default public
  private def classPrivateMethod(x: String): Unit = println(s"Class Animal.classPrivateMethod() $x")

  def interactWithCompanionObject(): Unit = {
    println("class Animal: interactWithCompanionObject: Animal.objectPrivateMethod(\"from Companion Class Animal\"): " + Animal.objectPrivateMethod("from Companion Class Animal"));
    println(s"class Animal: interactWithCompanionObject: Animal.objectPrivateField: ${Animal.objectPrivateField}")
    Animal.objectPrivateField = 666
    println(s"class Animal: interactWithCompanionObject: Animal.objectPrivateField = 666; Animal.objectPrivateField: ${Animal.objectPrivateField}")
  }
}

object Animal { // Companion Object (Animal) to existing class Animal
  val canLiveIndefinitely = false
  private var objectPrivateField: Int = -666

  private def objectPrivateMethod(x: String): Unit = println(s"Object Animal.objectPrivateMethod() $x")

  // both Companions can access each other's private fields and methods
  // singleton object Animal and instances of Animal are different things
  // but this does not apply to objects created from constructor Class Animal

  def interactWithCompanionClass(): Unit = {
    val anAnimal: Animal = new Animal
    println("object Animal: interactWithCompanionClass: val anAnimal: Animal = new Animal; anAnimal.classPrivateMethod(\"from Companion Object Animal\"): " + anAnimal.classPrivateMethod("from Companion Object Animal"));
    println(s"object Animal: interactWithCompanionClass: anAnimal.classPrivateField: ${anAnimal.classPrivateField}")
    anAnimal.classPrivateField = 999
    println(s"object Animal: interactWithCompanionClass: anAnimal.classPrivateField = 999; anAnimal.classPrivateField: ${anAnimal.classPrivateField}")
  }
}
