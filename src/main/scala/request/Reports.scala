package request

import model.Airport._
import model.Country._
import model.Runway.Runway
object Reports {
  //1°) count number of airport by iso country (10 max)
  def report1(airports: Iterator[Airport], countries: Iterator[Country]) = {
    val countryCodeName = countries.toSeq.map { y => (y.code, y.name) }.toMap
    Some(airports.toSeq
      .map { x => (x.iso_country, 1) }
      .foldLeft(Map[String, Double]()) { case (m, (k, v)) => m + (k -> (v + m.getOrElse(k, 0d)))
      }.toSeq
      .sortWith(_._2 > _._2)
      .take(10).map { x => (countryCodeName(x._1), x._2)
    }.toMap)

  }
  //2°) count number of airport by iso country (10 max)
  def report2(airports: Iterator[Airport], countries: Iterator[Country],runway: Iterator[Runway]) = {
    val countryCodeName = countries.toSeq.map { y => (y.code, y.name) }.toMap
    val airportIDCountry = airports.toSeq.map { y => (y.ident, y.iso_country) }.toMap
    Some(runway.toSeq
      .map{x=> (x.airport_ident,x.surface)}
      .map{x=> (airportIDCountry(x._1),x._2)}
      .groupMap(_._1)(_._2)
      .map(x=> (x._1,x._2.toList.distinct)).toSeq.sortBy(_._1))
      //.map { x => if (x._2.isEmpty) (countryCodeName(x._1), None) else (countryCodeName(x._1), x._2)})
  }
  def report3(runway: Iterator[Runway]) = {
    val runwayLatitude = runway.toSeq.map { y => (y.le_ident,1) }.toMap
    Some(runwayLatitude.groupMap(_._1)(_._2).map(x=>(x._1,x._2.toList)))
  }
}
