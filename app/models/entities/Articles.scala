package models.entities

import play.api.libs.json.Json

case class Article (
                      idArticle: String,
                      parentIdArticle: String,
                      shortNameArticle: String,
                      fullNameArticle: String,
                      textOfArticle: String
                    )

object Article{
  implicit val articles = Json.writes[Article]
}