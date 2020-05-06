package org.exercises.scala.lib.standard.list

object ManipulatingMatchingLists {
  val outputArray = Array(
    "Empty list",
    "List containing List starting with 2, ... (and has more elements)",
    "List of Int that starts with 1, 2, ... (and has more elements)",
    "List of length 1",
    "Exception - Unhandled case"
  )

  def main(): Unit = {
    println("\nManipulating (matching) lists")
    testListMatcherX(listMatcher1)
    println()
    testListMatcherX(listMatcher2)
  }

  def listMatcher1(list: List[Any]): String = list match {
    case List() => outputArray(0) // same as Nil
    case List(2 :: xs) => outputArray(1)
    case 1 :: 2 :: xs => outputArray(2)
    case x :: Nil => outputArray(3) // same as List(x)
    case _ => throw new Exception(outputArray(4))
  }

  def listMatcher2(list: List[Any]): String = list match {
    case Nil => outputArray(0) // same as List()
    case List(2 :: xs) => outputArray(1)
    case 1 :: 2 :: xs => outputArray(2)
    case List(x) => outputArray(3) // same as x :: Nil
    case _ => throw new Exception(outputArray(4))
  }

  def testListMatcherX(method: List[Any] => String): Unit = {
    val list1 = List()
    val list2 = List(List(2, "a", 'b'))
    val list3 = List(1, 2, "a", 'b')
    val list4 = List(0)
    val list5 = List(0.111111, 0.2222222222)

    println("list1: " + method(list1))
    println("list2: " + method(list2))
    println("list3: " + method(list3))
    println("list4: " + method(list4))
    try {
      print("list5: ")
      println(method(list5))
    } catch {
      case e: Exception => println(e.getMessage)
    }
    assert(method(list1) == outputArray(0), "method(list1) == outputArray(0)")
    assert(method(list2) == outputArray(1), "method(list2) == outputArray(1)")
    assert(method(list3) == outputArray(2), "method(list3) == outputArray(2)")
    assert(method(list4) == outputArray(3), "method(list4) == outputArray(3)")
  }
}
