package controllers

import play.api.libs.json.Json.{JsValueWrapper, stringify}
import play.api.libs.json._

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}



object Demo extends App {
  def vamos(args: Array[String]): Unit = {
    val writer = new PrintWriter(new File("test.txt" ))
    writer.write("1, 2, 3, 4, 5")
    writer.close()
  }
  vamos(Array())

  case class Point(x: Double, y: Double)

  object Point {
    implicit val format = Json.format[Point]
  }


  implicit val pointWrites = new Writes[Point] {
    def writes(point: Point) = Json.obj(
      "lat"  -> point.x,
      "long" -> point.y
    )

    val pointList: List[Point] = List(Point(2,3), Point(6,9))
    val a: JsValue = Json.toJson(pointList)
    println(pointList)
    println(a)

    val pointSet: Set[Point] = Set(Point(2,3), Point(6,9))
    val b: JsValue = Json.toJson(pointSet)
    println(pointSet)
    println(b)
    val c: String = stringify(a)
    val d: String = stringify(b)
    println(c)
    println(Json.prettyPrint(b))

  }






}



