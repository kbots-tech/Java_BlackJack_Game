//Created March of 2020 by Keegan Curran to practice and improve java skills, all code is original to him except the deck.java class (see notes there). 
//Code should follow all rules of blackjack and includes code to check if the Ace should be worth 1 or 11. The Code allows for betting chips and adds them or subtracts them based on W/L record. The dealer automatically plays their cards up to 16 and shows the first card, as should be done. The code includes checks to not allowing betting after the 3rd card is dealt and resets each time.
//INSTRUCTIONS: 
    //Required files:
      //Main.java-Creates the UI and runs the start code
      //Deck.java-Includes the code for the cards in the deck 
      //GameRules.java-Includes the code that controls the logic/rules of the game
      //GUI.java-Includes the UI of the code along with methods to create and control it

    //To play, run code and press START button, this will automatically add 2 cards to your hand and have the dealer run it's course. Later improvements may run cards differently so each time the player hits that's when the dealer will add additional cards. Use the Bet+ and Bet- buttons to increase or decrease the bets, after first HIT these can't be changed so bet wisely. Press HIT till you want to keep the cards in hand at which point press HOLD. To begin again press start once more for a new round.


import javax.swing.*;
import java.awt.event.*;

public class Main{
  public static void main(String args[]){
    GUI board=new GUI();
    board.START();
  }
}