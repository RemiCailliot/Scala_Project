import model.{Airport, Country, Runway}
import org.scalatest.funsuite.AnyFunSuite
import request.Reports.{report1, report2, report3}
import service.CSV

class TestApp extends AnyFunSuite {
  test("report1 assert") {
    val getr = report1(CSV.read("airports.csv", Airport.csvToAirport).lines,
      CSV.read("countries.csv", Country.csvToCountry).lines).head.head._2
    assert(getr == 592)
  }
  test("report2 assert") {
    val getr2 = report2(CSV.read("airports.csv", Airport.csvToAirport).lines,
      CSV.read("countries.csv", Country.csvToCountry).lines,
      CSV.read("runways.csv", Runway.csvToRunway).lines).head.head._2.map(x=> x.replaceAll("\"", ""))
    println(getr2)
    assert(getr2 == List("asp", "mac", "unk", "asphalt", "u"))
  }
  test("report3 assert") {
    val getr3 = report3(CSV.read("runways.csv", Runway.csvToRunway).lines).head.head._2
    assert(getr3 == List(1))
  }
}