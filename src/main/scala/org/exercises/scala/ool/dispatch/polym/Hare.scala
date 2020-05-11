package org.exercises.scala.ool.dispatch.polym

class Hare extends RabbitLike {
  override def encounter(animal: Animal): String = "Hare encountered another Animal"

  override def encounter(rabbitLike: RabbitLike): String = "Hare encountered another RabbitLike"

  def encounter(hare: Hare): String = "Hare encountered another Hare"

  def encounter(bunny: Bunny): String = "Hare encountered another Bunny"

  def encounter(lion: Lion): String = "Hare encountered another Lion"
}
