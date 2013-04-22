package wordChess;

import agent.*;
import com.swabunga.spell.event.*;
import com.swabunga.spell.engine.*;
import java.io.File;
/**
 * Write a description of class WordPuzzle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPuzzle implements Environment
{
    private StringBuffer puzzle;
    private SnapShot snapshot;

    public WordPuzzle(String s){
        this.puzzle = new StringBuffer(s);
    }

    public WordPuzzle clone(){
        return this.clone();
    }

    public StringBuffer getStringBuffer(){ return this.puzzle;}

    public String toString(){return puzzle.toString();}

    /** Compares the current puzzle snapshot with the String passed as an argument.
     * 
     *  Returns -1 if the SnapShot String is lexicographically before the argument, 1 if it is 
     *  lexographically after the argument, and 0 if they are equivalent.
     */
    public int equals(String s){
        String pString = this.getPercept().getString();
        return pString.compareToIgnoreCase(s);        
    }

    public SnapShot getPercept(){return this.snapshot;}

    public void setPercept(SnapShot s){this.snapshot = s;}

    public void update(Action step){
        Step s = (Step) step;
        if (s.getPosition() > puzzle.length() - 1) return;
        char[] current = this.getPercept().getString().toCharArray();
        current[s.getPosition()] = s.getTo();
        String modified = new String(current);
        this.getPercept().setPuzzle(modified);
        try {
            SpellDictionary dictionary = new SpellDictionaryHashMap(new File("english.0"), null);
            SpellChecker spellCheck = new SpellChecker(dictionary);
            int result = spellCheck.checkSpelling(new StringWordTokenizer(modified.toLowerCase()));
            if (result == SpellChecker.SPELLCHECK_OK) System.out.println(modified);
            //else System.out.println("Invalid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(this.getPercept().getString());
    }
}
