package controllers

import javax.inject._
import play.api.mvc._
import models._

@Singleton
class EsquemaController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def esquema = Action {

    val datosParaPasar: Datos = Datos("Hola", 457, List(1,2,3,4,5,6,7))

    val texto1 = "Título de la página"
    val texto2 = "Esto está en la variable texto2 definida en EsquemaController"

    val parameters: (String, String, Datos) = (texto1, texto2, datosParaPasar)

    Ok(views.html.esquema(parameters))
  }

  def esquema5 = Action {

    val datosParaPasar: Datos = Datos("Hola", 457, List(1,2,3,4,5,6,7))

    val texto1 = "Título de la página"
    val texto2 = "Esto está en la variable texto2 definida en EsquemaController"

    val parameters: (String, String, Datos) = (texto1, texto2, datosParaPasar)

    Ok(views.html.esquema5(parameters))
  }

}
