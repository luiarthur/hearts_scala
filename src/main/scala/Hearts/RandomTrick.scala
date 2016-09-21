package Hearts

case class RandomTrick(brokenHearts: Boolean = false, seed: Int = 0) {
  val Rand = scala.util.Random
  Rand.setSeed(seed)

  type Hand = Map[Char,List[Card]]
  type Hands = List[Map[Char,List[Card]]]

  val deck = new Deck

  def randomSuit(hand: Hand) = {
    val availableSuits = {
      val suits = hand.keys.toList
      if (brokenHearts || suits.size==1) suits else suits.filterNot(_=='H')
    }

    Rand.shuffle(availableSuits).head
  }

  def hasSuit(hand: Hand, suit: Char) = 
    hand isDefinedAt suit

  def randomTrick(hands: Hands, leader: Int) = {
    val suitInPlay = randomSuit(hands(leader))
    val cards = hands map { hand =>
      if (hasSuit(hand,suitInPlay)) {
        hand(suitInPlay).head
      } else {
        hand(randomSuit(hand)).head
      }
    }
    cards
  }

}

/*  in file testing:
    val deck = new Deck
    val hands = deck.deal
 */
