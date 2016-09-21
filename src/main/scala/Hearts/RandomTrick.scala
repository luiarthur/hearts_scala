package Hearts

//import Hearts.util

//case class RandomTrick(hands: List[Map[Char,List[Card]]], 
//                       leader: Int,
//                       brokenHearts: Boolean = false) {
//
//  val Rand = scala.util.Random
//
//  type Hand = Map[Char,List[Card]]
//  type Hands = List[Map[Char,List[Card]]]
//
//  private def randomSuit(hand: Hand) = {
//    val availableSuits = {
//      val suits = hand.keys.toList
//      if (brokenHearts || suits.size==1) suits else suits.filterNot(_=='H')
//    }
//
//    Rand.shuffle(availableSuits).head
//  }
//
//  private def hasSuit(hand: Hand, suit: Char) = 
//    hand isDefinedAt suit
//
//  private def randomTrick(hands: Hands, leader: Int) = {
//    val suitInPlay = randomSuit(hands(leader))
//    val cards = hands map { hand =>
//      if (hasSuit(hand,suitInPlay)) {
//        hand(suitInPlay).head
//      } else {
//        hand(randomSuit(hand)).head
//      }
//    }
//    cards
//  }
//
//  val cardsPlayed = randomTrick(hands, leader)
//  private val trick = Trick(cardsPlayed, leader)
//  val taker = trick.taker
//  def points = cardsPlayed contains { card => 
//    card match {
//      case "SQ" => true
//      case s: String => s.head == "H"
//    }
//  }
//}

/*  in file testing:
    val deck = new Deck
    val hands = deck.deal
 */
