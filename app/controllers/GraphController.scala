package controllers

import models.SentGraphData

import javax.inject.Inject
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
class GraphController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import Graph1._



  // The URL to the widget.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless i.e. all
  // of the "WidgetController" references are inside the .scala file.
  private val postUrl = routes.GraphController.askForData


  def showAnswer = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.showGraphForm(formG, postUrl))

  }

  // This will be the action that handles our form post
  def askForData = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[DataG] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.showGraphForm(formWithErrors, postUrl))
    }

    val successFunction = { data: DataG =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val datosPasados = SentGraphData(
                                        data.name,
                                        data.function,
                                        data.xAxisFrom,
                                        data.xAxisTo,
                                        data.xAxisFrom,
                                        data.yAxisTo,
                                        data.grid,
                                        data.graphColor)

      // Redirect(routes.WidgetController2.listWidgets).flashing("info" -> "Widget added!")
      Ok(views.html.showGraphAnswer(datosPasados)).flashing("info" -> "Widget added!")
    }

    val formValidationResult = formG.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
}
