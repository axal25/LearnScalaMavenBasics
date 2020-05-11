package org.exercises.scala.ool.dispatch.polym

trait RabbitLike extends Animal {
  override def encounter(animal: Animal): String = "RabbitLike encountered another Animal"

  def encounter(other: RabbitLike): String = "RabbitLike encountered another RabbitLike"
}
