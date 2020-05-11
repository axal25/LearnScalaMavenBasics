package org.exercises.scala.ool

import org.exercises.scala.lib.standard.UnimplementedCaseException
import org.exercises.scala.ool.arith.ration._
import org.exercises.scala.ool.dispatch.dyn.DynamicDispatchingPresentation
import org.exercises.scala.ool.dispatch.polym.{AnimalGen, Bunny, Hare, Lion}
import org.exercises.scala.ool.traits.Traits.Square
import util.MyAssertions

object ObjectOrientedProgramming {

  def main(): Unit = {
    println("\nObject oriented programming")
    functionsAndData()
    dataAbstraction()
    requireAndAssert()
    constructors()
    classesAndSubstitutions()
    operators()
    operatorPriorityPrecedenceRules()
    abstractClasses()
    classExtensions()
    implementationAndOverriding()
    objectDefinitions()
    dynamicBinding()
    dynamicDispatching()
    traits()
    scalaClassHierarchy()
    exercise()
  }

  def functionsAndData(): Unit = {
    println("Functions and Data")
    println("Design a package for doing rational arithmetic")
    println("Rational number = x / y represented by 2 Ints")
    println("x = numerator, y = denominator")
    println("For rational addition it is possible to define 2 functions like this")

    def addRationalNumerator(n1: Int, d1: Int, n2: Int, d2: Int): Int = return -1

    def addRationalDenominator(n1: Int, d1: Int, n2: Int, d2: Int): Int = return -1

    println("def addRationalNumerator(n1: Int, d1: Int, n2: Int, d2: Int):Int = ...")
    println("def addRationalDenominator(n1: Int, d1: Int, n2: Int, d2: Int):Int = ...")
    println("But it could prove difficult to manage all these numerators and denominators")
    println("Better choice is to combine the numerator denominator of a rational number in a data structure")
    iRational.tellTheStoryOfConstructor()
    val rational1: Rational = new Rational(1, 2)
    println("val rational1: Rational = new Rational(1, 2)")
    println(s"rational1: Rational{rational1.numerator: ${rational1.numerator}, rational1.denominator: ${rational1.denominator}}")
    println("def numerator = x - immutable - rational1.numerator = 5 => error")

    println("class Rational(x: Int, y: Int) {")
    println("\t...")
    println("\toverride def toString(): String = {")
    println("\t\ts\"Rational{ numerator: ${this.numerator}, denominator: ${this.denominator}}\"")
    println("\t}")
    println("}")
    println(s"$$rational1 / $$rational1.toString: ${rational1.toString}")

    println("now it is possible to implement standard rules")
    println("instead of writing complex functions solving addition")
    println("n1 / d1 + n2 / d2 = (n1 * d2 + n2 * d1) / (d1 * d2)")
    println("it is to possible to make it more readable by implementing methods inside Rational class")

    println("class Rational(x: Int, y: Int) {")
    println("\t...")
    println("\tdef add(other: Rational): Rational = Rational.add(this, other)");
    println("}")

    println("object Rational {")
    println("\t...")
    println("\tdef add(first: Rational, second: Rational): Rational = {")
    println("\t\tnew Rational(")
    println("\t\t\tfirst.numerator * second.denominator + first.denominator * second.numerator,")
    println("\t\t\tfirst.denominator * second.denominator")
    println("\t\t)")
    println("\t}")
    println("}")

    val rational2: IRational = new Rational(3, 4)

    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(10, 8), iRationalGen.add, "iRationalGen.add")
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(-2, 8), iRationalGen.sub, "iRationalGen.sub")
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(3, 8), iRationalGen.mul, "iRationalGen.mul")
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(4, 6), iRationalGen.div, "iRationalGen.div")

    MyAssertions.myAssert2Arg(10, 8, 2, iRationalGen.greatestCommonDivisor, "iRationalGen.greatestCommonDivisor")
    MyAssertions.myAssert2Arg(-2, 8, 2, iRationalGen.greatestCommonDivisor, "iRationalGen.greatestCommonDivisor")
    MyAssertions.myAssert2Arg(3, 8, 1, iRationalGen.greatestCommonDivisor, "iRationalGen.greatestCommonDivisor")
    MyAssertions.myAssert2Arg(4, 6, 2, iRationalGen.greatestCommonDivisor, "iRationalGen.greatestCommonDivisor")

    MyAssertions.myAssert1Arg(new Rational(10, 8), new Rational(5, 4), iRationalGen.reduce, "iRationalGen.reduce")
    MyAssertions.myAssert1Arg(new Rational(-2, 8), new Rational(-1, 4), iRationalGen.reduce, "iRationalGen.reduce")
    MyAssertions.myAssert1Arg(new Rational(3, 8), new Rational(3, 8), iRationalGen.reduce, "iRationalGen.reduce")
    MyAssertions.myAssert1Arg(new Rational(4, 6), new Rational(2, 3), iRationalGen.reduce, "iRationalGen.reduce")

    MyAssertions.myAssert1Arg(new Rational(10, 8), new Rational(10, 8).toString(), iRationalGen.toString, "iRationalGen.toString")
    MyAssertions.myAssert1Arg(new Rational(10, 8), new Rational(10, 8).toStringInterpretation(), iRationalGen.toStringInterpretation, "iRationalGen.toStringInterpretation")
  }

  def dataAbstraction(): Unit = {
    println("\nData Abstraction")
    val rationalAbstraction: RationalAbstraction = new RationalAbstraction(1, 3)
    println("In previous implementation - Rational - represented rational number were not always in simplest for")
    println("Created separate function reduce allowed us to simplify given number")
    println("But the represented abstract number in more complex form is exactly the same as in simplified")
    println("In addition it is possible to eventually reach limit of data types used in this class (Int(s))")
    println("In CLIENT's view behaviour of complex number form and simplified is exactly the same")
    println("Ability to implement differently data without affecting client is DATA ABSTRACTION")
    println("In addition changing from \"def numerator / denominator\" to \"val numerator / denominator\" [so they are computed only once] ...")
    println("Reusing greatest common divisor via \"gcd\" field variable...")
    println("Also not affect client's view of class behaviour")
    println("class RationalAbstraction(x: Int, y: Int) extends IRational {")
    println("\tprivate val gcd = iRational.greatestCommonDivisor(x, y)")
    println("\tval numerator = x / gcd")
    println("\tval denominator = y / gcd")
    println("\t...")
    println("}")
  }

  def requireAndAssert(): Unit = {
    println("\nRequire")
    println("final def require(requirement: Boolean, message: => Any): Unit")
    println("def require(requirement: Boolean): Unit")
    println("Tests an expression, throwing an IllegalArgumentException if false.")

    try {
      require(1 < 0, "1 < 0")
    } catch {
      case e: IllegalArgumentException => {
        println("try {")
        println("\trequire(1 < 0, \"1 < 0\")")
        println("} catch {")
        println(s"\tcase e: IllegalArgumentException => e.getClass.getSimpleName: ${e.getClass.getSimpleName} e.getMessage: ${e.getMessage}")
        println("}")
      }
    }
    println("Require is used to enforce a precondition on the called of a function.")

    println("\nAssert")
    println("final def assert(assertion: Boolean, message: => Any): Unit")
    println("def assert(assertion: Boolean): Unit")
    println("Tests an expression, throwing an AssertionError if false.")

    try {
      assert(1 < 0, "1 < 0")
    } catch {
      case e: AssertionError => {
        println("try {")
        println("\tassert(1 < 0, \"1 < 0\")")
        println("} catch {")
        println(s"\tcase e: AssertionError => e.getClass.getSimpleName: ${e.getClass.getSimpleName} e.getMessage: ${e.getMessage}")
        println("}")
      }
    }
    println("Assert is used to check the code of the function itself. Test the function.")
  }

  def constructors(): Unit = {
    println("\nConstructors")
    println("class declaration introduced a constructor - class' PRIMARY CONSTRUCTOR")
    println("Primary constructor: takes parameters of the class declaration and executes all statements in the class body")
    println("It is possible to declare an additional constructors - AUXILIARY CONSTRUCTORs - using method named \"this\"")
    class Rational(x: Int, y: Int) // primary constructor and its parameters
    { // primary constructor body
      require(y != 0, "denominator cannot be equal to 0.")

      def numerator = x

      def denominator = y

      // ...
      println(s"\tRational(numerator: $numerator, denominator: $denominator) has been created")

      def this(x: Int) = // auxiliary constructor
      {
        this(x, 1)
        println("\t\tthis(x: Int) - Last constructor was called through Auxiliary Constructor")
      }
    }
    println("class Rational(x: Int, y: Int) // primary constructor and its parameters")
    println("{ // primary constructor body")
    println("\trequire(y != 0, \"denominator cannot be equal to 0.\")")
    println("\tdef numerator = x")
    println("\tdef denominator = y")
    println("\t// ...")
    println("\tprintln(s\"\\tRational(numerator: $numerator, denominator: $denominator) has been created\")")
    println("\tdef this(x: Int) = // auxiliary constructor ")
    println("\t{")
    println("\t\tthis(x, 1)")
    println("\t\tprintln(\"\\t\\tthis(x: Int) - Last constructor was called through Auxiliary Constructor\")")
    println("\t}")
    println("}")
    println("val rational1: Rational = new Rational(1, 2)")
    val rational1: Rational = new Rational(1, 2)
    println("val rational2: Rational = new Rational(3)")
    val rational2: Rational = new Rational(3)
  }

  def classesAndSubstitutions(): Unit = {
    println("\nClasses and Substitutions")

    println("Class/Object evaluation model is the same as for Functions - substitution model")
    println("Instantiation of new class object \"new C(e1, e2, ..., en)")
    println("The arguments are evaluated exactly like for a normal function")
    println("This results in expression \"new C1(v1, v2, ..., vn)\" is already a value")
    println("class of definition:")
    println("class C(x1, x2, ..., xn) {")
    println("\t// ...")
    println("\tdef f(y1, y2, ..., yn) = b")
    println("\t// ...")
    println("}")
    println("This is how expression of form \"new C(v1, v2, ..., vn).f(w1, w2, ..., wn)\" is evaluated")
    println("\t1. substitution of function f parameters: (y1, y2, ..., yn) -> (w1, w2, ..., wn)")
    println("\t2. substitution of class C constructor parameters: (x1, x2, ..., xn) -> (v1, v2, ..., vn)")
    println("\t3. substitution of reference to instance of class object: new C(v1, v2, ..., vn) -> this")

    println("concrete declaration can look like this:")
    class C(x1: Int, x2: Double, xDots: String = "...", xn: Float = 0.01F) {
      val representation = s"x1: $x1, x2: $x2, xDots: $xDots, xn: $xn"
      println(s"primary constructor - representation: $representation")

      def f(y1: Int, y2: Double, yDots: String = "...", yn: Float = 0.01F): Unit = println(s"function f - y1: $y1, y2: $y2, yDots: $yDots, yn: $yn")
    }
    println("class C(x1: Int, x2: Double, xDots: String = \"...\", xn: Float = 0.01F) {")
    println("\tval representation = s\"x1: $x1, x2: $x2, xDots: $xDots, xn: $xn\"")
    println("\tprintln(s\"primary constructor - representation: $representation\")")
    println("\tdef f(y1: Int, y2: Double, yDots: String = \"...\", yn: Float = 0.01F): Unit = println(s\"function f - y1: $y1, y2: $y2, yDots: $yDots, yn: $yn\")")
    println("}")
    println("new C(1, 2.0, \"...1\", 0.01F).f(3,4.0,\"...2\", 0.02F)")
    new C(1, 2.0, "...1", 0.01F).f(3, 4.0, "...2", 0.02F)
  }

  def operators(): Unit = {
    println("\nOperators")
    println("It is possible to override operators - operators can be function identifiers, underscore character, operator symbols")
    println("Identifiers can be:")
    println("\t1. Alphanumeric - starting with a letter, followed by a sequence of letters and numbers")
    println("\t2. Symbolic - starting with operator symbol, followed by other operator symbols")
    println("\t3. Underscore (\"_\") counts as letter")
    println("Examples identifiers: x1 * +?%& vector_++ counter_=")
    println("class Rational(x: Int, y: Int) {")
    println("\tdef numerator = x")
    println("\tdef denominator = y")
    println("\tdef add(other: Rational): Rational = iRational.add(this, other)")
    println("\tdef sub(other: Rational): Rational = iRational.sub(this, other)")
    println("\tdef mul(other: Rational): Rational = iRational.mul(this, other)")
    println("\tdef div(other: Rational): Rational = iRational.div(this, other)")
    println("\tdef +(other: Rational): Rational = this.add(other)")
    println("\tdef -(other: Rational): Rational = this.sub(other)")
    println("\tdef *(other: Rational): Rational = this.mul(other)")
    println("\tdef /(other: Rational): Rational = this.div(other)")
    println("}")
    val rational1: Rational = new Rational(1, 2)
    val rational2: IRational = new Rational(3, 4)
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(10, 8), iRationalGen.+, "iRationalGen.+")
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(-2, 8), iRationalGen.-, "iRationalGen.-")
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(3, 8), iRationalGen.*, "iRationalGen.*")
    MyAssertions.myAssert2Arg(rational1, rational2, new Rational(4, 6), iRationalGen./, "iRationalGen./")

    def testingBadBehaviour(): Unit = {
      try {
        throw new UnimplementedCaseException(this, "testing exception for varargs (repeated parameters _*)", "obj1", "obj2", "obj3")
      } catch {
        case e: UnimplementedCaseException => println(s"#1 ${e.getMessage}")
      }
      try {
        val irationalImplObj1 = new Rational(1, 2)
        val irationalImplObj2 = new RationalAbstraction(1, 2)
        val irationalImplObjResult = iRational.constructorImpl(1, 2, irationalImplObj1, irationalImplObj2)
        val exceptionMsgPt1 = s"Should throw exception."
        val exceptionMsgPt2 = s"Method iRational.constructorImpl should not match any case to 2 different implementations of IRational trait."
        val exceptionMsgPt3 = s"irationalImplObj1: $irationalImplObj1, irationalImplObj2: $irationalImplObj2, irationalImplObjResult: $irationalImplObjResult."
        val exceptionMsg = s"$exceptionMsgPt1 $exceptionMsgPt2 $exceptionMsgPt3"
        throw new UnimplementedCaseException(this, "test", exceptionMsg)
      } catch {
        case e: MixingIRationalImplementationException => println(s"#2 ${e.getMessage}")
      }
      try {
        val irationalImplObj1 = new Rational(1, 2)
        val irationalImplObj2 = new RationalAbstraction(1, 2)
        val irationalImplObjResult = iRational.constructorImpl(1, 2, irationalImplObj2, irationalImplObj1)
        val exceptionMsgPt1 = s"Should throw exception."
        val exceptionMsgPt2 = s"Method iRational.constructorImpl should not match any case to 2 different implementations of IRational trait."
        val exceptionMsgPt3 = s"irationalImplObj2: $irationalImplObj2, irationalImplObj1: $irationalImplObj1, irationalImplObjResult: $irationalImplObjResult."
        val exceptionMsg = s"$exceptionMsgPt1 $exceptionMsgPt2 $exceptionMsgPt3"
        throw new UnimplementedCaseException(this, "test", exceptionMsg)
      } catch {
        case e: MixingIRationalImplementationException => println(s"#3 ${e.getMessage}")
      }
    }

    testingBadBehaviour()
  }

  def operatorPriorityPrecedenceRules(): Unit = {
    println("\nPrecedence Rules - Operator Priority")
    println("Priority of given operator is determined by its first character")
    println("Character priority in Ascending/Increasing order (from lowest  to highest)")
    println("(all letters)")
    println("|")
    println("^")
    println("&")
    println("< >")
    println("= !")
    println(":")
    println("+ -")
    println("* / %")
    println("(all other special characters)")
  }

  def abstractClasses(): Unit = {
    println("\nAbstract Classes")
    println("abstract class IntSet {")
    println("\tdef incl(x: Int): IntSet")
    println("\tdef contains(x: Int): Boolean")
    println("}")
    println("Abstract class - IntSet")
    println("Can have methods without implementation (incl, contains)")
    println("Cannot create instances of the abstract class")
  }

  def classExtensions(): Unit = {
    println("\nClass extensions")
    println("class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {")
    println("\toverride def incl(x: Int): IntSet = {")
    println("\t\tif (x < elem) new NonEmpty(elem, left.incl(x), right)")
    println("\t\telse if (x > elem) new NonEmpty(elem, left.incl(x), right.incl(x))")
    println("\t\telse this")
    println("\t}")
    println("\toverride def contains(x: Int): Boolean = {")
    println("\t\tif (x < elem) left.contains(x)")
    println("\t\telse if (x > elem) right.contains(x)")
    println("\t\telse true")
    println("\t}")
    println("}")
    println("class Empty extends IntSet {")
    println("\toverride def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)")
    println("\toverride def contains(x: Int): Boolean = false")
    println("}")
    println("Empty and NonEmpty extend class IntSet, so they conform to type IntSet")
    println("So object of type Empty or NonEmpty can be used wherever object of type IntSet is required")
    println("IntSet is superclass of Empty and NonEmpty")
    println("Every class defined by use extends some other class")
    println("Default superclass is java.lang.Object")
    println("Direct or indirect superclasses of a class C are base classes of class C")
    println("Base classes of NonEmpty are: IntSet, Object")
  }

  def implementationAndOverriding(): Unit = {
    println("\nImplementation and overriding")
    println("Classes: Empty and NonEmpty implement abstract methods from trait IntSet")
    println("It is also possible to override existing implementations")
    abstract class Base {
      def foo = 1

      def bar: Int
    }
    println("abstract class Base {")
    println("\tdef foo = 1")
    println("\tdef bar: Int")
    println("}")
    class Sub extends Base {
      override def foo = 2

      def bar = 3
    }
    println("class Sub extends Base {")
    println("\toverride def foo = 2")
    println("\tdef bar = 3")
    println("}")

  }

  def objectDefinitions(): Unit = {
    println("\nObject Definitions")
    println("Empty class extending IntSet could be defined as object because one could argue that there is only one empty IntSet")
    println("It can be done by changing keyword \"class\" to \"object\" (and remove \"new\" before \"Empty\")")
    println("object Empty extends IntSet {")
    println("\toverride def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)")
    println("\toverride def contains(x: Int): Boolean = false")
    println("}")
    println("This defines a singleton object named Empty")
    println("No other instance of Empty can be created")
    println("Singletons are values, so Empty evaluates to itself")
  }

  def dynamicBinding(): Unit = {
    println("\nDynamic Binding")
    println("Dynamic method dispatch - Code invocation during method call depends on the RUNTIME type of the object that contains method")
    println("Dynamic method dispatch - executed method implementation can be based on (because of runtime polymorphism):")
    println("\t1. receiver (single dispatch)")
    println("\t2. all objects involved (multiple dispatch - fe. double dispatch - fe. visitor pattern in Java)")
    println("\t3. result of an arbitrary function (predicate dispatch)")

    val hare = new Hare
    val bunny = new Bunny
    val lion = new Lion
    println(s"val hare = new Hare; hare: $hare")
    println(s"val bunny = new Bunny; bunny: $bunny")
    println(s"val lion = new Lion; lion: $lion")
    println
    MyAssertions.myAssert2Arg(hare, bunny, "Hare encountered another RabbitLike", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(hare, bunny, "Hare encountered another Bunny", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    MyAssertions.myAssert2Arg(hare, lion, "Hare encountered another Animal", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(hare, lion, "Hare encountered another Lion", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    println
    MyAssertions.myAssert2Arg(lion, bunny, "Lion encountered another Animal", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(lion, bunny, "Lion encountered another Bunny", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    MyAssertions.myAssert2Arg(lion, hare, "Lion encountered another Animal", AnimalGen.encounterAbstraction, "AnimalGen.encounterAbstraction")
    MyAssertions.myAssert2Arg(lion, hare, "Lion encountered another RabbitLike", AnimalGen.encounterImplementation, "AnimalGen.encounterImplementation")
    println("Single dispatching always happens when we call method of given object:")
    println("hare match {")
    println("\tcase animal1: Animal => bunny match {")
    println("\t\tcase animal2: Animal => animal1.encounter(animal2)")
    println("\t}")
    println("}")
    println("Double dispatching happens only when the argument object is matched to proper type:")
    println("hare match {")
    println("\tcase hare1: Hare => bunny match {")
    println("\t\tcase bunny2: Bunny => hare1.encounter(bunny2)")
    println("\t}")
    println("}")
    println("Or if matching of argument object is inside given method like so:")
    println("class Bunny extends Animal {")
    println("\toverride def encounter(animal: Animal) = animal match {")
    println("\t\tcase bunny: Bunny => \"Bunny encountered another Bunny\"")
    println("\t\tcase animal: Animal => \"Bunny encountered another Animal\"")
    println("\t}")
    println("}")
    println("Same matching principle can be used for predicate dispatching - different evaluation depending on some kind function")
    println("class Bunny extends Animal {")
    println("\toverride def encounter(a: Animal) = a match {")
    println("\t\tcase b: Bunny => \"Bunny encountered another Bunny\"")
    println("\t\tcase l: Lion if (l.isHungry) => \"Bunny encountered another HUNGRY Lion\"")
    println("\t\tcase l: Lion => \"Bunny encountered another NOT hungry Lion\"")
    println("\t\tcase _ => \"Bunny encountered another Animal\"")
    println("\t}")
    println("}")
  }

  def dynamicDispatching(): Unit = {
    println("\nDynamic dispatching")
    // Source: http://cleverlytitled.blogspot.com/2010/01/dynamic-dispatch-in-scala.html
    println("Multimethods vs. Pattern matching")
    println("Pattern matching is usually closed - all possibilities must be enumerated in single scope")
    println("Pattern matching is usually order dependent")
    println("Pattern matching has sugar for structure, type matches, exhaustiveness checking")
    println("Multimethods are an open set - possibility of defining new methods at any time, anywhere")
    println("Multimethods are independent of definition order")
    println("Multimethods are dynamic - good fit for Clojure")
    DynamicDispatchingPresentation.main()
  }

  def traits(): Unit = {
    println("\nTraits")
    println("A class can have only 1 (abstract) superclass")
    println("But can implement multiple traits (\"with\" keyword)")
    println("But traits cannot have constructor parameters")

    println("trait Planar {")
    println("\tvar i = 1")
    println("\tdef getI = i")
    println("\tdef setI(int: Int): Unit = { i = int }")
    println("\tdef getWidth: Int")
    println("\tdef getHeight: Int")
    println("\tdef getSurface = getWidth * getHeight")
    println("}")
    println
    println("trait Shape {")
    println("\tdef draw: Unit")
    println("}")
    println
    println("trait Movable {")
    println("\tdef move(deltaX: Int, deltaY: Int): Unit")
    println("}")
    println
    println("class Square(x: Int, y: Int) extends Shape with Planar with Movable {")
    println("\tvar width: Int = x")
    println("\tvar height: Int = y")
    println("\toverride def draw: Unit = println(s\"draw ${this.toString}\")")
    println("\toverride def getWidth: Int = this.width")
    println("\toverride def getHeight: Int = this.height")
    println("\toverride def move(deltaX: Int, deltaY: Int): Unit = {")
    println("\t\tthis.width = getWidth + deltaX")
    println("\t\tthis.height = getHeight + deltaY")
    println("\t}")
    println("\toverride def toString: String = s\"${this.getClass.getSimpleName}{ width: $getWidth, height: $getHeight, surface: $getSurface, i: $getI }\"")
    println("}")
    println

    val square1 = new Square(1, 2)
    println("val square1 = new Square(1, 2)")
    print("square1.draw: ")
    square1.draw

    val square2 = new Square(3, 4)
    println("val square2 = new Square(3, 4)")
    print("square2.draw: ")
    square2.draw

    square1.move(3, 4)
    println("square1.move(3, 4)")
    square1.setI(100)
    println("square1.setI(100)")

    print("square1.draw: ")
    square1.draw

    print("square2.draw: ")
    square2.draw
  }

  def scalaClassHierarchy(): Unit = {
    println("Scala class hierarchy")
    println("1. Any")
    println("\t1.1. AnyVal")
    println("\t\t1.1.1. Unit")
    println("\t\t1.1.2. Boolean")
    println("\t\t1.1.3. Char")
    println("\t\t1.1.4. Byte")
    println("\t\t1.1.5. Short")
    println("\t\t1.1.6. Int")
    println("\t\t1.1.7. Long")
    println("\t\t1.1.8. Float")
    println("\t\t1.1.9. Double")
    println("\t1.2. AnyRef <=> java.lang.Object")
    println("\t\t1.2.1. String")
    println("\t\t1.2.2. ... other Java classes ...")
    println("\t\t1.2.3. ScalaObject (trait)")
    println("\t\t\t1.2.3.1. Iterable")
    println("\t\t\t1.2.3.2. Seq")
    println("\t\t\t1.2.3.3. List")
    println("\t\t\t1.2.3.x. ... other Scala classes ...")
    println("\t\t\t1.2.x.x...x. Null (value: null) [subtype of every AnyRef]")
    println("\t\t\t\t1.x.x.x...x. Nothing (value: there is no value) [subtype of every Any]")
  }

  def exercise(): Unit = {
    println("\nExercise")

    abstract class Reducer(startInt: Int) {
      def combine(x: Int, y: Int): Int

      def reduce(xs: List[Int]): Int =
        xs match {
          case Nil => startInt
          case y :: ys => combine(y, reduce(ys))
        }
    }

    object Product extends Reducer(1) {
      def combine(x: Int, y: Int): Int = x * y
    }

    object Sum extends Reducer(0) {
      def combine(x: Int, y: Int): Int = x + y
    }

    val nums = List(1, 2, 3, 4)

    object ReducerGen {
      def reduceUsingProduct(obj: Any): Any = obj match {
        case list: List[Int] => Product.reduce(list)
        case _ => throw new UnimplementedCaseException(this, "reduceUsingProduct", obj)
      }

      def reduceUsingSum(obj: Any): Any = obj match {
        case list: List[Int] => Sum.reduce(list)
        case _ => throw new UnimplementedCaseException(this, "reduceUsingSum", obj)
      }
    }

    assert(Product.reduce(nums) == 24, "Product.reduce(nums) == 24")
    MyAssertions.myAssert1Arg(nums, 24, ReducerGen.reduceUsingProduct, "ReducerGen.reduceUsingProduct")
    assert(Sum.reduce(nums) == 10, "Sum.reduce(nums) == 10")
    MyAssertions.myAssert1Arg(nums, 10, ReducerGen.reduceUsingSum, "ReducerGen.reduceUsingSum")
  }
}
