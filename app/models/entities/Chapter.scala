package models.entities

import play.api.libs.json.Json

case class Chapter(
                    idChapter: String,
                    parentIdChapter: String,
                    shortNameChapter: String,
                    fullNameChapter: String,
                    listOfNestedArticles: String,
                    listOfNestedChapters: String
                  )
object Chapter{
  implicit val chapter = Json.writes[Chapter]
}