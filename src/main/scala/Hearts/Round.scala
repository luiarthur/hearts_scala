package Hearts

/*
 y_i = f(x_i),

 where x_i: 52+1+1 vector
 
 Computer is player j
 52 cards => value: -4,-3,-2,-1,0,1,2,3,4,5
 -j -> played by player j
  0 -> unseen card
 +j -> player j played this card in current trick
  5 -> I, player 1, have the card

  leader: {1,2,3,4}

  trick number = {1,...,13}
 */

class Round {
  val deck = new Deck
  type LLCards = List[List[Card]]

  // return the played cards
  def simulate = {
    import scala.util.Random.shuffle

    def trick(hands: LLCards, played: LLCards, leader: Int, broken: Boolean): (LLCards,LLCards) = {

      def endOfGame = hands.map(_.size).sum == 0//played.map(_.size).sum == 52

      if (endOfGame) (hands, played) else {

        val ldr = if (leader > -1) leader else 
                    hands.zipWithIndex.filter(_._1 contains Card("C2")).head._2

        val idx = ldr match {
          case 0 => List(0,1,2,3)
          case 1 => List(1,2,3,0)
          case 2 => List(2,3,1,0)
          case 3 => List(3,0,1,2)
        }

        val leadCard = if (leader == -1) Card("C2") else {
          val availableCards = 
            if (broken || hands(ldr).filterNot(_.suit=='H').size==0) {
              shuffle(hands(ldr))
            } else shuffle(hands(ldr).filterNot(_.suit=='H'))
          availableCards.head
        }
        val leadSuit = leadCard.suit
        val cards = idx map { i =>
          if (i == ldr) leadCard else {
            val cardsInSuit = hands(i).filter(_.suit==leadSuit)
            if (cardsInSuit.size > 0) shuffle(cardsInSuit).head else 
              shuffle(hands(i)).head
          }
        }

        val newHands = idx map { i => hands(i).filterNot(_ == cards(i)) }
        val newPlayed = idx map { i => cards(i) :: played(i) }
        val newLeader = cards.zipWithIndex.filter(_._1.suit==leadSuit).
                          maxBy(_._1.magnitude)._2
        val newBroken = broken || cards.map(_.suit).contains('H') 

        trick(newHands,newPlayed,newLeader,newBroken)
      }
    }

    trick(deck.deal, List.fill(4)(List()), -1, false)
  }
}
