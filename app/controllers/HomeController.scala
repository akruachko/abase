package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import helpers.GeneratorIdHelper

@Singleton
class HomeController @Inject() extends Controller with GeneratorIdHelper
{
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
