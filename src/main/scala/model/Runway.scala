package model

import reactivemongo.api.bson.Macros
import reactivemongo.api.bson.collection.BSONCollection

import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Runway {
  case class Runway(airport_ident:String,surface: String,le_ident:String)

  implicit val userFormat3 = Macros.handler[Runway]

  def csvToRunway(list: Array[String]): Option[Runway] =
    (Try(list(2).toString).toOption, Try(list(5).toString).toOption,Try(list(8).toString).toOption) match{
      case (Some(x), Some(y),Some(z)) => Some(Runway(x,y,z))
      case (Some(x), Some(y),None) => None
      case (Some(x), None,Some(z)) => None
      case (None, Some(y),Some(z)) => None
      case (Some(x), None,None) => None
      case (None, Some(y),None) => None
      case (None, None,Some(z)) => None
      case (None, None,None) => None
    }

  def airportToMongo(c: Runway, coll: Future[BSONCollection]): Unit = {
    val result = coll.flatMap(x => x.insert.one(c))
    result.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) =>
        println(s"successfully inserted document with result: $writeResult")
    }
  }
}
