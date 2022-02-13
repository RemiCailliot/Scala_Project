package request

import model.Airport.Airport
import model.Country.Country
import model.Runway.Runway

object Queries{
  def query1(codename: String,airports: Iterator[Airport], countries: Iterator[Country],runway: Iterator[Runway]) = {
    println(codename)
    val country = countries.toList.filter(x=>(x.name==codename || x.code==codename))
    val code,name = country.size match {
      case 1 => (country.head.code, country.head.name)
      case _ => (None, None)
    }
    val airports2 = airports.toList.filter(x=>(x.iso_country==code))
    val ident = airports2.map(_.ident)
    val runway2= runway.toList.filter(x=> ident.contains(x.airport_ident))
    Some(Seq(name,airports2,runway2))




  }
}