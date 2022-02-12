package model

import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, Macros}
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Country {
  case class Country(code: String, name: String, continent: String)

  implicit val userFormat = Macros.handler[Country]

  def csvToCountry(list: Array[String]): Option[Country] =
    Option(Country(list(1), list(2), list(3)))

  def countryToMongo(c: Country, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}
