package Hearts

//class RandomGame {
//
//  type Hand = Map[Char,List[Card]]
//  type Hands = List[Map[Char,List[Card]]]
//
//  val deck = new Deck
//  val hands = deck.deal
//
//  val ll = hands.map{_.values.flatten.toList}.zipWithIndex
//  val playerWith2C = ll.filter(_._1 contains Card("C2")).head._2 
//
//  val randTrick = RandomTrick(hands,
//                              leader=playerWith2C,
//                              brokenHearts=false)
//
//
//  def play(trick: RandomTrick, taken: List[List[Card]], leader: Int, brokenHearts: Boolean): List[List[Card]] = {
//    val endOfGame = {taken map {_.size}}.sum == 52
//    val cardsPlayed = trick.cardsPlayed
//    val taker = trick.taker
//    def points = trick.points
//
//    if (endOfGame) taken else {
//      play(trick = RandomTrick(hands), 
//           taken = taken(taker) ++ cardsPlayed, 
//           leader = taker, 
//           brokenHearts = brokenHearts || points)
//    }
//  }
//}
