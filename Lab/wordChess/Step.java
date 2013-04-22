package wordChess;

import agent.Action;
/**
 * Write a description of class Step here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Step implements Action
{
   private int position;
   private char to;
   
   public Step(int position, char to){
       this.position = position;
       this.to = to;
    }
   public int getPosition() {return position;}
   public char getTo() {return to;}   
   public double getCost(){return 1;}
    
}
