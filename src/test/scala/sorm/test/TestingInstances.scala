package sorm.test

import sorm._, core._
import sext._, embrace._

object TestingInstances {
  private def url ( t : DbType )
    = t match {
        case DbType.H2 => "jdbc:h2:mem:test"
        case DbType.Mysql => "jdbc:mysql://localhost/test"
        case DbType.Sqlite => "jdbc:sqlite::memory:"
        case DbType.Hsqldb => "jdbc:hsqldb:mem:testdb"
        case _ => ???
      }

  private def name ( t : DbType )
    = t.toString

  private def instance ( entities : Traversable[Entity], t : DbType, poolSize : Int )
    = new Instance(entities, url(t), poolSize = poolSize, initMode = InitMode.DropAllCreate)

  def instances
    ( entities : Traversable[Entity],
      poolSizes : Seq[Int] = 1 :: 6 :: Nil,
      dbTypes : Seq[DbType] = DbType.Mysql :: DbType.H2 :: Nil )
    : Stream[(Instance, String)]
    = dbTypes.toStream.flatMap(t => poolSizes.map(t -> _))
        .map{ case (t, s) => instance(entities, t, s) -> (name(t) + ":" + s) }
}
