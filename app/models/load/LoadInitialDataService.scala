package models.load

import javax.inject.Inject

import com.google.inject.AbstractModule
import com.typesafe.config.ConfigFactory
import models.entities.{Article, Chapter}
import models.service.{ArticleService, ChapterService}
import play.api.inject.ApplicationLifecycle

import scala.xml.XML

class LoadInitialDataService @Inject()(chapterService: ChapterService, articleService: ArticleService){
  lazy val config = ConfigFactory.load()

  def loadDBwithXML: Unit = {
    val pathXmlDB = config.getString("firstStartXml")
    val xmlDB = XML.loadFile(pathXmlDB)
    val article = (xmlDB \\ "DB" \\ "AllArticle" \\ "Article").map { arti =>
      Article((arti \\ "id").text.toString,  (arti \\ "chapterId").text.toString, (arti \\ "shortName").text.toString, (arti \\ "fullName").text.toString, (arti \\ "text").text.toString)
    }
    val chapter = (xmlDB \\ "DB" \\ "AllChapter" \\ "Chapter").map { ch =>
      Chapter((ch \\ "id").text.toString, if((ch \\ "parentId").text.isEmpty) None else Some((ch \\ "parentId").text.toString), (ch \\ "shortName").text.toString, (ch \\ "fullName").text.toString)
    }
    chapter.foreach { ch =>
      chapterService.add(ch)
    }
    article.foreach { ar =>
      articleService.add(ar)
    }
  }

}
