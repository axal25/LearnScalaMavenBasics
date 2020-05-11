package org.exercises.scala.ool.dispatch.polym

class Bunny extends RabbitLike {
  override def encounter(animal: Animal): String = "Bunny encountered another Animal"

  override def encounter(rabbitLike: RabbitLike): String = "Bunny encountered another RabbitLike"

  def encounter(bunny: Bunny): String = "Bunny encountered another Bunny"

  def encounter(hare: Hare): String = "Bunny encountered another Hare"

  def encounter(lion: Lion): String = "Bunny encountered another Lion"
}
