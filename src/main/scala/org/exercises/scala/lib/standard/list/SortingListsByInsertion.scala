package org.exercises.scala.lib.standard.list

object SortingListsByInsertion {

  def main(): Unit = {
    println("\nSorting Lists by Insertion Sort")
    testInsert()
    testInsertionSort()
  }

  val unknown1: (Any, Any) => Boolean = (elem1: Any, elem2: Any) => compare(elem1, elem2) < 0
  val condition: (Any, Any) => Boolean = unknown1
  val unknown2 = Nil

  private def compare(obj1: Any, obj2: Any): Int = (obj1, obj2) match {
    case (int1: Int, int2: Int) => int1 - int2
    case _ => throw new Exception("Unimplemented compare")
  }

  private def insert(elementToInsert: Any, list: List[Any]): List[Any] = list match {
    case List() => elementToInsert :: unknown2
    case head :: tail => {
      if (condition(elementToInsert, head)) elementToInsert :: head :: tail
      else head :: insert(elementToInsert, tail)
    }
  }

  def insertionSort(list: List[Any]): List[Any] = list match {
    case List() => List()
    case head :: tail => insert(head, insertionSort(tail))
  }

  def testInsert(): Unit = {
    val list1 = List(1, 3)
    val list2 = List(2, 3)
    val list3 = List(1, 2)
    val expectedList = 1 :: 2 :: 3 :: Nil
    assert(insert(2, list1) == expectedList, "insert(2, list1) == expectedList")
    assert(insert(1, list2) == expectedList, "insert(1, list2) == expectedList")
    assert(insert(3, list3) == expectedList, "insert(3, list3) == expectedList")
  }

  def testInsertionSort(): Unit = {
    val list0 = List(7, 3, 9, 2)
    assert(2 :: 3 :: 7 :: 9 :: Nil == insertionSort(list0), "2 :: 3 :: 7 :: 9 :: Nil == insertionSort(list0)")
  }
}
