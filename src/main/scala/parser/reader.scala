package parser
import model.Model.Country

import scala.io.Source
object Reader {


    def parserCountry[P](path: String,csvToCountry: Array[String]=> Option[P]): Any = {
        Source.fromFile(path)
          .getLines()
          .map(_.split(","))
          .flatMap(Country.csvToCountry)
    }





    def parser[P](str: String, csvToP : Array[String] => Option[P]): /*Iterator[P]*/Any = {
        Source.fromFile(str)
        .getLines()
        .map(_.split(","))
        .flatMap(Point.csvToP)
    }
    case class Point(x:Int, y: Int, z: Option[Int] = None)
    //case class = plusieurs méthodes prédéfini
    object Point {
        def csvToP(str:Array[String]) : Option[Any] = Option(str)
        .map{_.map(_.trim)
        .map(x => util.Try(x.toInt).toOption) match {
            case Array(Some(x),Some(y)) => Some(Point(x.toInt, y.toInt))
            case Array(Some(x),Some(y),Some(z)) => Some(Point(x.toInt, y.toInt,Some(z.toInt)))
            case _ =>None
            }
        }
    }
}