package samples

import org.junit.Assert._
import org.junit._
import org.scalatest.{FlatSpec, Matchers}

@Test
class AppTest {

  @Test
  def testOK() = assertTrue(true)

  @Test
  def testShouldBe(): Unit = {
    class ShouldBeTester extends FlatSpec with Matchers {
      def shouldBeTest(): Unit = {
        1 + 2 shouldBe 3
      }
    }
  }

  //    @Test
  //    def testKO() = assertTrue(false)

}


