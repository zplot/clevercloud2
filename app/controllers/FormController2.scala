package controllers

import javax.inject.Inject
import models.SentData2
import play.api.data._
import play.api.mvc._

class FormController2 @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import Form2._
  private val postUrl = routes.FormController2.askForData
  def showAnswer = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.showForm2(form, postUrl))
  }
  def askForData = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data2] =>
      BadRequest(views.html.showForm2(formWithErrors, postUrl))
    }
    val successFunction = { data: Data2 =>
      val textoPasado = SentData2(p = data.p, poly = data.poly)
      Ok(views.html.showFormAnswer2(textoPasado)).flashing("info" -> "Widget added!")
    }
    val formValidationResult = form.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
}
