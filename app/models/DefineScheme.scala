package models

import models.entities.{Article, Chapter}
import org.squeryl.Schema

object DefineScheme extends Schema{
  val article = table[Article]("article")
  val chapter = table[Chapter]("chapter")
}
