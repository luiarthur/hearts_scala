package Hearts

case class Hand(hand: Map[Char,List[Card]]) {
  def shortSuit(suit: Char) = hand isDefinedAt suit
}
