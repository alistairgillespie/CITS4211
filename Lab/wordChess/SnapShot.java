package wordChess;

import agent.*;

/**
 * Write a description of class SnapShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnapShot implements Percept
{
    private String puzzle;
    int letterCycled = 0;
    int timesCycled = 0;
    boolean complete = false;
    
    public SnapShot(WordPuzzle w){setPuzzle(w.toString());}
    public SnapShot(String s){setPuzzle(s);}
    
    public void setPuzzle(String s){this.puzzle = s;}
    
    public int cycledChar(){
        timesCycled++;
        if (timesCycled == 27){
            timesCycled = 1;
            letterCycled++;
            if (letterCycled == puzzle.length()) complete = true;
        }
        return letterCycled;
    }
    
    public int getTimesCycled(){return this.timesCycled;}
    public String getString(){return this.puzzle;}
    
    public boolean isComplete(){return complete;}
}
