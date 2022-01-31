import scala.io.Source
object MainApp extends App{
  val driver1 = new reactivemongo.api.AsyncDriver
  reader.parser("src/main/scala/file.csv",reader.Point.csvtoP)
  // you can also use the Java Scanner class, if desired
  val scanner = new java.util.Scanner(System.in)
  print("Query or Reports?")

  val input = scanner.nextLine()
  if (input=="Query" || input=="Reports"){
    print(s"You choose $input")
  } else { print(s"You choose poorly")}

  
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
  val l=List((2, "ook!"), (1, "lolcode"), (3, "intercal"), (5, "ook ook"), (1, "brainfuck"), (2, "ArnoldC"))
  val bibli=List(
    List("ook!","ook ook"),
    List("lolcode"),
    List("intercal"),
    List("brainfuck"),
    List("ArnoldC"),
    )
  def count_vote(list:List[(Int, String)],bibli:List[List[String]]): List[(Object,Int)] = {
    list.map(x=> if (bibli.exists(_.contains(x._2))) ( x._1 , bibli.filter(_.contains(x._2))(0)(0) ) else (x._1,None))
    .groupBy(_._2).map { 
      case (key,value) => key -> (value.map(_._1)).sum}.toList
    }
  println(count_vote(l,bibli))
}