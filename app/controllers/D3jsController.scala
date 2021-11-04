package controllers

import javax.inject._
import play.api.mvc._
import models._

@Singleton
class D3jsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def graph1 = Action {

    val pageTitle = "Math & Code"

    val graph1Data: D3jsGraph1 = D3jsGraph1(
      graphTitle = "Example from the book",
      graphCredit = "Line Chart, Multiple Series by Erin Petenko"
    )

    val datos: D3jsParameters = D3jsParameters(pageTitle, graph1Data)

    Ok(views.html.generic.graph1(datos))
  }

  def functionplot = Action {

    val pageTitle: String = "Math & Code"

    Ok(views.html.functionplot(pageTitle))
  }

}