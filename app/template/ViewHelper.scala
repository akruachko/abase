package template

import controllers.routes
import models.entities.{Article, Chapter}
import play.twirl.api.Html

object ViewHelper {

  def cascadTable2(chaptersCategory: Seq[Chapter]): Seq[Html] = {
    for(i <- chaptersCategory.indices) yield {
      if(chaptersCategory(i).parentId.isEmpty){
        Html(s"""<li><a href="#">${chaptersCategory(i).shortName}</a></li>\n""")
      }else{
        Html(
          (for(j <- 1 until chaptersCategory.size) yield {
          if(chaptersCategory(j).parentId.contains(chaptersCategory(j - 1).id)){
            val chap = chaptersCategory.filter(_.parentId.contains(chaptersCategory(i - 1).id))
            Seq(s"""<ol>""",
            chap.map(ch => s"""<li><a href="#">${ch.shortName}</a></li>\n""").mkString,
            cascadTable2(chap).mkString("\n"),
            s"""</ol>""").mkString("\n")
          }else {""}
        }).mkString("\n"))


      }
    }

  }
  def cascadTable(chaptersCategory: Seq[Chapter], articles: Seq[Article]):Html =
  {
    Html(
      (for(i <- chaptersCategory.indices) yield {
        if(chaptersCategory(i).parentId.isEmpty){ s"""\n            <li><a href="#">${chaptersCategory(i).shortName}</a>&nbsp;-&nbsp;<a href="/removeChapter/${chaptersCategory(i).id}">Remove</a></li>"""
        }else {
          """<ol>"""+
            (for(j <- 1 until chaptersCategory.size) yield {
              if(chaptersCategory(j).parentId.contains(chaptersCategory(i - 1).id)){
                s"""<li><a href="#">${chaptersCategory(j).shortName}</a>&nbsp;-&nbsp;<a href="/removeChapter/${chaptersCategory(j).id}">Remove</a></li><ol class='without-numbering'>""" +
                articles.filter(chaptersCategory(j).id == _.chapterId).map(a => s"""<li><a href="/article/${a.id}"> ${a.shortName} </a>&nbsp;-&nbsp;<a href="/removeChapter/${a.id}">Remove</a></li>""").mkString("\n") +
                 """</ol>"""
              }else ""
            }).mkString("\n")+
            """</ol>"""
        }
      }).mkString("\n"))
  }

  def getSomeArticle(data: Article, chapters: Seq[Chapter]): Html = {
    val chap = chapters.find(_.id == data.chapterId).getOrElse("").asInstanceOf[Chapter]
    val mainChap = chapters.find(ch => chap.parentId.contains(ch.id)).get
    Html(
      s"""<h3>${mainChap.fullName}<-${chap.fullName}</h3>""" +
      s"""<h3>${data.fullName}</h3>\n""" +
      s"""<p>${data.text}</p>"""
    )
  }
}
