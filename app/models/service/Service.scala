package models.service

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table

trait Service [T]{

  val table: Table[T]

  def delete(id: String):Unit
  def findById(id: String): T
  def updateEntity(data: T):Unit

  def add(data: T): Unit ={
    transaction{
      table.insert(data)
    }
  }
  def list(): Seq[T] = {
    transaction{
      from(table)(t => select(t)).toList
    }
  }
}
