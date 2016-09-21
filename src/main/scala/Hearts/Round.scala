package Hearts

class Round {
  val deck = new Deck
  type LLCards = List[List[Card]]

  // STOPPED HERE:
  def playTrick(hands: LLCards, leader: Int = -1) = {
    ???
  }

  // return the played cards
  def simulate: (LLCards,LLCards) = {
    val hands = deck.deal

    def trick(played: LLCards, taken: LLCards): (LLCards,LLCards) = {
      def endOfGame = taken.map(_.size).sum == 52

      if (endOfGame) (played, taken) else {
        ???
      }
    }

    trick(playTrick(deck.deal), List())
  }
}
