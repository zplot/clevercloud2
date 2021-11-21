package controllers

import javax.inject.Inject
import models.SentData2
import play.api.data._
import play.api.mvc._



/**
 * The classic WidgetController using MessagesAbstractController.
 *
 * Instead of MessagesAbstractController, you can use the I18nSupport trait,
 * which provides implicits that create a Messages instance from a request
 * using implicit conversion.
 *
 * See https://www.playframework.com/documentation/2.8.x/ScalaForms#passing-messagesprovider-to-form-helpers
 * for details.
 */
class FormController2 @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import Form2._



  // The URL to the widget.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless i.e. all
  // of the "WidgetController" references are inside the .scala file.
  private val postUrl = routes.FormController2.askForData


  def showAnswer = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.showForm2(form, postUrl))
  }

  // This will be the action that handles our form post
  def askForData = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.showForm2(formWithErrors, postUrl))
    }

    val successFunction = { data: Data =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val textoPasado = SentData2(name = data.name)


      // Redirect(routes.WidgetController2.listWidgets).flashing("info" -> "Widget added!")
      Ok(views.html.showFormAnswer2(textoPasado)).flashing("info" -> "Widget added!")
    }

    val formValidationResult = form.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
}