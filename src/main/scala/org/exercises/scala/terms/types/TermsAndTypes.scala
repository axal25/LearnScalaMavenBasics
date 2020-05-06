package org.exercises.scala.terms.types

object TermsAndTypes {

  def main() {
    basics()
    assertionIssuesWithShouldBeMethod()
    functionExamples()
  }

  def basics() {
    val anInt1 = 1
    val aBoolean1 = true
    val aString1 = "Hello, Scala!"
    val sum1 = 1 + 2
    val aString2 = "Hello, " ++ "Scala!"
    val sum2_1 = (1 + 2) * 3
    val sum2_2 = (3) * 3
    val sum2_3 = 9
    val sizeOfString = "Hello, Scala!".size
    val rangeOfNumbers1 = 1.to(10)
    assert(
      3 + 2 == 3.+(2),
      "3 + 2 == 3.+(2)"
    )
    assert(
      1.to(10) == (1 to 10),
      "1.to(10) == (1 to 10)"
    )
    val aInt2: Int = 0
    val aInt3: Int = 1
    val aString3: String = "foo"
    val aString4: String = "bar"
    val aInt4: Range.Inclusive = 1 to 3
    val a32BitInt: Int = 456
    val a64BitDouble: Double = 4.56
    val aBoolean2: Boolean = false;
  }

  def assertionIssuesWithShouldBeMethod() {
    // 1 + 2 shouldBe 3
    // method shouldBe can be used only inside test path, because scala doesn't allow org.scalatest package to be imported
    // changing package scope in maven (pom.xml) does not work
    // we need this package because should be is connected to test class / interface (trait) we need to extend / implement
    /**
     * @Test
     * def testShouldBe(): Unit = {
     * class ShouldBeTester extends FlatSpec with Matchers {
     * def shouldBeTest(): Unit = {
     * 1 + 2 shouldBe 3
     * }}}
     */
    assert(
      1 + 2 == 3,
      "1 + 2 == 3"
    )

    assert(
      "Hello, " ++ "Scala!" == "Hello, Scala!",
      "\"Hello, \" ++ \"Scala!\" == \"Hello, Scala!\""
    )

    assert(
      "Hello, Scala!".toUpperCase == "HELLO, SCALA!",
      "\"Hello, Scala!\".toUpperCase == \"HELLO, SCALA!\""
    )

    assert(
      -42.abs == 42,
      "-42.abs == 42"
    )
  }

  def functionExamples() {
    assert(
      16.toHexString == "10",
      "16.toHexString == \"10\""
    )

    assert(
      (0 to 10).contains(10) == true,
      "(0 to 10).contains(10) == true"
    )

    assert(
      (0 until 10).contains(10) == false,
      "(0 until 10).contains(10) == false"
    )

    assert(
      "foo".drop(1) == "oo",
      "\"foo\".drop(1) == \"oo\""
    )

    assert(
      "bar".take(2) == "ba",
      "\"bar\".take(2) == \"ba\""
    )
  }
}
