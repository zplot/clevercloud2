package controllers

object Graph1 {
  import play.api.data.Forms._
  import play.api.data.Form

  /**
   * A form processing DTO that maps to the form below.
   *
   * Using a class specifically for form binding reduces the chances
   * of a parameter tampering attack and makes code clearer.
   */
  case class DataG(
                    name: String,
                    function: String,
                    xAxisFrom: String,
                    xAxisTo: String,
                    yAxisFrom: String,
                    yAxisTo: String,
                    grid: String,
                    graphColor: String
                  )

  /**
   * The form definition for the "create a widget" form.
   * It specifies the form fields and their types,
   * as well as how to convert from a Data to form data and vice versa.
   */
  val formG: Form[DataG] = Form(
    mapping(
      "name" -> nonEmptyText,
      "function" -> nonEmptyText,
      "xAxisFrom" -> nonEmptyText,
      "xAxisTo" -> nonEmptyText,
      "yAxisFrom" -> nonEmptyText,
      "yAxisTo" -> nonEmptyText,
      "grid" -> text,
      "graphColor" -> text
    )(DataG.apply)(DataG.unapply)
  )
}
