package org.exercises.scala.ool.traits

object Traits {
  trait Planar {
    var i = 1

    def getI = i

    def setI(int: Int): Unit = {
      i = int
    }

    def getWidth: Int

    def getHeight: Int

    def getSurface = getWidth * getHeight
  }

  trait Shape {
    def draw: Unit
  }

  trait Movable {
    def move(deltaX: Int, deltaY: Int): Unit
  }

  class Square(x: Int, y: Int) extends Shape with Planar with Movable {
    var width: Int = x
    var height: Int = y

    override def draw: Unit = println(s"draw ${this.toString}")

    override def getWidth: Int = this.width

    override def getHeight: Int = this.height

    override def move(deltaX: Int, deltaY: Int): Unit = {
      this.width = getWidth + deltaX
      this.height = getHeight + deltaY
    }

    override def toString: String = s"${this.getClass.getSimpleName}{ width: $getWidth, height: $getHeight, surface: $getSurface, i: $getI }"
  }
}
