package model

import reactivemongo.api.bson.Macros
import reactivemongo.api.bson.collection.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Runway {
  case class Runway(surface: String,le_ident:String)

  implicit val userFormat3 = Macros.handler[Runway]

  def csvToRunway(list: Array[String]): Option[Runway] =
    Some(Runway(list(5),list(8)))

  def airportToMongo(c: Runway, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}
