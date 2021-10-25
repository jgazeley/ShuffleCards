import java.util.Random;

public class Cards {}

class Card {

	private int value;
	private int suit;
	private String v;
	private String st;

	public Card(int value, int suit) {

		this.value = value;
		this.suit = suit;
	}

//*****************************************************************************

	public String getValue() {
		if(value == 1)
			v = "Ace";
		if(value == 2)
			v = "Two";
		if(value == 3)
			v = "Three";
		if(value == 4)
			v = "Four";
		if(value == 5)
			v = "Five";
		if(value == 6)
			v = "Six";
		if(value == 7)
			v = "Seven";
		if(value == 8)
			v = "Eight";
		if(value == 9)
			v = "Nine";
		if(value == 10)
			v = "Ten";
		if(value == 11)
			v = "Jack";
		if(value == 12)
			v = "Queen";
		if(value == 13)
			v = "King";

		return v;
	}

//*****************************************************************************

	public String getSuit() {

		if(suit == 1)
			st = "Clubs";
		if(suit == 2)
			st = "Diamonds";
		if(suit == 3)
			st = "Hearts";
		if(suit == 4)
			st = "Spades";

		return st;
	}

//*****************************************************************************

	public int getIntValue() { return value; }
	public int getIntSuit() { return suit; }

	public boolean equals(String other) {
		return this.toString().equals(other.toString());
	}

	public String toString() {
		String s = getValue() + " of " + getSuit();
		return s;
	}

//*****************************************************************************

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

class ListNode {

	private Card card;
	private ListNode next;

	//Constructor 1 (Empty)
	public ListNode() {}

	//Constructor 2 (Accepts Card object as parameter)
	public ListNode(Object o) {
		if (o instanceof Card) {
			Card c = (Card)o;
			this.card = c;
		}
	}

	//Constructor (Accepts Card object and ListNode as parameters)
	public ListNode(Object o, ListNode next) {
		if(o instanceof Card) {
			Card c = (Card)o;
			card = c;
			this.next = next;
		}
	}

//*****************************************************************************

	//Getter methods for ListNode class
	public Card getCard() { return card; }
	public ListNode getNext() { return next; }

	//Setter method to set the reference for the next node
	public void setNext(ListNode n) { this.next = n; }

//*****************************************************************************

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

class Deck {

	private ListNode front;

	public Deck() {
		
		for(int i = 1; i <= 13; i++) {
			add(i, 3); //add hearts to deck
		}

		for(int i = 1; i <= 13; i++) {
			add(i, 1); //add clubs to deck
		}

		for(int i = 13; i >= 1; i--) {
			add(i, 2); //add diamonds to deck
		}

		for(int i = 13; i >= 1; i--) {
			add(i, 4); //add spades to deck
		}

	}

//*****************************************************************************

	public void add(int value, int suit) {

		Card c = new Card(value, suit);
		ListNode curr = front;

		if(front == null) {
			front = new ListNode(c);
			return;
		}

		ListNode n = new ListNode(c);

		while(curr.getNext() != null) {
			curr = curr.getNext();
		}

		curr.setNext(n);
	}

//*****************************************************************************

	public void add(int pos, int value, int suit) {

		Card c = new Card(value, suit);

		if(pos == 0) {
			ListNode n = new ListNode(c);
			n.setNext(front);
			front = n;
			return;
		}

		ListNode curr = front;
		int i = 0;

		while(i < pos - 1) {
			curr = curr.getNext();
			i++;
		}

		ListNode n = new ListNode(c);

		n.setNext(curr.getNext());

		curr.setNext(n);
	}

//*****************************************************************************

	public void remove(String card)	{
		if(front == null)
			return;

		if(front.getCard().equals(card))
			front = front.getNext(); 
		ListNode pre = front;
		ListNode curr = front;

		while(curr != null && !(curr.getCard().equals(card))) {
			pre = curr;
			curr = curr.getNext();
		}

		if(curr != null && curr.getNext() == null &&(curr.getCard().equals(card))) {
			pre.setNext(null);
		}

		else if(curr == null) {}

		else {
			pre.setNext(curr.getNext());

		}
	}

//*****************************************************************************

	public String getString(int pos) {
	
		ListNode curr = front;
		int index = 1;

		while(pos != index) {
			index++;
			curr = curr.getNext();
		}

		return curr.getCard().toString();
	}

//*****************************************************************************

	public Card getCard(int pos) {
	
		ListNode curr = front;
		int index = 1;

		while(pos != index) {
			index++;
			curr = curr.getNext();
		}

		return curr.getCard();
	}

//*****************************************************************************

	public void shuffle() {

		Random r = new Random();
		String rand;
		String last;
		int randValue, lastValue, randSuit, lastSuit, intRandom;

		for(int i = 52; i > 1; i--) {

			intRandom = r.nextInt(i) + 1;

			while(intRandom == i) {
				intRandom = r.nextInt(i) + 1;
			}

			rand = getString(intRandom);
			last = getString(i);
			randValue = getCard(intRandom).getIntValue();
			randSuit = getCard(intRandom).getIntSuit(); 
			lastValue = getCard(i).getIntValue();
			lastSuit = getCard(i).getIntSuit();       
			remove(rand);
			remove(last);
			add(i -2, randValue, randSuit);
			add(intRandom -1, lastValue, lastSuit);
		}
	}

//*****************************************************************************

	public String toString() {

		if(front == null)
			return "";

		ListNode curr = front;
		String s = "";

		while(curr != null) {
			s = s + curr.getCard() + " \n";
			curr = curr.getNext();
		}

		return s;
	}

//*****************************************************************************

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

class Driver {
	public static void main(String[] args) {
		Deck deck = new Deck();
		// System.out.println(deck);
		// System.out.println("Shuffling deck...\n");
		deck.shuffle();
		System.out.println(deck);
	}
}
