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
    println(deck.deal)
    println(deck.deal)
  }

  test("Taker") {
    import Hearts._
    val cards = List(Card("S12"),Card("SA"),Card("H13"),Card("D2"))
    assert(Trick(cards,leader=0).taker == 1)
    assert(Trick(cards,leader=1).taker == 1)
    assert(Trick(cards,leader=2).taker == 2)
    assert(Trick(cards,leader=3).taker == 3)
  }

}
