object model {
    
    case class airportObj(iso_country:String,/*ident:String*/)
    case class CountryObj(country_code:String,country_name:String)
    case class runwaysObj(surface:String,/*airports_ident:String*/)
    /*ident:airport==airports_ident:runways*/

}