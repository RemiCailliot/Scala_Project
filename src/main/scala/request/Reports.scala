package request

import model.Airport._
import model.Country._

object Reports {
  //1°) count number of airport by iso country (10 max)
  def report1(airports: Iterator[Airport], countries: Iterator[Country]) = {
    val codename = countries.toSeq.map { y => (y.code, y.name) }.toMap
    airports.toList
      .map { x => (x.iso_country, 1) }
      .foldLeft(Map[String, Double]()) { case (m, (k, v)) => m + (k -> (v + m.getOrElse(k, 0d)))
      }.toSeq
      .sortWith(_._2 > _._2)
      .take(10).map { x => (codename(x._1), x._2)
    }

  }
  //1°) count number of airport by iso country (10 max)
  def report2(airports: Iterator[Airport], countries: Iterator[Country]) = {
    val codename = countries.toSeq.map { y => (y.code, y.name) }.toMap
    airports.toList
      .map { x => (x.iso_country, 1) }
      .foldLeft(Map[String, Double]()) { case (m, (k, v)) => m + (k -> (v + m.getOrElse(k, 0d)))
      }.toSeq
      .sortWith(_._2 > _._2)
      .take(10).map { x => (codename(x._1), x._2)
    }

  }
}
