package Hearts

object GameOld {
  val deck = new Deck

  def apply() = {

    val ldr = 
      deck.deal.zipWithIndex.filter(_._1 contains Card("C2")).head._2

    new GameOld(deck.deal, List.fill(4)(List[Card]()), ldr, false, 0)
  }
}

class GameOld (val hands: List[List[Card]], 
            val played: List[List[Card]],
            val leader: Int,
            val brokenHearts: Boolean,
            val trickNum: Int) {
  type LLC = List[List[Card]]

  val leadSuit = if (trickNum==0) 'C' else hands(leader).head.suit
  def endOfGame = trickNum == 13
  def shortSuited(player: Int, suit: Char) = 
    hands(player).count(_.suit==suit) == 0

  def randCard(player: Int) = {
    if (player == leader) {
      if (trickNum==0) {
        println("leader: "+leader)
        // play 2 of Clubs
        Card("C2")
      } else if (brokenHearts || shortSuited(player,'H')) {
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

  def randGame: GameOld = if (endOfGame) this else randTrick


  // need to redo this sequentially!
  def randTrick: GameOld = {

    val idx = List(0,1,2,3)

    val cardsInCurrTrick = idx map randCard
    println(cardsInCurrTrick)

    //val 
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

    new GameOld(newHands,newPlayed,newLeader,newBrokenHearts,newTrickNum)
  }
}
