import javax.swing.*;
import java.awt.event.*;

public class GameRules{

  //Int to check if Ace is worth 1 or 11
  private int aceCo=1;
  private int aceUs=1;

  //Int to adjust dealer turn
  private int DealerTurn;

  //Deck of cards used
  private Deck cards;

  //Count of card this round is on
  public int CardCount;

  //User and Computer score values
  public int IScore=0;
  public int CScore=0;

  //Last Card Values
  public String Last="Null";
  public String Last2="Null";

  //Last Round Result Values
  public String LastRound="Null";

  //Array for holding user cards
  private String[] Card=new String[52];

  //Last Score of the Player and Computer
  public int PIScore=0;
  public int PCScore=0;

  //String for last Dealer Card
  public String DCard="Dealer Card is";

  //Int for chips in hand
  public int chip=10;
  public int bet=0;

  //Past cards int and string for storing them
  private int PCards=0;
  public String cardsHand="";

  //Creates a new deck at the start of the round
  public void Start(){
    cards=new Deck();
  }

  //Resets the cards and card count if at a full deck
  public void Reset(){
    if(CardCount==51){
      cards=new Deck();
      CardCount=0;
    }
    System.out.println("RESET"); 
    IScore=0;
    CScore=0;

  }


  //Updates the Score for user and dealer on the first round
  public void updateScore(){
    //Reshuffles cards if needed
    if(CardCount==51){
      cards=new Deck();
      CardCount=0;
    }
    Last=cards.toString(CardCount);
    Card[PCards]=(cards.toString(CardCount));
    PCards+=1;
    
    //Creates and prints the String for cards in hand of player
    System.out.println("The Cards in hand are:");
    cardsHand="";
    for(int i=0; i<PCards; i++){
      cardsHand=cardsHand+Card[i]+"\n";
    }
    System.out.println(cardsHand);

    //Code for points of User
    if(Last.substring(0,1).equals("2")){
      IScore+=2;
    }else if(Last.substring(0,1).equals("3")){
      IScore+=3;
    }else if(Last.substring(0,1).equals("4")){
      IScore+=4;
    }else if(Last.substring(0,1).equals("5")){
      IScore+=5;
    }else if(Last.substring(0,1).equals("6")){
      IScore+=6;
    }else if(Last.substring(0,1).equals("7")){
      IScore+=7;
    }else if(Last.substring(0,1).equals("8")){
      IScore+=8;
    }else if(Last.substring(0,1).equals("9")){
      IScore+=9;
    }else if(Last.substring(0,1).equals("J")||Last.substring(0,1).equals("Q")||Last.substring(0,1).equals("K")||Last.substring(0,1).equals("1")){
      IScore+=10;
    }else if(Last.substring(0,1).equals("A")){
      if(IScore+11<=21){
        IScore+=11;
        aceUs=11;
      }else{
        IScore+=1;
        aceUs=1;
      }
    }else{
      IScore+=1;
      }
    
    //This is for is the user has an ace worth 11 and got over 21
    if((IScore>21)&&(aceUs==11)){
        IScore-=10;
        System.out.println("User Score Ace Minus");  
    }
    this.IScore=IScore;
    CardCount++;
    System.out.println("The Score is \n"+IScore);
    
   //Shows us the dealers first card ONLY 
   while(DealerTurn==0){
     DCard=cards.toString(CardCount);
     DealerTurn++;
   }

   //Dealer cards runs through automatically, hits to at least 16 like normal blackjack rules
   while(CScore<=16){  
     Last2=cards.toString(CardCount);
     CardCount++;
     if(Last2.substring(0,1).equals("2")){
      CScore+=2;
    }else if(Last2.substring(0,1).equals("3")){
      CScore+=3;
    }else if(Last2.substring(0,1).equals("4")){
      CScore+=4;
    }else if(Last2.substring(0,1).equals("5")){
      CScore+=5;
    }else if(Last2.substring(0,1).equals("6")){
      CScore+=6;
    }else if(Last2.substring(0,1).equals("7")){
      CScore+=7;
    }else if(Last2.substring(0,1).equals("8")){
      CScore+=8;
    }else if(Last2.substring(0,1).equals("9")){
      CScore+=9;
    }else if(Last2.substring(0,1).equals("J")||Last.substring(0,1).equals("Q")||Last.substring(0,1).equals("K")){
      CScore+=10;
    }else if(Last2.substring(0,1).equals("A")){
      if(CScore+11<=21){
        CScore+=11;
        aceCo=11;
      }else{
        CScore+=1;
        aceCo=1;
        
      }
    }else{
      CScore+=1;
    }

    //Same as user code subtracts the 10 for the ace going to 1
    if((CScore>21)&&(aceCo==11)){
      CScore-=10;
      aceCo=1;
    }

    //For if cards run out reshuffles deck
    if(CardCount==51){
      cards=new Deck();
      CardCount=0;
    }
   }
  }
  
  //Code to keep cards and determine win or loss
  public void Hold(){

    int DealerTurn=0;
    //Resets ace value to 11
    aceCo=1;
    aceUs=1;

    //Runs through the code for which user won or tied
    PIScore=IScore;
    PCScore=CScore;
    if(IScore>CScore&&IScore<=21||CScore>21){
      System.out.println("You Win");
      System.out.println("Your score was "+IScore);
      System.out.println("Dealer score was "+CScore);
      Reset();
      LastRound="W";
    }else if(CScore>IScore||IScore>21){
      System.out.println("You Lose");
      System.out.println("Your score was "+IScore);
      System.out.println("Dealer score was "+CScore);
      LastRound="L";
      Reset();
    }else if(CScore==IScore){
      System.out.println("Draw");
      System.out.println("Your score was "+IScore);
      System.out.println("Dealer score was "+CScore);
      Reset();
      LastRound="T";
    }else{
      System.out.println("What happened?");
      System.out.println("Your score was "+IScore);
      System.out.println("Dealer score was "+CScore);
      Reset();
    }

    //Runs points 
    PointsGained();

    //Prints out the chips gained
    System.out.println("Chips in hand are "+chip);
    PCards=0;

  }

  //Code for increasing bet
  public void BetUp(){
    if(bet<chip){
      bet+=1;
      System.out.println("Current bet is "+bet);
    }else{
      System.out.println("Max Bet");
    }
  }

  //Code for decreasing bet
  public void BetDown(){
    if(bet>1){
      bet-=1;
      System.out.println("Current bet is "+bet);
    }else{
      System.out.println("Min Bet");
    }
  }  

  //Code to calculate Points lost or won
  public void PointsGained(){
    if(LastRound.equals("W")){
      chip+=bet*2;
    }else if(LastRound.equals("T")){

    }else{
      chip-=bet;
    }
    bet=0;
    if(chip==0){
      chip=10;
    }
  }
}