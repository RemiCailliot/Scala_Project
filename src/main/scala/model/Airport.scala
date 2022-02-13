package model

import reactivemongo.api.bson.Macros
import reactivemongo.api.bson.collection.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Airport {
  case class Airport(ident: String, name: String, latitude_deg: String, longitude_deg: String, elevation_ft: String, continent: String, iso_country: String, iso_region: String)

  //TODO a try toLong for longitude et latitude
  implicit val userFormat2 = Macros.handler[Airport]

  def csvToAirport(list: Array[String]): Option[Airport] =
    (Try(list(1).toString).toOption, Try(list(8).toString).toOption) match {
      case (Some(x), Some(y)) =>  Some(Airport(x.toLowerCase, list(3).toLowerCase, list(4).toLowerCase, list(5).toLowerCase,
                                  list(6).toLowerCase, list(7).toLowerCase, y.toLowerCase, list(9).toLowerCase))
      case (Some(x), None) => None
      case (None, Some(y)) => None
      case (None, None) => None
    }

  def airportToMongo(c: Airport, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}