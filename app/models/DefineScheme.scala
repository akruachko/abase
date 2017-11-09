package models

import models.entities.{Article, Chapter}
import org.squeryl.Schema

object DefineScheme extends Schema{
  val article = table[Article]
  val chapter = table[Chapter]
}
