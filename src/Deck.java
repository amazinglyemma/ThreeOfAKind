import java.util.*;

public class Deck {
	private  ArrayList<Card> deck = new ArrayList<>();

	
	//create deck
	public Deck(){
	
	}
	public void addCard(Card card){
		deck.add(0, card);
	}
	
	public void removeCard(){
		deck.remove(0);
	}
	
	public Card currentCard(){
		if (deck.isEmpty()){
			
			return deck.get(0);
		}
		else{
		return deck.get(0);
		}
	}
	
	
	public void shuffle(){
		for(int i=0; i<51; i++){ 
			int rand = new Random().nextInt(52-i)+i;
			Card temp = deck.get(i);
			deck.set(i,deck.get(rand));
			deck.set(rand, temp);
		}
	}
	
	public void shuffleDiscard(Deck newDeck){
		shuffle(); // shuffle discard deck
		//iterate each item in this.deck and add card to newDeck
		for (Card card: deck){
			newDeck.addCard(card);
		}
		
		
	}
	
	public void discard(Deck discardDeck){
		discardDeck.addCard(deck.get(0));
		deck.remove(0);
	}
	
	public boolean isDeckEmpty(){
		return deck.isEmpty();
		}
	
	
}