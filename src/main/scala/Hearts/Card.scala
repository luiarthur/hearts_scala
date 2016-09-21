package Hearts

case class Card(card: String) { // Suit + Value

  def suit = card.head
  def value = card.tail

  def isPointCard = points > 0
  def points = card match {
    case "SQ" => 13
    case s: String => if (s.head == 'H') 1 else 0
  }

  def magnitude: Int = value match {
    case "J" => 11
    case "Q" => 12
    case "K" => 13
    case "A" => 14
    case s: String => s.toInt
  }

  override def toString = suit + value
}
