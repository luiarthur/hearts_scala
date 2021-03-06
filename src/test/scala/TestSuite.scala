import org.scalatest.FunSuite

class TestSuite extends FunSuite {

  import ORF.models.timer
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
    println
    msg("Random Game")
    msg("starting hand:")
    val newGame = Game()
    //newGame.hands.foreach{i => println(i.sortBy(_.card))}
    newGame.hands.foreach{println}
    val g = newGame.randGame()
    assert(g.hands == List(List(),List(),List(),List()))
    assert(g.hands.map(_.size) == List(0,0,0,0))
    assert(g.trickNum == 13)
    assert(g.brokenHearts)
    msg("leaders: " + g.leaders)
    g.playBack
  }
  
  test("Train") {
    //implicit class Hands(llc: List[List[Card]]) {
    //  def trick(i: Int) = llc map {l => l(i)}
    //}
    println
    val randGame = Game().randGame()
    val leaders = randGame.leaders.tail
    val played = randGame.played
    randGame.playBack
    assert({leaders.size :: played.map(_.size)}.toSet.size==1)
    val points = randGame.points
    val takers = randGame.takers
    println("Points: " + points)
    println("Takers: " + takers)

    val y = points
    val X = randGame.genX

    val game = Game()
    import ORF.models._
    val rng52 = Vector.fill(52)(Vector(-4.0,4.0))
    val xrng = dataRange(rng52 ++ Vector(Vector(0.0,13.0),Vector(0.0,3.0)) )
    val param = Param(minSamples=100,minGain=0,maxDepth=15,xrng=xrng)
    val orf = RegForest(param,numTrees=15,par=true)
    val n = 1000
    for (i <- 1 to n) {
      print("\rIteration: " + i + "/" + n)
      val randGame = game.randGame()
      val y = randGame.points
      val X = randGame.genX
      assert(y.size == X.size)
      for (j <- 0 until y.size) {
        orf.update(X(j),y(j))
      }
    }
    println
    println("Mean num Leaves: " + orf.meanNumLeaves)
    // ORF STUFF?
    val testGame = {Game()}.randGame()
    println("Prediction: " + orf.predicts(testGame.genX.toVector))
    println(testGame.points)
    println(testGame.takers)
  }
}
