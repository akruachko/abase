package models.entities

import org.squeryl.KeyedEntity
import play.api.libs.json.Json

case class Article (id: String,
                     chapterId: String,
                     shortName: String,
                     fullName: String,
                     text: String)
extends KeyedEntity[String]

object Article{
  implicit val jsonArticle = Json.format[Article]
}

