package service

import model.{Airport, Country, Runway}
import request.Queries.query1
import request.Reports.{report1, report2, report3}

object Prompt {
  def getChoice: Unit = {
    val scanner = new java.util.Scanner(System.in)
    System.out.println("\n\nQueries or Reports?")
    val line = scanner.nextLine()
    line.toLowerCase() match {
      case "reports" | "r" => println("You choose Reports"); chooseReports
      case "queries" | "q" => println("You choose Queries"); answerQueries
      case _  => println("Incorrect request,please choose either Queries or Reports"); getChoice
    }
  }
  def chooseReports : Unit = {
    val scanner = new java.util.Scanner(System.in)
    println("""We have 3 different reports  (1, 2, 3)
              1) 10 countries with highest number of airports and countries  with lowest number of airports.
              2) Type of runways per country
              3) The top 10 most common runway latitude """.stripMargin )
    val line = scanner.nextLine()
    line match {
      case "1"  => println(report1(CSV.read("airports.csv",Airport.csvToAirport).lines,CSV.read("countries.csv",Country.csvToCountry).lines))
      case "2"  => println(report2(CSV.read("airports.csv",Airport.csvToAirport).lines,CSV.read("countries.csv",Country.csvToCountry).lines,CSV.read("runways.csv",Runway.csvToRunway).lines))
      case "3"  => println(report3(CSV.read("runways.csv",Runway.csvToRunway).lines))
      case _ => println("Please reconsider your choice");chooseReports

  }}


  def answerQueries: Unit = {
    val scanner = new java.util.Scanner(System.in)
    System.out.println("\nWhich country do you choose? Choose either country code or country name (FR or France)")
    val line = scanner.nextLine().toLowerCase
    print(query1(line,CSV.read("airports.csv",Airport.csvToAirport).lines,CSV.read("countries.csv",Country.csvToCountry).lines,CSV.read("runways.csv",Runway.csvToRunway).lines))
  }
}
