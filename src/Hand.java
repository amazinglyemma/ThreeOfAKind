import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	private ArrayList<Card> hand = new ArrayList<>();

	public void showHand() {

		for (Card card : hand) {
			System.out.println(card);
		}
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void discardCard(int card, Deck deck) {
		deck.addCard(hand.get(card));
		hand.remove(card);
	}

	public void takeCard(Deck deck) {
		this.addCard(deck.currentCard()); // add card to hand
		deck.removeCard(); // remove card from discard pile
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean isWin() {
		for (int i = 0; i < getHand().size(); i++) {
			// get first object to compare
			int count = 1;
			int rankCompare = getHand().get(i).rank;
			for (int j = i + 1; j < getHand().size(); j++) {
				if (rankCompare == getHand().get(j).rank) {
					count++;
				}
			}
			if (count == 3) {
				return true;
			}
		}
		return false;
	}

	// sort cards by rank
	public void sort() {
		Collections.sort(hand);
	}

	public int pickDiscard() {
		int rankMatch = hand.get(4).rank;
		for (int i = 0; i <= 2; i++) {
			if (rankMatch == hand.get(i).rank) {
				if (rankMatch == hand.get(i + 1).rank) {
					if (i + 2 > 5) {
						return 0; // if next card value is greater than hand limit, discard 1st card.
					}
					return i + 2;
				}
				return i + 1;
			}
		}
		if (rankMatch == hand.get(3).rank) {
			if (hand.get(0).rank == hand.get(1).rank) {
				//last card matches. 1st and 2nd card match eachother, discard 3rd.
				return 2;
			}
			//Last card matches, 1st and 2nd do not. Discard 1st card.
			return 0;
		}
		return 4;
	}
}