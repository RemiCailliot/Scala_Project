scalaVersion := "2.1.0"
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked"
)
// https://mvnrepository.com/artifact/org.reactivemongo/reactivemongo
libraryDependencies += "org.reactivemongo" %% "reactivemongo" % "1.0.10" % "provided"
