package controllers

import javax.inject._

import helpers.IdGenerator
import models.entities.{Article, Chapter}
import models.service.{ArticleService, ChapterService}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import models.load.LoadInitialDataService
@Singleton
class HomeController @Inject()(articleService: ArticleService, chapterService: ChapterService, loadService: LoadInitialDataService) extends Controller
{
  def index() = Action {
    Ok(views.html.appPage(articleService.list(), chapterService.list(),hierarchical()))
  }

  def article(id: String) = Action{
    Ok(views.html.articlePage(chapterService.list(), articleService.list(),articleService.findById(id)))
  }
  def chapter(id: String) = Action{
    Ok(views.html.chapterPage(chapterService.list(), id))
  }

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
      if(!isArticleExist(article.fullName)){
        articleService.add(article)
      }
      Redirect(routes.HomeController.index())

  }

  private def isArticleExist(fullName: String): Boolean = {
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
      Redirect(routes.HomeController.article(article.id))
  }

  def removeArticle(id: String) = Action
  {
    implicit request =>
      articleService.delete(id)
      Redirect(routes.HomeController.index())
  }

  def addNewChapter() = Action{
    implicit request =>
      val chapter = addChapter.bindFromRequest().get.copy(id = IdGenerator.randomId)
      if(!isChapterExist(chapter.fullName)){
        chapterService.add(chapter)
      }
        Redirect(routes.HomeController.index())
  }
  def hierarchical(): Seq[Chapter] ={
    val chapters = chapterService.list()
    def child(chap: Chapter): Seq[Chapter] = {
      chap +: chapters.filter(_.parentId.contains(chap.id)).flatMap(child)
    }
    chapters.flatMap(
      chap =>
        if(chap.parentId.isEmpty) {
          child(chap)
        }else{
          Seq.empty
        }
    )
  }

  private def isChapterExist(fullName: String): Boolean ={
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
      Redirect(routes.HomeController.index())
  }

  def removeChapter(id: String) = Action{
    implicit request =>
      articleService.deleteWhere(id)
      chapterService.delete(id)
      Redirect(routes.HomeController.index())
  }
  def setDB() = Action {
    implicit request =>
      loadService.loadDBwithXML
      Redirect(routes.HomeController.index())
  }
}
