package org.exercises.scala.ool.dispatch.dyn

class Bunny extends RabbitLike {
  encounter.defMethod({ case animal: Animal => "BUNNY encountered ANIMAL" })
  encounter.defMethod({ case rabbitLike: RabbitLike => "BUNNY encountered RABBIT_LIKE" })
  encounter.defMethod({ case bunny: Bunny => "BUNNY encountered BUNNY" })
  encounter.defMethod({ case hare: Hare => "BUNNY encountered HARE" })
  encounter.defMethod({ case lion: Lion => "BUNNY encountered LION" })
}
