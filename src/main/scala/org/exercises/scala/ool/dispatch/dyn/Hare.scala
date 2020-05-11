package org.exercises.scala.ool.dispatch.dyn

class Hare extends RabbitLike {
  encounter.defMethod({ case animal: Animal => "HARE encountered ANIMAL" })
  encounter.defMethod({ case rabbitLike: RabbitLike => "BUNNY encountered RABBIT_LIKE" })
  encounter.defMethod({ case hare: Hare => "HARE encountered HARE" })
  encounter.defMethod({ case bunny: Bunny => "HARE encountered BUNNY" })
  encounter.defMethod({ case lion: Lion => "HARE encountered LION" })
}
