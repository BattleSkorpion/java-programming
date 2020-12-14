//package blackjakc;
//
//import java.util.Arrays;
//
//public class Hand {
//	private String[] hand;
//
//	public Hand() {
//		hand  = new String[0];
//	}
//
//	public Hand(int numCards, String[] cards) {
//		hand = new String[numCards];
//		hand = cards;
//	}
//
//	public void add(String card) {
//		String[] temp = new String[hand.length + 1];
//		for (int i = 0; i < hand.length; i++) {
//			temp[i] = hand[i];
//		}
//		temp[temp.length - 1] = card;
//
//		hand = temp;
//	}
//
//	public String[] getHand() {
//		return hand;
//	}
//
//	public String toString() {
//		String str = "";
//		for (int i = 0; i < hand.length; i++) {
//			str += hand[i];
//			str += "\n";
//		}
//		// test
//		// System.out.println(str.length());
//		return str;
//	}
//
//	public int length() {
//		return hand.length;
//	}
//
//	public int[] handValue(String game) {
//		int[] values = new int[hand.length + 1]; // will also return total hand value at end
//		int totalValue = 0;
//
//		// games
//		switch (game.toLowerCase()) {
//
//		// for blackjack
//		case "blackjack":
//			int numAce = 0;
//			int aceIndex[] = new int[hand.length];
//
//			// first for assigning values, ace will be given 0 for now
//			for (int i = 0; i < hand.length; i++) {
//				values[i] = Deck.cardValue(hand[i], hand, game);
//				totalValue += values[i];
//			}
//
//			// find number of aces
//			for (int i = 0; i < hand.length; i++) {
//				if (values[i] == 0) {
//					numAce++;
//					aceIndex[numAce] = i;
//				}
//			}
//			// decreasing array size to what is necessary
//			aceIndex = Arrays.copyOfRange(aceIndex, 0, numAce);
//
//			// decide what to do with aces
//			// TODO: NO NO NO NO NO NO NO!!!! FIX THE TOP
//			if (numAce > 1) {
//				for (int i = 0; i < numAce; i++) {
//					values[aceIndex[i]] = 1; // 11 * 2 == 22, too high, any more would be too high as well. can only have one soft/11 ace.
//				}
//			}
//			else if (totalValue <= 10) { // meaning ace value being 11/soft would not cause bust
//				values[aceIndex[0]] = 11;
//			}
//			else {
//				values[aceIndex[0]] = 1;
//			}
//
//			// final stuff and return
//			values[values.length - 1] = totalValue;
//			return values;
//
//		// end blackjack case
//
//		} // end switch
//	}
//
//}
