package model

import reactivemongo.api.bson.Macros
import reactivemongo.api.bson.collection.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Country {
  case class Country(code: String, name: String, continent: String)

  implicit val userFormat = Macros.handler[Country]

  def csvToCountry(list: Array[String]): Option[Country] =
    Some(Country(list(1), list(2), list(3)))

  def countryToMongo(c: Country, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}
