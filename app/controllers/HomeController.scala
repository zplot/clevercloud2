package controllers

import javax.inject._
import play.api.mvc._
import models._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {

    val datosParaPasar: Datos = Datos("Hola", 457, List(1,2,3,4,5,6,7))

    val pageTitle = "Math & Code"
    val texto2 = "Esto está en la variable texto2 definida en EsquemaController"

    val parameters: (String, String, Datos) = (pageTitle, texto2, datosParaPasar)

    Ok(views.html.esquema5(parameters))
  }


}
