package jvm.the.rock.ool.hierarchy

class Cat extends Animal

class Dog(name: String, val fieldName: String) extends Animal { // also a Constructor

}

abstract class WalkingAnimal {
  private val hasLegs = true

  protected def walk(): Unit

  def doWalk(): Unit = walk()
}

// interface === trait
trait Carnivore {
  def eat(animal: Animal): Unit
}

trait WaterBased {
  def swim(animal: Animal): Unit
}

trait Philosopher {
  def ?!(thought: String): Unit // valid method name
}

class Crocodile extends Animal with Carnivore with WaterBased with Philosopher { // can extend only 1 class, can implement (with) multiple traits (mixing)
  override def eat(animal: Animal): Unit = println(s"Crocodile.eat($animal: Animal)") // implements method from Carnivore
  override def swim(animal: Animal): Unit = println(s"Crocodile.swim($animal: Animal)") // implements method from WaterBased
  override def eat(): Unit = println("Crocodile.eat()") // override method from Animal
  override def ?!(thought: String): Unit = println(s"Croc.?! - thought: $thought")
}
