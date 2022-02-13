package model

import reactivemongo.api.bson.Macros
import reactivemongo.api.bson.collection.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Airport {
  case class Airport(name:String,latitude_deg:String,longitude_deg:String,elevation_ft:String,continent:String,iso_country:String,iso_region:String)
  //TODO a try for longitude et latitude
  implicit val userFormat2 = Macros.handler[Airport]

  def csvToAirport(list: Array[String]): Option[Airport] =
    Some(Airport(list(3),list(4), list(5), list(6),list(7),list(8),list(9)))

  def airportToMongo(c: Airport, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}