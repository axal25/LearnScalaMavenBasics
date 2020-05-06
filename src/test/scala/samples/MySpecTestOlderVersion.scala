package samples

import jdk.internal.jline.console.internal.ConsoleRunner
import org.specs2.mutable.Specification

class MySpecTestOlderVersion extends ConsoleRunner()

object MySpec extends Specification {
  "This wonderful system" should {
    "save the world" in {
      val list = Nil
      list must beEmpty
    }
  }
}
