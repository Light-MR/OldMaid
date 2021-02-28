/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.HashMap;

/**
 * Code representation of a playing card.
 * Added functionalities:
 * -Reverse mapping of enums
 * @author Seiji Dominic Bautista
 */
public class Card implements Comparable<Card> {

     private Suit suit;//clubs, spades, diamonds, hearts
     private Value value;//1-13

     enum Value {
          ACE(1),
          TWO(2),
          THREE(3),
          FOUR(4),
          FIVE(5),
          SIX(6),
          SEVEN(7),
          EIGHT(8),
          NINE(9),
          TEN(10),
          JACK(11),
          QUEEN(12),
          KING(13);

          int number;
          //Map to get Enum using int value;
          private static final HashMap<Integer, Value> map;

          static {
               map = new HashMap<>();
               for (Value value : Value.values()) {
                    map.put(value.getNumber(), value);
               }
          }

          Value(int i) {
               this.number = i;
          }

          public int getNumber() {
               return number;
          }

          public static Value getValue(int val) {
               return map.get(val);
          }
     }

     enum Suit {
          HEARTS(0, "Hearts"),
          DIAMONDS(1, "Diamonds"),
          SPADES(2, "Spades"),
          CLUBS(3, "Clubs");


          private final Integer number;
          private final String name;
          private static final HashMap<Integer, Suit> map;

          static {
               map = new HashMap<>();
               for (Suit suit : Suit.values()) {
                    map.put(suit.number, suit);
               }
          }

          Suit(int num, String name) {
               this.number = num;
               this.name = name;
          }

          public int getNumber() {
               return number;
          }

          public String getName() {
               return name;
          }

          /**
           * Find suit obj by its fields.
           * Throws IllegalArgumentException if suit num out of range.
           * @param num Suit number equivalent
           * @return Suit
           */
          public static Suit getByNum(int num) {
               if (num > 3 || num < 0)
                    throw new IllegalArgumentException("Suit number out of range.");
               return map.get(num);
          }
     }

     public Card() {}

     public Card(int suit, int value) {
          setSuit(suit);
          setValue(value);
     }

     /**
      * @return the suit
      */
     public Suit getSuit() {
          return suit;
     }

     /**
      * @param suit the suit to set
      */
     public void setSuit(String suit) {
          try {
               this.suit = Suit.valueOf(suit.trim().toUpperCase());
          } catch (IllegalArgumentException e) {
               System.out.println("That card does not exist.");
          }
     }

     public void setSuit(int suit) {
          this.suit = Suit.getByNum(suit);
     }

     /**
      * @return the CardNumber
      */
     public int getValue() {
          return value.getNumber();
     }

     /**
      * @param value CardNumber
      */
     public void setValue(int value) {
          if (value < 1 || value > 13)
               throw new IllegalArgumentException("Card value out of range.");
          this.value = Value.getValue(value);
     }

     /**
      * Returns the equality of parameter obj and this instance.
      *
      * @param card Object
      * @return card and this instance's equality
      */
     public boolean equals(Object card) {
          if (card == this)
               return true;
          if (card == null)
               return false;
          if (card instanceof Card) {
               return getSuit().equals(((Card) card).suit) &&
                       getValue() == ((Card)card).getValue();
          }
          return false;
     }

     /**
	 * 
	 * @param o
	 */
	@Override
     public int compareTo(Card o) {
          if (this.value.getNumber() > o.value.getNumber()) {
               return 1;
          } else if (this.value.getNumber() < o.value.getNumber()) {
               return -1;
          } else {
               return 0;
          }
     }

     public static void main(String[] args) {
          Card c = new Card();
          c.setValue(9);
          c.setSuit(6);
          System.out.println(c);
     }

     @Override
     public String toString() {
          return String.format("%d of %s", this.getValue(), this.getSuit().getName());
     }
}