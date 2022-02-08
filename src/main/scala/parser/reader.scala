package parser

import scala.io.Source
object Reader {


    //def parsermongo[P](url:String,csvtocountrie: Array[String]=> Option[P]):

    



    def parser[P](str: String, csvtoP : Array[String] => Option[P]): /*Iterator[P]*/Any = {
        Source.fromFile(str)
        .getLines()
        .map(_.split(","))
        .flatMap(Point.csvtoP)
    }
    case class Point(x:Int, y: Int, z: Option[Int] = None)
    //case class = plusieurs méthodes prédéfini
    object Point {
        def csvtoP(str:Array[String]) : Option[Any] = Option(str)
        .map{_.map(_.trim)
        .map(x => util.Try(x.toInt).toOption) match {
            case Array(Some(x),Some(y)) => Some(Point(x.toInt, y.toInt))
            case Array(Some(x),Some(y),Some(z)) => Some(Point(x.toInt, y.toInt,Some(z.toInt)))
            case _ =>None
            }
        }
    }
}