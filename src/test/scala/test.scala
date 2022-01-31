import MainApp._
import Querys._
import Reports._
class TestApp extends org.scalatest.FunSuite {
  test("giveMeHelloString function"){
    assert(giveMeHelloString == "Hello")
  }

  test("Reports1 function") {
    assert(Reports.highest_number_of_airports() == 8)
  }
