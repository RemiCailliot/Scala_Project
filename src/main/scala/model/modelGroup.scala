package model


object Model {

    case class Airport(iso_country:String)
    object Airport {
        def csvToAirport(str:Array[String]) : Option[Any] = Option(str)
          .map{_.map(_.trim)
            .map(x => x)
          }
    }
    case class Country(code:String,name:String,continent:String)
    object Country {
        def csvToCountry(str:Array[String]) : Option[Any] = Option(str)
          .map{_.map(_.trim)
            .map(x => x)
          }
    }

    case class Runway(surface:String)
    object Runway {
        def csvToRunway(str:Array[String]) : Option[Any] = Option(str)
          .map{_.map(_.trim)
            .map(x => x)
          }
    }
    /*ident:airport==airports_ident:runways*/

}