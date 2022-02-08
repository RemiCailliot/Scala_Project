package model


object Model {
    case class Airport(iso_country:String)
    case class Country(code:String,name:String,continent:String)
    case class Runway(surface:String)
    /*ident:airport==airports_ident:runways*/

}