package org.exercises.scala.lib.standard

import org.exercises.scala.lib.standard.handle.error.ErrorHandling
import org.exercises.scala.lib.standard.list.{CommonListOperations, ListBasicsAndConstructors, ManipulatingMatchingLists, SortingListsByInsertion}
import org.exercises.scala.lib.standard.option.Optionals

object StandardLibrary {

  def main(): Unit = {
    ListBasicsAndConstructors.main()
    ManipulatingMatchingLists.main()
    SortingListsByInsertion.main()
    CommonListOperations.main()
    Optionals.main()
    ErrorHandling.main()
  }
}
