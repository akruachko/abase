package models.entities

import play.api.libs.json.Json

case class Chapter(
                    id: String,
                    parentId: Option[String],
                    shortName: String,
                    fullName: String
                  )
object Chapter{
  implicit val jsonChapter = Json.format[Chapter]
}