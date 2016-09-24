package Hearts

object Game {
  type LLC = List[List[Card]]
  val deck = new Deck

  def apply(hands: LLC, played: LLC, leader: Int, 
            brokenHearts: Boolean, trickNum: Int) = {
    val ldr = hands.zipWithIndex.filter(_._1 contains Card("C2")).head._2
    new Game(deck.deal, List.fill(4)(List()), ldr, false, 0)
  }
}

class Game (val hands: List[List[Card]], 
            val played: List[List[Card]],
            val leader: Int,
            val brokenHearts: Boolean,
            val trickNum: Int) {
  type LLC = List[List[Card]]

  def enfOfGame = trickNum == 13
  def randGame = ???
  def shortSuited(player: Int) = ???

  def randCard(player: Int) = {
    if (player == leader) {
      if (brokenHearts) {
        // play a random card
        ???
      } else {
        // play a random non-heart card
        ???
      }
    } else {
      // if short-suited, play any card
      // else play in suit
      ???
    }
  }

  def randTrick: Game = {
    val idx = leader match {
      case 0 => List(0,1,2,3)
      case 1 => List(1,2,3,0)
      case 2 => List(2,3,1,0)
      case 3 => List(3,0,1,2)
    }

    ???
  }
}
