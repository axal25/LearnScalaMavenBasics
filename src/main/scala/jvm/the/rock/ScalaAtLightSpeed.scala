package jvm.the.rock

import jvm.the.rock.`match`.pattern.PatternMatching
import jvm.the.rock.advanced.AdvancedBits
import jvm.the.rock.basics.AbsoluteBasics
import jvm.the.rock.func.prog.FunctionalProgramming
import jvm.the.rock.ool.ScalaAsAnObjectOrientedLanguage
import main.MainApp

object ScalaAtLightSpeed {
  /**
   * Source: https://rockthejvm.com/courses/728053/lectures/13122825
   */


  def main() {
    MainApp.announceSourceName(this.getClass.getSimpleName)
    AbsoluteBasics.main()
    ScalaAsAnObjectOrientedLanguage.main()
    FunctionalProgramming.main()
    PatternMatching.main()
    AdvancedBits.main()
  }

}