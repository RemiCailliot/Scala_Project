import scala.io.Source
import scala.util.{Success, Failure}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.{ Cursor, DB, MongoConnection, AsyncDriver }
import reactivemongo.api.MongoConnection.ParsedURI
import reactivemongo.api.bson.{ BSONDocument,BSONDocumentWriter, BSONDocumentReader, Macros,document}
import reactivemongo.api.bson.collection.BSONCollection
import Model._
import Reader._
import reactivemongo.api.commands.Command
object MainApp extends App{
  val mongoURL = "mongodb://localhost:27018"
  val dbName= "scala"
  val mongoDriver = AsyncDriver()
  val parsedURIFuture: Future[ParsedURI] = MongoConnection.fromString(mongoURL)
  val connection: Future[MongoConnection] = parsedURIFuture.flatMap(u => mongoDriver.connect(u))
  val db: Future[DB] = connection.flatMap(_.database(dbName))
  val countriesCollection: Future[BSONCollection] = db.map(_.collection("countries"))
  val airportsCollection: Future[BSONCollection] = db.map(_.collection("airports"))

  implicit def countryReader: BSONDocumentReader[Country] = Macros.reader[Country]

  val hnabc = highest_number_of_airports(airportsCollection)

  countries.onComplete({
    case Success(listCountry) => {
      //Do something with my list
      println(listCountry)
    }
    case Failure(e) => {
      //Do something with my error
      print("error")
    }
  })

  hnabc.onComplete({
    case Success(listCountry) => {
      //Do something with my list
      println(listCountry)
    }
    case Failure(e) => {
      //Do something with my error
      print("error")
    }
  })













  // reader.parser("src/main/scala/file.csv",reader.Point.csvtoP)
  // // you can also use the Java Scanner class, if desired
  // val scanner = new java.util.Scanner(System.in)
  // print("Queries or Reports?")
  // val input = scanner.nextLine()
  // if (input=="Queries" || input=="Reports"){
  //   print(s"You choose $input\n")
  // } else { print(s"You choose poorly")}
  // scanner.close();
  
  //Pour le projet tout doit être sur github, chacun partage
  //le parsing prend en param une fonction avec des points en 2D ou 3D
  //lire au moins le fichier, trouver le fichier, le charger
  //collection [string] => Option[P] ou Try[P] (mauvais pour la perf) ou Either[Int/String, P] (signature deserialization) 
  //Either code d'erreur
  //def t[P](file:String, f : function de type P) : collection[P]  fonction d'ordre supérieur
  //Point (x :Int, y:Int, z:Option[Int])
  //deserializer dans le companion object d'une case class
  //must to do : deserialize
  //reactive mongo +mongodb
  //docker image
  //faire unmodel pour chacun des type métier
  // val l=List((2, "ook!"), (1, "lolcode"), (3, "intercal"), (5, "ook ook"), (1, "brainfuck"), (2, "ArnoldC"))
  // val bibli=List(List("ook!","ook ook"),List("lolcode"),List("intercal"),List("brainfuck"),List("ArnoldC"))

  // def count_vote(list:List[(Int, String)],bibli:List[List[String]]): List[(Object,Int)] = {
  //   list.map(x=> if (bibli.exists(_.contains(x._2))) ( x._1 , bibli.filter(_.contains(x._2))(0)(0) ) else (x._1,None))
  //   .groupBy(_._2).map { 
  //     case (key,value) => key -> (value.map(_._1)).sum}.toList
  //   }
  //println(count_vote(l,bibli))
}