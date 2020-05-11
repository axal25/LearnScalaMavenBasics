package org.exercises.scala.ool.dispatch.dyn

trait RabbitLike extends Animal {
  encounter.defMethod({ case animal: Animal => "RABBIT_LIKE encountered ANIMAL" })
  encounter.defMethod({ case rabbitLike: RabbitLike => "RABBIT_LIKE encountered RABBIT_LIKE" })

}
