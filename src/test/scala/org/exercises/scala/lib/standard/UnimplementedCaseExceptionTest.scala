package org.exercises.scala.lib.standard

import org.junit._
import org.scalatest.{FlatSpec, Matchers}
import util.MyAssertions

@Test
class UnimplementedCaseExceptionTest {

  @Test
  def test1(): Unit = {
    class FlatSpecWithMatchers extends FlatSpec with Matchers {
      def testThrows(): Unit = {
        val functionName = "testThrows"
        val obj1: Integer = 1
        val obj2 = "Obj 2"
        val obj3: Character = '3'
        val obj4 = List(4, "Obj 5", '6', List(7, 8, 9, 10))
        val unimplementedCaseException = new UnimplementedCaseException(this, functionName, obj1, obj2, obj3, obj4)
        unimplementedCaseException should not be new UnimplementedCaseException(this, functionName)
        val expectedMessage = {
          s"${this.getClass.getSimpleName} - $functionName - Unimplemented case for object(s): " +
            s"$obj1: ${obj1.getClass.getSimpleName}, " +
            s"$obj2: ${obj2.getClass.getSimpleName}, " +
            s"$obj3: ${obj3.getClass.getSimpleName}, " +
            s"$obj4: ${obj4.getClass.getSimpleName}."
        }
        MyAssertions.myAssert1Arg(unimplementedCaseException.getMessage, expectedMessage, x => x, "x => x")
        unimplementedCaseException.getMessage shouldBe expectedMessage
      }

      testThrows()
    }

    new FlatSpecWithMatchers
  }

}
