package org.exercises.scala.lib.standard.list

object ListBasicsAndConstructors {

  def main(): Unit = {
    lists()
    listConstructors()
  }

  def lists(): Unit = {
    println("\nLists")
    println("Lists are immutable - elements of lists cannot be changed")
    println("Lists are recursive")
    println("List are homogeneous - are intended to be composed of elements of the same type")

    val fruits1: List[String] = List("apples", "oranges", "pears")
    val fruits2 = List("apples", "oranges", "pears")
    val numbers1: List[Int] = List(1, 2, 3, 4)
    val numbers2 = List(1, 2, 3, 4)
    val empty1: List[Nothing] = List()
    val empty2: List[Nothing] = Nil
    val empty3: Nil.type = Nil
    val empty4 = Nil
    val empty5 = Nil
    val empty = List()
    val diag1: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
    val diag2 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))

    val heterogeneousList: List[Any] = List(1, "1", '1')
    println("But it is possible to store all types insides one list using List[Any] - common ancestor: Any")
    println("val heterogeneousList: List[Any] = List(1, \"1\", '1') | heterogeneousList: " + heterogeneousList)
  }

  def listConstructors(): Unit = {
    println("List constructors")
    println("All lists are constructed from empty list Nil")
    println("val empty2: List[Nothing] = Nil")
    println("Construction operations \"cons\" using operator \"::\" like in \"x :: xs\" gives a new list with 1st element \"x\" followed by list \"xs\"")
    println("Examples:")
    val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
    println("val fruit = \"apples\" :: (\"oranges\" :: (\"pears\" :: Nil))")
    val numbers: List[Int] = 1 :: (2 :: (3 :: (4 :: Nil)))
    println("val numbers = 1:: (2 :: (3 :: (4 :: Nil)))")
    val empty = Nil
    println("val empty = Nil")

    println("\nRight associativity")
    println("By conversion operators ending in \":\" associate to the right")
    println("A :: B :: C  <==> A :: (B :: C)")
    val assertion1 = "1:: (2 :: (3 :: (4 :: Nil))) == 1 :: 2 :: 3 :: 4 :: Nil"
    assert(1 :: (2 :: (3 :: (4 :: Nil))) == 1 :: 2 :: 3 :: 4 :: Nil, assertion1)
    println(assertion1)
    println("Those operators ending in \":\" are also seen as method calls of the right-hand operand")
    val assertion2 = "1 :: 2 :: 3 :: 4 :: Nil == Nil.::(4).::(3).::(2).::(1)"
    assert(1 :: 2 :: 3 :: 4 :: Nil == Nil.::(4).::(3).::(2).::(1), assertion2)
    println(assertion2)
  }
}
