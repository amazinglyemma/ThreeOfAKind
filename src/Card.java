public class Card implements Comparable<Card> {
	private static String suitNames[] = {"Hearts","Diamonds","Clubs","Spades"};
	private static String rankNames[] = {"narf","narf","2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
	int suit;
	int rank;
	
	//Constructor
	public Card(int rank, int suit){
		this.suit = suit;
		this.rank = rank;
	}
	
	//getRank
	public int getRank(){
		return rank;
	}
	//ToString
	public String toString(){
		return rankNames[rank] + " of " + suitNames[suit];
	}

	@Override
	public int compareTo(Card card) {
		if (rank < card.rank) return -1;
		if (rank > card.rank) return 1;
		return 0;
	}		
}
