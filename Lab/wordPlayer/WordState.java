package wordPlayer;

import agent.*;
import search.*;
import wordPlayer.*;
import wordChess.*;
/**
 * Write a description of class WordState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordState implements State
{
    String state; 

    public WordState(String state){this.state = state;}

    public void update (Action action) throws RuntimeException{
        Step s = (Step) action;
        if (s.getPosition() > state.length() - 1) return;
        char[] current = state.toCharArray();
        current[s.getPosition()] = s.getTo();
        String modified = new String(current);
        this.setState(modified);
    }

    public Actions getActions(){
        String original = state;
        WordSmith smith = new WordSmith();
        SnapShot snap = new SnapShot(state);
        Actions newActions = new Actions();
        while(!snap.isComplete()){
            Action a = smith.getAction(snap);
            if (a != null){
                this.update(a);
                if(state.compareTo(original) != 0) {
                newActions.add(a);
                //System.out.println("WordState getAction " + original + " to " + state + " legal.");
                }
            }
        }
        state = original;
        return newActions;   
    }

    public Object clone(){
        return new WordState(this.state);
    }

    public String getState(){return state;}

    public void setState(String s){this.state = s;}

}
