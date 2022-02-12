package model
object Country {

  case class Country(code:String,name:String,continent:String)
  object Country {
    def csvToCountry(str:Array[String]) : Option[Any] = Option(str)
      .map{_.map(_.trim)
        .map(x => x)
      }
  }
}
