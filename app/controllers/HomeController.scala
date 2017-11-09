package controllers

import javax.inject._

import helpers.IdGenerator
import models.entities.{Article, Chapter}
import models.service.{ArticleService, ChapterService}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json

@Singleton
class HomeController @Inject()(articleService: ArticleService, chapterService: ChapterService) extends Controller
{
  def index = Action {
    Ok(views.html.index2(articleService.list(), chapterService.list()))
  }
//  def index = Action {
//    Ok(views.html.index("Hi"))
//  }
  val addArticle = Form(
    mapping(
      "id" -> text,
      "chapterId" -> text,
      "shortName" -> text,
      "fullName"  -> text,
      "text"      -> text
    )(Article.apply)(Article.unapply)
  )

  val addChapter = Form(
    mapping(
      "id" -> text,
      "parentId" -> optional(text),
      "shortName" -> text,
      "fullName" -> text
    )(Chapter.apply)(Chapter.unapply)
  )

  def addNewArticle() = Action{
    implicit request =>
      val article = addArticle.bindFromRequest().get.copy(id = IdGenerator.randomId)
      articleService.add(article)
      Ok
  }

  def isArticleExist(fullName: String): Boolean = {
    val articles = articleService.list()
    articles.exists(_.fullName == fullName)
  }

  def getAllArticles = Action {
    Ok(Json.toJson(articleService.list()))
  }

  def getArticle(id: String) = Action{
    implicit request => Ok(Json.toJson(articleService.findById(id)))
  }

  def updateArticle() = Action{
    implicit request =>
      val article = addArticle.bindFromRequest().get
      articleService.updateEntity(article)
      Ok
  }

  def removeArticle(id: String) = Action
  {
    implicit request =>
      articleService.delete(id)
      Ok
  }

  def addNewChapter() = Action{
    implicit request =>
      val chapter = addChapter.bindFromRequest().get.copy(id = IdGenerator.randomId)
      chapterService.add(chapter)
      Ok
  }

  def isChapterExist(fullName: String): Boolean ={
    val chapters = chapterService.list()
    chapters.exists(_.fullName == fullName)
  }

  def getAllChapters = Action{
    Ok(Json.toJson(chapterService.list()))
  }

  def getChapter(id: String) = Action{
    implicit request => Ok(Json.toJson(chapterService.findById(id)))
  }

  def updateChapter() = Action{
    implicit request =>
      val chapter = addChapter.bindFromRequest().get
      chapterService.updateEntity(chapter)
      Ok
  }

  def removeChapter(id: String) = Action{
    implicit request =>
      articleService.deleteWhere(id)
      chapterService.delete(id)
      Ok
  }
}
