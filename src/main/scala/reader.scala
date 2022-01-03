import scala.io.Source
object reader {
    def parser[P](str: String, csvtoP : Array[String] => Option[P]): Iterator[P] = {
        Source.fromFile("src/main/scala/file.csv")
        .getLines()
        .map(_.split(","))
        .flatMap(Point.csvtoP)
        .foreach(println)
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