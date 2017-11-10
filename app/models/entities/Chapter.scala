package models.entities

import org.squeryl.KeyedEntity
import play.api.libs.json.Json

case class Chapter(
                    id: String,
                    parentId: Option[String],
                    shortName: String,
                    fullName: String
                  ) extends KeyedEntity[String]
object Chapter{
  implicit val jsonChapter = Json.format[Chapter]
}