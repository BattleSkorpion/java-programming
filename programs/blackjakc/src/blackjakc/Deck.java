package blackjakc;

public class Deck {

	private final String[] cardTypes = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	private final String[] suits = {"Hearts", "Diamonds", "Clover", "Spades"};
	private String[] deck = new String[52];  // 52 cards in a deck

	public Deck () {
		newDeck();
	}

	// get methods
	public String[] getDeckSuits() {
		return suits;
	}

	public String[] getCardTypes() {
		return cardTypes;
	}

	public String[] getDeck() {
		return deck;
	}

	public String deal() {
		// 0 to refer to top card of deck
		String card = deck[0];
		this.remove(0);
		// testing
		// System.out.println(card);
		// System.out.println("new deck top: " + deck[0]);
		return card;
	}

	public int length() {
		return deck.length;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < this.length(); i++) {
			str += deck[i];
			str += "\n";
		}
		return str;
	}

	static public int cardValue(String card, String[] hand, String game) {
		switch (game.toLowerCase()) {

		case "blackjack":
			switch (card.substring(0, card.indexOf(' ')).toLowerCase()) {
				case "ace": return 1; // could be 1 or 11 will decide later
				case "2": return 2;
				case "3": return 3;
				case "4": return 4;
				case "5": return 5;
				case "6": return 6;
				case "7": return 7;
				case "8": return 8;
				case "9": return 9;
				case "10": return 10;
				case "jack": return 10;
				case "queen": return 10;
				case "king": return 10;
			}
		}

		return -1; // fail
	}

	private void newDeck() {
		for (int i = 0, index = 0; i < cardTypes.length; i++) {
			for (int j = 0; j < suits.length; j++, index++) {
				deck[index] = cardTypes[i] + " of " + suits[j];
			}
		}
		//testing
		//System.out.println("deck in Deck: \n" + this.toString());
	}

	private void remove(int index) {
		String[] temp = new String[this.length() - 1]; // 1 smaller than deck length, removing one card

		for (int i = 0; i < index; i++) {
			temp[i] = deck[i];
		}

		// skip index, card being removed from deck

		for (int i = index; i < temp.length; i++) {
			temp[i] = deck[i + 1];
		}

		deck = temp;
	}
}
