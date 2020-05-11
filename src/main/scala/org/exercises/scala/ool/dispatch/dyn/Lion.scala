package org.exercises.scala.ool.dispatch.dyn

class Lion extends Animal {
  var isHungry: Boolean = false

  encounter.defMethod({ case animal: Animal => "LION encountered ANIMAL" })
  encounter.defMethod({ case bunny: Bunny => "LION encountered BUNNY" })
  encounter.defMethod({ case hare: Hare => "LION encountered HARE" })
  encounter.defMethod({ case lion: Lion => "LION encountered LION" })
}
