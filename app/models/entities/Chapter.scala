package models.entities

import org.squeryl.annotations.Column
import play.api.libs.json.Json

case class Chapter(
                    @Column("id_chapter") id: String,
                    @Column("short_name_chapter") shortName: String,
                    @Column("full_name_chapter") fullName: String,
                    @Column("nested_article") parentArticle: String,
                    @Column("nested_chapter") parentChapter: String
                  )
object Chapter{
  implicit val chapter = Json.writes[Chapter]
}