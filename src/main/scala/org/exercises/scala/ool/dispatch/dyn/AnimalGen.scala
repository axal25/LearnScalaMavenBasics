package org.exercises.scala.ool.dispatch.dyn

import org.exercises.scala.lib.standard.UnimplementedCaseException

object AnimalGen {
  def encounterAbstraction(obj1: Any, obj2: Any): Any = {
    obj1 match {
      case rabbitLike1: RabbitLike => obj2 match {
        case rabbitLike2: RabbitLike => rabbitLike1.encounter(rabbitLike2)
        case animal2: Animal => rabbitLike1.encounter(animal2)
        case _ => throw new UnimplementedCaseException(this, "encounter", rabbitLike1, obj2)
      }
      case animal1: Animal => obj2 match {
        case rabbitLike2: RabbitLike => animal1.encounter(rabbitLike2)
        case animal2: Animal => animal1.encounter(animal2)
        case _ => throw new UnimplementedCaseException(this, "encounter", animal1, obj2)
      }
      case _ => throw new UnimplementedCaseException(this, "encounter", obj1, obj2)
    }
  }

  def encounterImplementation(obj1: Any, obj2: Any): Any = obj1 match {
    case hare1: Hare => obj2 match {
      case hare2: Hare => hare1.encounter(hare2)
      case bunny2: Bunny => hare1.encounter(bunny2)
      case lion2: Lion => hare1.encounter(lion2)
      case animal2: Animal => hare1.encounter(animal2)
      case _ => throw new UnimplementedCaseException(this, "encounter", hare1, obj2)
    }
    case bunny1: Bunny => obj2 match {
      case hare2: Hare => bunny1.encounter(hare2)
      case bunny2: Bunny => bunny1.encounter(bunny2)
      case lion2: Lion => bunny1.encounter(lion2)
      case animal2: Animal => bunny1.encounter(animal2)
      case _ => throw new UnimplementedCaseException(this, "encounter", bunny1, obj2)
    }
    case lion1: Lion => obj2 match {
      case hare2: Hare => lion1.encounter(hare2)
      case bunny2: Bunny => lion1.encounter(bunny2)
      case lion2: Lion => lion1.encounter(lion2)
      case animal2: Animal => lion1.encounter(animal2)
      case _ => throw new UnimplementedCaseException(this, "encounter", lion1, obj2)
    }
    case animal1: Animal => obj2 match {
      case hare2: Hare => animal1.encounter(hare2)
      case bunny2: Bunny => animal1.encounter(bunny2)
      case lion2: Lion => animal1.encounter(lion2)
      case animal2: Animal => animal1.encounter(animal2)
      case _ => throw new UnimplementedCaseException(this, "encounter", animal1, obj2)
    }
    case _ => throw new UnimplementedCaseException(this, "encounter", obj1, obj2)
  }
}
