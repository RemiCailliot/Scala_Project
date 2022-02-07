scalaVersion := "2.13.0"
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked"
)
// https://mvnrepository.com/artifact/org.reactivemongo/reactivemongo
libraryDependencies += "org.reactivemongo" %% "reactivemongo" % "1.0.10"

// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.35"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.17.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.17.1"