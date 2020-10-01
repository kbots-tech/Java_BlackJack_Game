import javax.swing.*;
import java.awt.event.*;

public class GUI extends GameRules{

  private int count=0;
  private int winStreak=0;
  public  GUI(){
    //The board frame
    JFrame gameBoard=new JFrame("BlackJack");
    gameBoard.setSize(500,700);

    //Start button
    JButton START=new JButton("Start");
    START.setSize(100,100);

    //Reset button (not fully in use)
    JButton RESET=new JButton("Reset");
    RESET.setSize(100,100);

    //Hit button adds card
    JButton HIT=new JButton("HIT");
    HIT.setSize(100,100);

    //Hold button keeps hand
    JButton HOLD=new JButton("HOLD");
    HOLD.setSize(100,100);

    //Labels showing score
    JLabel Score=new JLabel("Score: 0");
    Score.setSize(100,25);

    //Labels for last rounds results
    JLabel LastResult=new JLabel("Last Round");
    JLabel CoScore=new JLabel("Computer Score Was:");
    JLabel PlScore=new JLabel("Player Score Was:");

    //Label showing dealers first card
    JLabel DealerCard=new JLabel("The Dealers First Card:");

    //Buttons for changing the bet and showing bet
    JButton BetPlus=new JButton("Bet +");
    JButton BetMinus=new JButton("Bet -");
    JLabel bets=new JLabel("The Current Bet is: 0");
    JLabel chips=new JLabel("The Chips in hand are: 10");
    JButton SetBet=new JButton("Set Bet");

    //Label showing cards in players hand
    JTextArea cardsInHand=new JTextArea("The Cards in hand are:");

    //Label showing win Streak
    JLabel WinStreak=new JLabel("Win Streak:"+winStreak);


    //Adding labels and buttons to the board
    gameBoard.add(START);
    START.setBounds(0,0,200,100);
    gameBoard.add(HIT);
    HIT.setBounds(0,100,100,100);
    HIT.setVisible(false);
    gameBoard.add(HOLD);
    HOLD.setBounds(100,100,100,100);
    HOLD.setVisible(false);
    gameBoard.add(Score);
    Score.setBounds(275,0,100,100);
    gameBoard.add(LastResult);
    LastResult.setBounds(0,350,200,200);
    gameBoard.add(PlScore);
    PlScore.setBounds(0,375,200,200);
    gameBoard.add(CoScore);
    CoScore.setBounds(0,400,200,200);
    gameBoard.add(DealerCard);
    DealerCard.setBounds(0,300,500,200);
    gameBoard.add(BetPlus);
    BetPlus.setBounds(0,0,100,100);
    BetPlus.setVisible(false);
    gameBoard.add(BetMinus);
    BetMinus.setBounds(100,0,100,100);
    BetMinus.setVisible(false);
    gameBoard.add(bets);
    bets.setBounds(275,100,200,100);
    gameBoard.add(chips);
    chips.setBounds(275,150,200,100);
    gameBoard.add(cardsInHand);
    cardsInHand.setBounds(275,250,200,200);
    cardsInHand.setOpaque(false);
    gameBoard.add(SetBet);
    SetBet.setBounds(0,100,200,100);
    SetBet.setVisible(false);
    gameBoard.add(WinStreak);
    WinStreak.setBounds(275,0,200,200);
    gameBoard.setLayout(null);
    gameBoard.setVisible(true);
    

    //Adds two cards to players hand and plays the dealers cards
    START.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       Start();
       System.out.println("RESET");
       Score.setText("Score: "+Integer.toString(IScore));

       //Adds the two cards to the players hand
       HIT.doClick();
       HIT.doClick();
       START.setVisible(false);
       SetBet.setVisible(true);
       BetPlus.setVisible(true);
       BetMinus.setVisible(true);
      }
    });




    //Adds card to player hand
    HIT.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       updateScore();
       Score.setText("Score: "+Integer.toString(IScore));
       System.out.println("HIT");
       //LastCard.setText("Last Card: "+Last);
       DealerCard.setText("The Dealers First Card "+DCard);

       //Adds 1 to the card count
       count+=1;

       //Updates the label for cards in players hand
       cardsInHand.setText("The Cards in Hand Are:\n"+cardsHand);

       //If the score gets about 21 automatically holds
       if(IScore>21){
         HOLD.doClick();
       }

      }
    });

    //Code for hold button
    HOLD.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       System.out.println("HOLD");
       Hold();
       START.setVisible(true);
       HOLD.setVisible(false);
       HIT.setVisible(false);

       //Shows last round W/L/T results
       if(LastRound.equals("W")){
        LastResult.setText("Last Result: Win");
        winStreak+=1;
        WinStreak.setText("Win Streak: "+winStreak);
       }else if(LastRound.equals("L")){
        LastResult.setText("Last Result: Loss");
        winStreak=0;
        WinStreak.setText("Win Streak: "+winStreak);
       }else if(LastRound.equals("T")){
        LastResult.setText("Last Result: Draw");
        winStreak+=1;
        WinStreak.setText("Win Streak: "+winStreak);
       }
       
       //Updates the labels for last round score and results, also blanks old labels
       PlScore.setText("Player score was: "+PIScore);
       CoScore.setText("Computer score was: "+PCScore);
       Score.setText("Score: ");

       //Shows new chips and resets the bet
       chips.setText("The Chips in hand are: "+chip);
       bets.setText("The Current Bet is: 0");
      
       //Resets the count and allows betting again
       count=0;
      }
    });

    //Button to increase bet 
    BetPlus.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      if(count!=2){

      }else{
       BetUp();
       bets.setText("The Current Bet is: "+bet);
      }
      }
    });

    //Button to decrease bet
    BetMinus.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      if(count!=2){

      }else{
       BetDown();
       bets.setText("The Current Bet is: "+bet);
      }
      }
    });

    //Sets the bet value and hides/shows needed buttons
    SetBet.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        SetBet.setVisible(false);
        BetPlus.setVisible(false);
        BetMinus.setVisible(false);
        HIT.setVisible(true);
        HOLD.setVisible(true);
      }
    });
  
  }

  //Backup code for reset  
  public String RESET(){
    Reset();
    return "Reset";

  }


  //Code in the main class to start the game automatically
  public String START(){
    Start();
    return "Start";
  }
}