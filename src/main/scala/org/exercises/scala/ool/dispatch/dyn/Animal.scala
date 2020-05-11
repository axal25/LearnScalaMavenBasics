package org.exercises.scala.ool.dispatch.dyn

trait Animal {
  val encounter = DynamicDispatch.defMultiMethod[Animal, String]
  encounter.defMethod({ case animal: Animal => "ANIMAL encountered ANIMAL" })

  override def toString: String = this.getClass.getSimpleName
}
