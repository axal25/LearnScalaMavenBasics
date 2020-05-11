package org.exercises.scala.ool.dispatch.polym

class Lion extends Animal {
  override def encounter(animal: Animal): String = "Lion encountered another Animal"

  def encounter(rabbitLike: RabbitLike): String = "Lion encountered another RabbitLike"

  def encounter(bunny: Bunny): String = "Lion encountered another Bunny"

  def encounter(lion: Lion): String = "Lion encountered another Lion"
}
