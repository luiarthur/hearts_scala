package Hearts

// Assume 4 players & 1 deck
class Deck(val seed: Int = 0)  {

  scala.util.Random.setSeed(0)
  val Rand = scala.util.Random

  private val suits = 
    List("S","H","C","D") // spade, heart, club, diamond

  private val values = 
    (2 to 10).toList.map(_.toString) ++ List("J","Q","K","A")
  
  private val cards = 
    for (suit <- suits; value <- values) yield Card(suit + value)

  private val nCardsPerPlayer = 13
  private def shuffle = Rand.shuffle(cards)

  def deal = shuffle.
               grouped(nCardsPerPlayer).
               map(g => g.groupBy(_.suit)).toList
}

/* In file testing:
   val deck = new Deck
   val hands = deck.deal
 */
