import MainApp._
import request.Reports
import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite
import model.{Airport, Country, Runway}
import request.Queries.query1
import request.Reports.{report1, report2, report3}
import service.CSV

class TestApp extends AnyFunSuite {
  test("giveMeHelloString function") {
    assert(report1(CSV.read("airports.csv",Airport.csvToAirport).lines,CSV.read("countries.csv",Country.csvToCountry).lines)("United States") == )
  }

  test("Reports1 function") {
    assert(Reports.highest_number_of_airports() == 8)
  }
}
