import scala.io.Source
object main extends App{
  case class Point(x:Int, y:Int, z:Option[Int])
  //Source.fromFile(str).getLines.take(acc)
  
  def parse_csv(str:String): Option[Any] = Option(Source.fromFile(str).getLines)
  }

  //Pour le projet tout doit être sur github, chacun partage
  //le parsing prend en param une fonction avec des points en 2D ou 3D
  //lire au moins le fichier, trouver le fichier, le charger
  //collection [string] => Option[P] ou Try[P] (mauvais pour la perf) ou Either[Int/String, P] (signature deserialization) 
  //Either code d'erreur
  //def t[P](file:String, f : function de type P) : collection[P]  fonction d'ordre supérieur
  //Point (x :Int, y:Int, z:Option[Int])
  //deserializer dans le companion object d'une case class
  //must to do : deserialize
}