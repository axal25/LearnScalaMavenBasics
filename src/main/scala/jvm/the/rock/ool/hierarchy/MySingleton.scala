package jvm.the.rock.ool.hierarchy

// singleton object
object MySingleton { // it is the only instance of MySingleton type
  val mySpecialValue = 53232131

  def mySpecialMethod(): Unit = {
    val value = 52131
    println(s"MySingleton: mySpecialMethod - printing value: $value");
  }

  def apply(x: Int): Int = {
    val y = x + 1
    println(s"MySingleton: apply - incrementing $x to $y and returning $y")
    y
  }
}
