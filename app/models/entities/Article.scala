package models.entities

import play.api.libs.json.Json

case class Article (
                     id: String,
                     parentId: Option[String],
                     shortName: String,
                     fullName: String,
                     text: String
                    )

object Article{
  implicit val articles = Json.writes[Article]
}