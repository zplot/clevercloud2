package controllers

object Form2 {

  import play.api.data.Forms._
  import play.api.data.Form

  case class Data2(p: Int, poly: String)

  val form = Form(
    mapping(
      "p" -> number,
      "poly" -> nonEmptyText
    )(Data2.apply)(Data2.unapply)
  )
}
