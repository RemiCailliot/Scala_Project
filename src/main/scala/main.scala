import model.Country._
import reactivemongo.api.MongoConnection
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, Macros}
import service.CSV

import scala.concurrent.{ExecutionContext, Future}


object MainApp extends App{
  import ExecutionContext.Implicits.global
  val mongoURL = "mongodb://localhost:27018"
  val dbName= "scala"
  val driver1 = new reactivemongo.api.AsyncDriver
  val parsedUri = MongoConnection.fromString(mongoURL)
  // Database and collections: Get references
  val connection3 = parsedUri.flatMap(driver1.connect(_))

  implicit val CountryReader: BSONDocumentReader[Country] = Macros.reader[Country]

  println("hello")
  def dbFromConnection(connection: Future[MongoConnection],dbName:String,collectionName:String): Future[BSONCollection] =
    connection.flatMap(_.database("scala")).
      map(_.collection("countries"))

  val countriesDb = dbFromConnection(connection3,dbName,"countries")
  val v = countriesDb.flatMap(_.find(BSONDocument()).cursor[Country]().collect[List]())
  //v.foreach(println)
  val x = CSV.read("countries.csv",Country.csvToCountry)
  println(x.nbInvalidLine)
  x.lines.foreach(println)















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