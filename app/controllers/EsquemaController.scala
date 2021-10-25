package controllers

import javax.inject._
import play.api.mvc._
import models._

@Singleton
class EsquemaController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {



  def esquema5 = Action {

    val datosParaPasar: Datos = Datos("Hola", 457, List(1,2,3,4,5,6,7))

    val pageTitle = "Math & Code"
    val texto2 = "Esto está en la variable texto2 definida en EsquemaController"

    val parameters: (String, String, Datos) = (pageTitle, texto2, datosParaPasar)

    Ok(views.html.esquema5(parameters))
  }

}
