package controllers

import javax.inject._
import play.api.mvc._
import models._
import play.twirl.api.Html


@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def pag1 = Action {

    val title = "Título de la página"
    val header = "Cabecera de la página"


    val estoEsHtml: play.twirl.api.Html = views.html.generic.ejemplo()
    val sinxfun: play.twirl.api.Html = views.html.specific.sinx()

    val section1 = Section(1, "Cabecera de la sección 1", estoEsHtml)

    val section2 = Section(2, "Cabecera de la sección 2", sinxfun)

    val section3 = Section(3, "Cabecera de la sección 3", estoEsHtml)

    val sections = List(section1, section2, section3)
    val page1 = Page(title, header, sections)

    Ok(views.html.generic.pag1(page1))
  }

}

