package Hearts


import ORF.models._
object Game {
  val deck = new Deck

  def apply(orf: RegTree = null) = {
    val hands = deck.deal
    //                                  hand                           index
    val ldr = hands.zipWithIndex.filter(_._1 contains Card("C2")).head._2
    val ldrs = List(ldr)
    new Game(hands, List.fill(4)(List[Card]()), ldrs, false, 0, orf)
  }
}

class Game(val hands: List[List[Card]], 
           val played: List[List[Card]],
           val leaders: List[Int],
           val brokenHearts: Boolean,
           val trickNum: Int,
           val orf: RegTree) {
  val deck = new Deck

  val idx = List(0,1,2,3)
  val leader = leaders.head
  private var _brokenHearts = brokenHearts
  private var _leadSuit = '_'

  def endOfGame = trickNum == 13
  def shortSuited(player: Int, suit: Char) = 
    hands(player).count(_.suit==suit) == 0
  def allSuit(player: Int, suit: Char) =
    hands(player).count(_.suit==suit) == hands(player).size


  def randCard(player: Int) = {
    if (player == leader) { // player is leader
      val leadCard = if (trickNum==0) {
        // play 2 of Clubs
        Card("C2")
      } else if (_brokenHearts) { // hearts are broken
        // play a random card
        hands(player).head
      } else { // hearts are not broken
        if (allSuit(player,'H')) { // all cards are hearts
          // play a random heart
          _brokenHearts = true
          hands(player).head
        } else { // not all cards are hearts
          // play a random non-heart card
          hands(player).filterNot(_.suit=='H').head
        }
      }
      _leadSuit = leadCard.suit
      leadCard
    } else { // not leader
      if (shortSuited(player,_leadSuit)) {
        // if short-suited, play any card
        val out = hands(player).head
        if (!_brokenHearts && out.suit=='H') _brokenHearts = true
        out
      } else {
        // else play in suit
        hands(player).filter(_.suit==_leadSuit).head
      }
    }
  }

  def randGame(game: Game = this): Game = 
    if (game.endOfGame) game else randGame(game.randTrick)

  def randTrick: Game = {
    val ord = leader match {
      case 0 => List(0,1,2,3)
      case 1 => List(1,2,3,0)
      case 2 => List(2,3,0,1)
      case 3 => List(3,0,1,2)
    }

    val cardsInCurrTrick = 
      {for (i <- ord) yield randCard(i)}.zip(ord).sortBy(_._2).map(_._1)
     
    val newHands = 
      hands.zip(cardsInCurrTrick).map( z => z._1 filterNot { _ == z._2} )

    val newPlayed = 
      cardsInCurrTrick zip played map { z => z._1 :: z._2 }

    val newLeader = cardsInCurrTrick.zipWithIndex.
                      filter(_._1.suit==_leadSuit).maxBy(_._1.magnitude)._2

    val newTrickNum = trickNum + 1

    new Game(newHands,newPlayed,newLeader::leaders,_brokenHearts,newTrickNum, orf)
  }

  def playBack = {
    print(Console.CYAN+ "Playback (")
    print(Console.YELLOW + "Leaders, " + Console.RED + "Takers")
    println(Console.CYAN + ")" + Console.RESET)
    for (i <- (0 to 12).toList.reverse) {
      for (j <- 0 to 3) {
        val col = if (leaders(i) == j) {
          Console.RED 
        } else if (leaders(i+1) == j) {
          Console.YELLOW
        } else Console.RESET
        print(col + j.toString + ": " + played(j)(i) + "\t" + Console.RESET)
      }
      println
    }
  }

  private def trick(i: Int) = played map { l => l(12-i) }
  private def pointInTrick(i: Int) = 
    trick(i).count(_.suit == 'H') + trick(i).count(_.card == "SQ") * 13

  /* List takers in order */
  def takers = {
    assert(endOfGame,"This is only done at end of game")
    leaders.reverse.tail
  }


  /* List points taken in each trick in order */
  def points = {
    assert(endOfGame,"This is only done at end of game")
    val out = for (i <- List.range(0,13)) yield pointInTrick(i) 
    assert(out.sum == 26, "Points don't sum to 26!!!")
    out
  }

  // generate x vector for trick t // FIXME. is this right?
  def genx(t: Int) = {
    val x = Array.fill(54)(0.0)
    x(52) = t // trick number
    x(53) = leaders(t) // leaders
    var i = 0
    for (c <- deck.cards) {
      x(i) = 
        if (played(0) contains c) {
          -1 
        } else if (played(1).drop(12-t) contains c) {
          -2
        } else if (played(2).drop(12-t) contains c) {
          -3
        } else if (played(3).drop(12-t) contains c) {
          -4
        } else if (played(0).take(t+1) contains c) {
          1
        } else if (played(1).take(t+1).head == c) {
          2
        } else if (played(2).take(t+1).head == c) {
          3
        } else if (played(3).take(t+1).head == c) {
          4
        } else 0
      i += 1
    }
    x.toVector
  }

  def genX = {
    assert(endOfGame,"This is only done at end of game")
    for (t <- 0 until 13) yield genx(t)
  }

  def smartCard = { // play a smart card if player 0
    ???
  }
}
