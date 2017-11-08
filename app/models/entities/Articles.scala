package models.entities

import org.squeryl.annotations.Column
import play.api.libs.json.Json

case class Article (
                      @Column("id_article") id: String,
                      @Column("short_name_article") shortName: String,
                      @Column("full_name_article") fullName: String,
                      @Column("text_article") text: String
                    )

object Article{
  implicit val articles = Json.writes[Article]
}