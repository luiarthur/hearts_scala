package Hearts

case class Card(card: String) { // Suit + Value

  def suit = card.head
  def value = card.tail

  def valueToInt: Int = value match {
    case "J" => 11
    case "Q" => 12
    case "K" => 13
    case "A" => 14
    case s: String => s.toInt
  }

  def valueGreaterThan(that: Card) = 
    this.valueToInt > that.valueToInt

  def valueLessThan(that: Card) = 
    this.valueToInt < that.valueToInt

  def valueEqualTo(that: Card) = 
    this.valueToInt == that.valueToInt

  def valueNotEqualTo(that: Card) = 
    this.valueToInt != that.valueToInt

  override def toString = suit + value
}
