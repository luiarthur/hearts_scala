import org.scalatest.FunSuite

class TestSuite extends FunSuite {
  def round(x: Double, d: Int = 2) = 
    (scala.math.pow(10,d) * x).toInt / scala.math.pow(10,d)

  test("Just a test") {
    import ORF.models._
    assert(true)
  }

  test("General") {
    import Hearts._
    val deck = new Deck
    val hands = deck.deal
    println(hands)
  }

}
