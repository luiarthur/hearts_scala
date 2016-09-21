import org.scalatest.FunSuite

class TestSuite extends FunSuite {

  def round(x: Double, d: Int = 3) = 
    (scala.math.pow(10,d) * x).toInt / scala.math.pow(10,d)

  def msg(s: String) = println(Console.CYAN + s + Console.RESET)

  test("Just a test") {
    import ORF.models._
    assert(true)
  }

  import Hearts._
  test("Deck Printint") {
    val deck = new Deck
    println(deck.deal)
    println(deck.deal)
  }

  test("point cards") {
    assert(Card("SQ").isPointCard)
    assert(Card("HQ").isPointCard)
    assert(!Card("CQ").isPointCard)
    assert(!Card("D10").isPointCard)
    assert(Card("D10").points==0)
    assert(Card("H10").points==1)
  }

  test("Count") {
    val played = List("H2","D3","H3","H4","H5","H6")
    val mine = List("SQ","H7","H8","H9","H9","H10")

    val playedCards = played map{c => Card(c)}
    val myCards = mine map{c => Card(c)}

    val count = new Count(myCards,playedCards)

    msg( "Prob of S: " + round(count.probSuit('S')) )
    msg( "Prob of H: " + round(count.probSuit('H')) )
    msg( "Prob of C: " + round(count.probSuit('C')) )
    msg( "Prob of D: " + round(count.probSuit('D')) )
  }

  test("Taker") {
    val cards = List(Card("S12"),Card("SA"),Card("H13"),Card("D2"))
    //assert(Trick(cards,leader=0).taker == 1)
    //assert(Trick(cards,leader=1).taker == 1)
    //assert(Trick(cards,leader=2).taker == 2)
    //assert(Trick(cards,leader=3).taker == 3)
  }

  test("Random Game") {
    //val randGame = new RandomGame
  }


}
