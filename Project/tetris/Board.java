package tetris;

import agent.*;
import search.*;

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board implements Environment, Percept, State
{
    public Object clone(){return new Board();}

    public Percept getPercept(){return this;}

    public void update(Action action){
    }

    public Actions getActions(){
        return null;
    }
}
