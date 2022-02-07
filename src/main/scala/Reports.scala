import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import reactivemongo.api.bson.{ BSONDocument, BSONString }

import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.commands.AggregationFramework
import Model._

object Reports{
    def highest_number_of_airports(col:Future[BSONCollection],n:Int =10): Future[List[Airport]] = {
        col.aggregatorContext[BSONDocument](
        pipeline = List(
            Group(BSONString("$iso_country"))( "total" -> Sum(1)),
            )).prepared.cursor.collect[List]()
    }
}