package Hearts

class Deck(val seed: Int = 0)  {
  scala.util.Random.setSeed(0)
  val Rand = scala.util.Random

  val suits = List("S","H","C","D") // spade, heart, club, diamond
  val values = (2 to 10).toList.map(_.toString) ++ List("J","Q","K","A")
  
  val cards = 
    for (suit <- suits; value <- values) yield value + suit

  def shuffle = Rand.shuffle(cards)

  def deal = 
}
