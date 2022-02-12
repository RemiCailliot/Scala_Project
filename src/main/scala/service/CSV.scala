package service
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.IteratorHasAsScala

final case class ReadResult[A](lines: Iterator[A], nbInvalidLine: Int)

object CSV {
    def read[A](fileName: String, parseLine: Array[String] => Option[A], regex: String = ",") = {
        val (parsedLine, invalidLine) = Option(Files.lines(Paths.get(s"src/main/data/$fileName")))
          .map(_.iterator().asScala)
          .getOrElse(Iterator.empty) // if file can't be read option will be a none.
          .drop(1) // drop csv header
          .map(_.split(regex)) //you may want to use a regexp here
          .map(_.map(_.trim))
          .map(parseLine)
          .partition(_.isDefined)
        ReadResult(parsedLine.flatten, invalidLine.size)
    }
}


//
//    def parser[P](str: String, csvToP : Array[String] => Option[P]): /*Iterator[P]*/Any = {
//        Source.fromFile(str)
//        .getLines()
//        .map(_.split(","))
//        .flatMap(Point.csvToP)
//    }
//    case class Point(x:Int, y: Int, z: Option[Int] = None)
//    //case class = plusieurs méthodes prédéfini
//    object Point {
//        def csvToP(str:Array[String]) : Option[Any] = Option(str)
//        .map{_.map(_.trim)
//        .map(x => util.Try(x.toInt).toOption) match {
//            case Array(Some(x),Some(y)) => Some(Point(x.toInt, y.toInt))
//            case Array(Some(x),Some(y),Some(z)) => Some(Point(x.toInt, y.toInt,Some(z.toInt)))
//            case _ =>None
//            }
//        }
//    }
//}