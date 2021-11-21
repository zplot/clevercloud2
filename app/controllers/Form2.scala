package controllers

object Form2 {
  import play.api.data.Forms._
  import play.api.data.Form

  /**
   * A form processing DTO that maps to the form below.
   *
   * Using a class specifically for form binding reduces the chances
   * of a parameter tampering attack and makes code clearer.
   */
  case class Data(p: Int, poly: String)

  /**
   * The form definition for the "create a widget" form.
   * It specifies the form fields and their types,
   * as well as how to convert from a Data to form data and vice versa.
   */
  val form = Form(
    mapping(
      "p" -> number,
      "poly" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )
}
