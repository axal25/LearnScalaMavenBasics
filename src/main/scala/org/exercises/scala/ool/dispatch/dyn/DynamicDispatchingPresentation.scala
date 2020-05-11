package org.exercises.scala.ool.dispatch.dyn

import util.MyAssertions

object DynamicDispatchingPresentation {
  def main(): Unit = {
    val bunny = new Bunny
    val hare = new Hare
    val lion = new Lion
    val lazyBunny = new Bunny {
      override def toString: String = "LazyBunny"
      encounter.defMethod({ case lion: Lion if !lion.isHungry => "LAZY BUNNY encountered NOT HUNGRY LION" })
    }
    lazyBunny.encounter.defMethod({ case lion: Lion if !lion.isHungry => "LAZY BUNNY encountered NOT HUNGRY LION" })

    println(s"\nval hare = new Hare; hare: $hare")
    println(s"val bunny = new Bunny; bunny: $bunny")
    println(s"val lion = new Lion; lion: $lion")
    println("val lazyBunny = new Bunny;")
    println("lazyBunny.encounter.defMethod({ case lion: Lion if !lion.isHungry => \"LAZY BUNNY encountered NOT HUNGRY LION\"})")
    println(s"lazyBunny: $lazyBunny")
    println
    MyAssertions.myAssert2Arg(hare, bunny, "HARE encountered BUNNY", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(hare, bunny, "HARE encountered BUNNY", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    MyAssertions.myAssert2Arg(hare, lion, "HARE encountered LION", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(hare, lion, "HARE encountered LION", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    println
    MyAssertions.myAssert2Arg(lion, bunny, "LION encountered BUNNY", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(lion, bunny, "LION encountered BUNNY", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    MyAssertions.myAssert2Arg(lion, hare, "LION encountered HARE", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(lion, hare, "LION encountered HARE", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    println
    MyAssertions.myAssert2Arg(lazyBunny, lion, "LAZY BUNNY encountered NOT HUNGRY LION", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(lazyBunny, lion, "LAZY BUNNY encountered NOT HUNGRY LION", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
  }
}
