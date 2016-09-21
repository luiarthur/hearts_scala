package Hearts

class Cards {
  val suits = List("S","H","C","D") // spade, heart, club, diamond
  val values = (2 to 10).toList.map(_.toString) ++ List("J","Q","K","A")
  
  val cards = 
    for (suit <- suits; value <- values) yield value + suit
}
