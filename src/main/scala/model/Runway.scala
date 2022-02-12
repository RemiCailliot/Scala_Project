package model
object Runway {

  case class Runway(surface:String)
  object Runway {
    def csvToRunway(str:Array[String]) : Option[Any] = Option(str)
      .map{_.map(_.trim)
        .map(x => x)
      }
  }
}
