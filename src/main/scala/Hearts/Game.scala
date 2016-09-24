package Hearts

object Game {
  val deck = new Deck

  def apply() = {

    val ldr = 
      deck.deal.zipWithIndex.filter(_._1 contains Card("C2")).head._2

    new Game(deck.deal, List.fill(4)(List[Card]()), ldr, false, 0)
  }
}

class Game (val hands: List[List[Card]], 
            val played: List[List[Card]],
            val leader: Int,
            val brokenHearts: Boolean,
            val trickNum: Int) {
  type LLC = List[List[Card]]
  import scala.util.Random.shuffle

  val leadSuit = played(leader).head.suit
  def endOfGame = trickNum == 13
  def shortSuited(player: Int, suit: Char) = 
    hands(player).count(_.suit==suit) == 0

  def randCard(player: Int) = {
    if (player == leader) {
      if (brokenHearts || shortSuited(player,'H')) {
        // play a random card
        hands(player).head
      } else {
        // play a random non-heart card
        hands(player).filterNot(_.suit=='H').head
      }
    } else { // not leader
      if (shortSuited(player,leadSuit)) {
        // if short-suited, play any card
        hands(player).head
      } else {
        // else play in suit
        hands(player).filter(_.suit==leadSuit).head
      }
    }
  }

  def randGame: Game = if (endOfGame) this else randTrick


  // need to redo this sequentially!
  def randTrick: Game = {
    val idx = leader match {
      case 0 => List(0,1,2,3)
      case 1 => List(1,2,3,0)
      case 2 => List(2,3,1,0)
      case 3 => List(3,0,1,2)
    }

    val cardsInCurrTrick = idx map { i => randCard(i) }

    val newHands = idx map {i =>
      hands(i) filterNot { _ == cardsInCurrTrick(i) }
    }

    val newPlayed = 
      cardsInCurrTrick zip played map { z => z._1 :: z._2 }

    val newLeader = cardsInCurrTrick.zip(idx).
                      filter(_._1.suit==leadSuit).maxBy(_._1.value)._2
    val newBrokenHearts = 
      brokenHearts || cardsInCurrTrick.count(_.suit=='H') > 0
    val newTrickNum = trickNum + 1

    new Game(newHands,newPlayed,newLeader,newBrokenHearts,newTrickNum)
  }
}
