package models.entities

import play.api.libs.json.Json

case class Article (id: String,
                     chapterId: String,
                     shortName: String,
                     fullName: String,
                     text: String)

object Article{
  implicit val jsonArticle = Json.format[Article]
}

