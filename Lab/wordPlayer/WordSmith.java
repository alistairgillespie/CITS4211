package wordPlayer;

import agent.*;
import search.*;
import wordPlayer.*;
import wordChess.*;
import com.swabunga.spell.event.*;
import com.swabunga.spell.engine.*;
import java.io.File;
/**
 * Write a description of class WordSmith here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordSmith implements Agent
{
    private SpellDictionary dictionary;
    private SpellChecker spellCheck;

    public WordSmith(){
        try {
            SpellDictionary dictionary = new SpellDictionaryHashMap(new File("english.0"), null);
            SpellChecker spellCheck = new SpellChecker(dictionary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isWord(String s){
        int result = spellCheck.checkSpelling(new StringWordTokenizer(s.toLowerCase()));
        if (result == SpellChecker.SPELLCHECK_OK) return true;
        else return false;
    }

    public Action getAction(Percept percept){
        SnapShot p = (SnapShot) percept;
        int x = p.cycledChar();        
        char[] current = p.getString().toCharArray(); 
        if (x >= current.length) return null;
        current[x] = (char)(current[x] + p.getTimesCycled());
        if (current[x] > 90) current[x] = (char)(current[x] - 26);
        return new Step(x, current[x]);
    }

    public Node depthFirstSearch(String start, String goal){        
        WordState startState = new WordState(start);
        WordNodeInfo nodeInfo = new WordNodeInfo(goal);
        DepthFirstSearch dfs = new DepthFirstSearch(startState, nodeInfo);
        return dfs.search();
    }

    public Node breadthFirstSearch(String start, String goal){        
        WordState startState = new WordState(start);
        WordNodeInfo nodeInfo = new WordNodeInfo(goal);
        BreadthFirstSearch bfs = new BreadthFirstSearch(startState, nodeInfo);
        return bfs.search();
    }

    public Node depthLimitedSearch(String start, String goal, double maxDepth){        
        WordState startState = new WordState(start);
        WordNodeInfo nodeInfo = new WordNodeInfo(goal, maxDepth);
        DepthLimitedSearch dls = new DepthLimitedSearch(startState, nodeInfo, maxDepth);
        return dls.search();
    }

}
