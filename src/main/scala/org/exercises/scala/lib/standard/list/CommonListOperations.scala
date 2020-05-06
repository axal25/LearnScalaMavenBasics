package org.exercises.scala.lib.standard.list

import util.MyAssertions

object CommonListOperations {

  def main(): Unit = {
    println("\nCommon Operations on Lists")
    listMap()
    listFilter()
    listFlatMap()
  }

  def listMap(): Unit = {
    println("\nmap method: List(...).map(T => T)")
    val list1 = List(0, 1, 2, 3, 4, 5, 6)
    val expectedList1 = List(1, 2, 3, 4, 5, 6, 7)
    val assertion1 = s"${list1}.map(x => x+1) == ${expectedList1}"
    assert(list1.map(x => x + 1) == expectedList1, assertion1)
    println(assertion1)
    MyAssertions.myAssert(list1, expectedList1, ListsImpl.map, "ListsImpl.map")
  }

  def listFilter(): Unit = {
    println("\nfilter method: List(...).filter(T => Boolean)")
    val list1 = List(0, 1, 2, 3, 4, 5, 6)
    val expectedList1 = List(0, 2, 4, 6)
    val assertion1 = s"${list1}.filter(x => x % 2 == 0) == ${expectedList1}"
    assert(list1.filter(x => x % 2 == 0) == expectedList1, assertion1)
    println(assertion1)
    MyAssertions.myAssert(list1, expectedList1, ListsImpl.filter, "ListsImpl.filter")
  }

  def listFlatMap(): Unit = {
    val flatMapDescription = "flatMap method: val originList:: List[T] = List(...); originList.flatMap(T => List[T]): List[T] = "
    println(s"\n$flatMapDescription{ List(t1_1, t1_2, t1_3, ..., t1_n, ..., tm_n }")
    println(s"$flatMapDescription{ t1_1 :: t1_2 :: t_3 :: ... t1_n :: tm_n :: Nil }")
    val list1 = List(0, 5, 10, 15, 20, 25, 30)
    val expectedList1 = List(0, 1, 2, 5, 6, 7, 10, 11, 12, 15, 16, 17, 20, 21, 22, 25, 26, 27, 30, 31, 32)
    val expectedList2 = 0 :: 1 :: 2 :: 5 :: 6 :: 7 :: 10 :: 11 :: 12 :: 15 :: 16 :: 17 :: 20 :: 21 :: 22 :: 25 :: 26 :: 27 :: 30 :: 31 :: 32 :: Nil

    val actualOutcome1 = s"Actual outcome: ${list1.flatMap(ListsImpl.Transformations.flatMapTransform)}"

    val assertion1 = s"${list1}.flatMap(int => List(int, int + 1, int + 2)) == ${expectedList1}"
    assert(list1.flatMap(int => List(int, int + 1, int + 2)) == expectedList1, assertion1 + actualOutcome1)
    println(assertion1)

    val assertion2 = s"${list1}.flatMap(int => List(int, int + 1, int + 2)) == ${expectedList2}"
    assert(list1.flatMap(int => List(int, int + 1, int + 2)) == expectedList2, assertion2 + actualOutcome1)
    println(assertion2)

    MyAssertions.myAssert(list1, expectedList1, ListsImpl.flatMap, "FlatMapImpl.flatMap")
    MyAssertions.myAssert(list1, expectedList2, ListsImpl.flatMap, "FlatMapImpl.flatMap")
  }

  object ListsImpl {
    object Transformations {
      def mapTransform(obj: Any): Any = obj match {
        case int: Int => int + 1
        case _ => throw new Exception("Unimplemented case")
      }

      def filterTransform(obj: Any): Boolean = obj match {
        case int: Int => int % 2 == 0
        case _ => throw new Exception("Unhandled case")
      }

      def flatMapTransform(obj: Any): List[Any] = obj match {
        case int: Int => List(int, int + 1, int + 2)
        case _ => throw new Exception("Unimplemented transform")
      }
    }

    def map(obj: Any): Any = Lists.map(obj, Transformations.mapTransform)

    def filter(obj: Any): Any = Lists.filter(obj, Transformations.filterTransform)

    def flatMap(obj: Any): Any = Lists.flatMap(obj, Transformations.flatMapTransform)
  }

  object Lists {

    def map(obj: Any, transform: Any => Any): Any = obj match {
      case list: List[Any] => list.map(transform)
      case _ => throw new Exception("Unimplemented case")
    }

    def filter(obj: Any, transform: Any => Boolean): Any = obj match {
      case list: List[Any] => list.filter(transform)
      case _ => throw new Exception("Unimplemented case")
    }

    def flatMap(obj: Any, transform: Any => List[Any]): Any = obj match {
      case list: List[Any] => list.flatMap(transform)
      case _ => throw new Exception("Unimplemented case")
    }
  }
}
