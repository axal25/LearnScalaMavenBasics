package org.exercises.scala.ool.set.int

abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean
}
