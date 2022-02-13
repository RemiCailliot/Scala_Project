package model

import reactivemongo.api.bson.Macros
import reactivemongo.api.bson.collection.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Country {
  case class Country(code: String, name: String, continent: String)


  implicit val userFormat = Macros.handler[Country]

  def csvToCountry(list: Array[String]): Option[Country] =
    (Try(list(1).toString).toOption, Try(list(2).toString).toOption,Try(list(3).toString).toOption) match {
      case (Some(x), Some(y), Some(z)) => Some(Country(x, y, z))
      case (Some(x), None, Some(z)) => None
      case (None, Some(y), Some(z)) => None
      case (Some(x), Some(y), None) => None
      case (Some(x), None, None) => None
      case (None, Some(y), None) => None
      case (None, None, Some(z)) => None
      case (None, None, None) => None
    }

  def countryToMongo(c: Country, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}
