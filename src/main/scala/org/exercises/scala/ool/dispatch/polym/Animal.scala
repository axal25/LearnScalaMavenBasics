package org.exercises.scala.ool.dispatch.polym

trait Animal {
  def encounter(other: Animal): String = "Animal encountered another Animal"

  override def toString: String = this.getClass.getSimpleName
}
