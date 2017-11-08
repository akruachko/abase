package models.entities

import play.api.libs.json.Json

case class Chapter(
                    id: String,
                    parentId: Option[String],
                    shortName: String,
                    fullName: String,
                    listOfNestedArticles: String,
                    listOfNestedChapters: String
                  )
object Chapter{
  implicit val chapter = Json.writes[Chapter]
}