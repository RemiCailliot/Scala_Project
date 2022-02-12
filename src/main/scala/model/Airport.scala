package model
object Airport {
  case class Airport(iso_country:String)
  object Airport {
    def csvToAirport(str:Array[String]) : Option[Array[String]] = Option(str)
      .map{_.map(_.trim)
        .map(x => x)
      }
  }
}
