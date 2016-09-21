package Hearts

// Assume 4 players & 1 deck
class Deck {

  private val suits = 
    List("S","H","C","D") // spade, heart, club, diamond

  private val values = 
    (2 to 10).toList.map(_.toString) ++ List("J","Q","K","A")
  
  val cards = 
    for (suit <- suits; value <- values) yield Card(suit + value)

  private val nCardsPerPlayer = 13
  private def shuffle = scala.util.Random.shuffle(cards)

  def deal = 
    shuffle.grouped(nCardsPerPlayer).toList//.map{l=>l.sortBy(_.card)}
}

/* In file testing:
    import Hearts._
    import ORF.models.timer
    val deck = new Deck
    val hands = deck.deal

    val cards = List(Card("S12"),Card("SA"),Card("H13"),Card("D2"))
 */
