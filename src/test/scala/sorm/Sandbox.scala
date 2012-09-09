package sorm

import core._
import jdbc._
import persisted.Persisted
import reflection.Reflection
import samples._
import extensions.Extensions._

object Sandbox extends App {

  import reflect.runtime.universe._
  import sorm.reflection.ScalaApi._

  object ResponseType extends Enumeration {
    val Listing, Album = Value
  }

  Reflection[ResponseType.Value].inheritsFrom(Reflection[Enumeration#Value])
    .treeString.trace()
}
