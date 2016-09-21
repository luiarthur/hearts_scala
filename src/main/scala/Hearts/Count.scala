package Hearts

class Count(myCards: List[Card], playedCards: List[Card]) {

  private val staticDeck = {new Deck}.cards
  private val nCardsPlayed = playedCards.size + myCards.size

  private def prob(nPlayed: Int, nMine: Int, nTotal: Int) = 
    (nTotal-nPlayed-nMine) / (52-nCardsPlayed).toDouble

  def probSuit(suit: Char) = {
    val nPlayed = playedCards.count(_.suit == suit)
    val nMine = myCards.count(_.suit == suit)
    val nTotal = 13
    
    prob(nPlayed, nMine, nTotal)
  }

  def probValue(value: String) = {
    val nPlayed = playedCards.count(_.value == value)
    val nMine = myCards.count(_.value == value)
    val nTotal = 4
    
    prob(nPlayed, nMine, nTotal)
  }

  def probCard(card: Card) = 
    1.0 / (52-nCardsPlayed-myCards.size)

  def probGreaterThan(value: String, addMine: Boolean=false) = {
    val nPlayed = playedCards.count(_.value > value)
    val nMine = myCards.count(_.value > value)
    val nTotal = staticDeck.count(_.value > value)

    prob(nPlayed, nMine, nTotal)
  }

}
