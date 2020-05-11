package org.exercises.scala.ool.set.int

object Empty extends IntSet {
  override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  override def contains(x: Int): Boolean = false
}