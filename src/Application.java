import java.util.Scanner;

public class Application {

	private static Scanner input;

	public static void main(String[] args) {
		int num = -1;
		String choice = "null";
		// Create discard deck
		Deck discardDeck = new Deck();

		// Create Deck and Shuffle
		Deck deck = new Deck();
		for (int suit = 0; suit <= 3; suit++) {
			for (int rank = 2; rank <= 14; rank++) {
				Card newCard = new Card(rank, suit);
				deck.addCard(newCard);
			}
		}
		deck.shuffle();

		// Deal hands
		Hand playerHand = new Hand();
		for (int i = 0; i < 4; i++) {
			playerHand.takeCard(deck);
		}

		Hand computerHand = new Hand();
		for (int i = 0; i < 4; i++) {
			computerHand.takeCard(deck);
		}

		// deal first card to discard pile
		deck.discard(discardDeck);

		// start game
		while (!(playerHand.isWin()) && !(computerHand.isWin())) {
			// if deck is empty, shuffle the discard deck
			if (deck.isDeckEmpty()) {
				System.out.println("END OF DECK");
				discardDeck.shuffleDiscard(deck);
				deck.discard(discardDeck);
			}

			// show player hand
			playerHand.sort();
			playerHand.showHand();

			// show discard pile
			System.out.println();
			System.out.println(discardDeck.currentCard());

			// get user input
			input = new Scanner(System.in);
			System.out.println("Take, or Draw?");
			choice = input.nextLine();

			// validate user input
			choice = choice.toLowerCase();
			while (!(choice.equals("draw")) && !(choice.equals("take"))) {
				System.out.println("Take, or Draw?");
				choice = input.nextLine();
				choice.toLowerCase();
			}

			// Take or draw card
			switch (choice) {
			case "take":
				playerHand.takeCard(discardDeck); // take card from discardDeck
				
				//ask for discard
				System.out.println("Choose a card to discard: 1,2,3,4");
				num = input.nextInt() - 1; // subtract 1 to align with 0 indexed arrayList
			

				while (num < 0 || num > 3) { // ERROR CATCH
					System.out.println("Error: Enter an integer between 1 -4");
					System.out.println("Choose a card to discard: 1,2,3,4");
					num = input.nextInt() - 1; // subtract 1 to align with 0 indexed arrayList
				}

				playerHand.discardCard(num, discardDeck);

				break;
			case "draw":
				System.out.println("Card Drawn: " + deck.currentCard());
				playerHand.takeCard(deck);
				
				// ask for discard
				System.out.println("Choose a card to discard: 1,2,3,4 or 5 for drawn card");
				num = input.nextInt() - 1; // subtract 1 to align with 0 indexed arrayList
				
				while (num < 0 || num > 4) { // ERROR CATCH
					System.out.println("Error: Enter an integer between 1 - 5");
					System.out.println("Choose a card to discard: 1,2,3,4 or 5 for drawn card");
					num = input.nextInt() - 1; // subtract 1 to align with 0 indexed arrayList
				}
				
				playerHand.discardCard(num, discardDeck);

			}
			if (playerHand.isWin()) {
				System.out.println("Player wins!");
				break;
			}
			// show player hand
			System.out.println("########################################");
			System.out.println();

			//////////////////////////// COMPUTER'S TURN
			//////////////////////////// ////////////////////////
			System.out.println("COMPUTERS TURN!!!!!");
			computerHand.sort();

			// See if the card in the discard deck matches a card in the
			// computerHand
			int match = 0;
			for (Card card : computerHand.getHand()) {
				if (card.rank == (discardDeck.currentCard().rank)) {
					match++;
				}
			}

			if (match > 0) {
				computerHand.takeCard(discardDeck);
				computerHand.discardCard(computerHand.pickDiscard(), discardDeck);
				System.out.println("Computer picks from discard pile");
				System.out.println();
			} else {
				computerHand.takeCard(deck);
				computerHand.discardCard(computerHand.pickDiscard(), discardDeck);
				System.out.println("Computer picks from draw pile");
				System.out.println();
			}
			if (computerHand.isWin()) {
				System.out.println("Computer wins!");
				break;
			}
		}
	}
}