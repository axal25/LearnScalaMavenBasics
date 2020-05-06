package jvm.the.rock.basics

object AbsoluteBasics {
  val meaningOfLife: Int = 42 // constant, reassigning not allowed

  def main() {
    types()
    expressions()
  }

  def types(): Unit = {
    val aBoolean = false // type is optional
    val aChar: Char = 'a'
    val aDouble: Double = 2.3
    val aFloat: Float = 111111
    val aString: String = "I love Scala"

    val aComposedString: String = "I" + " " + "love" + " " + "Scala"
    val anInterpolatedString: String = s"The meaning of life is $meaningOfLife"
  }

  def expressions() {
    // expression = structures that can be reduced to a value
    val anExpression = 2 + 3

    // if-expression
    val ifExpression = if (meaningOfLife > 43) 56 else 999
    val chainedIfExpression =
      if (meaningOfLife > 43) 56
      else if (meaningOfLife < 0) -2
      else if (meaningOfLife > 999) 78
      else 0

    // code block
    val aCodeBlock = {
      //definitions
      val aLocalValue = 67

      aLocalValue + 3 // returned value from block, value of last expression, will become value assigned to aCodeBlock
    }

    // define a function
    def myInlineFunction(x: Int, y: String): String = y + " " + x

    def myCodeBlockFunction(x: Int, y: String): String = {
      y + " " + x
    }

    // recursive function
    def factorial(n: Int): Int =
      if (n <= 1) 1
      else n * factorial(n - 1)

    /*
    factorial(5) = 5 * factorial(4) = 5 * 24 = 120
    factorial(4) = 4 * factorial(3) = 4 * 6
    factorial(3) = 3 * factorial(2) = 3 * 2
    factorial(2) = 2 * factorial(1) = 2 * 1
    factorial(1) = 1
     */
    assert(factorial(5) == 120, "factorial(5) == 120");
    // don't use loops or iterations, we use RECURSION
    // type: Unit = no meaningful value === "void" in Java
    println("I love Scala") // returns type: Unit, type is SIDE EFFECT // System.out.println("I love Scala")

    def myUnitReturningFunction(): Unit = {
      print("I don't like returning Unit type")
    }

    val theUnit = ()
  }

}
