package search;

//
//  GeneralSearch.skeleton
//  javaAgents
//
//  Created by Cara MacNish on 28/07/05.
//  Copyright (c) 2005 CSSE, UWA. All rights reserved.
//

import agent.*;
import java.util.*;
import search.*;
import wordChess.*;
import wordPlayer.*;
import com.swabunga.spell.event.*;
import com.swabunga.spell.engine.*;
import java.io.File;

public abstract class GeneralSearch {
    NodeInfo nodeInfo;
    ArrayList<Node> unvisited; 
    ArrayList<String> visited;

    private SpellDictionary dictionary;
    private SpellChecker spellCheck;

    public GeneralSearch (State startState, NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
        unvisited = new ArrayList<Node>();
        unvisited.add(new Node(startState, new Actions()));
        visited = new ArrayList<String>();

        try {
            this.dictionary = new SpellDictionaryHashMap(new File("english.0"), null);
            this.spellCheck = new SpellChecker(dictionary);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Node search() {
        while (!unvisited.isEmpty()){
            Node s = this.select();
            if (s == null) return null;
            unvisited.remove(s);
            //if (!visited.contains(s.getState())){
            String word = ((WordState)s.getState()).getState();
            int result = spellCheck.checkSpelling(new StringWordTokenizer(word.toLowerCase()));
            if (result == SpellChecker.SPELLCHECK_OK) {
                if (!visited.contains(((WordState)s.getState()).getState())){ 
                    //System.out.println(((WordState)s.getState()).getState() + " exists already.");
                    long begin = System.currentTimeMillis();
                    //String x = ((WordState)s.getState()).getState();
                    //System.out.println("Starting Node: " + x);
                    int expansions = 0;
                    if(nodeInfo.isGoal(s)) return s;
                    if(!nodeInfo.isTerminal(s)){
                        visited.add(((WordState)s.getState()).getState());
                        //visited.add(s);
                        Actions actions = s.getState().getActions();
                        for (int index = actions.size() - 1; index >= 0; index--){ 
                            // For loop from R to L of an Actions instance is more efficient as remaining
                            // Objects in the List don't need to be copied, as when looping from L to R 
                            Action a = (Action)actions.remove(index);
                            Node n = (Node)s.clone();
                            n.update(a); 
                            this.insert(n);
                            expansions++;
                        }
                    }
                    //System.out.println(expansions + " expansions complete after " + (System.currentTimeMillis()-begin)/1000 + "s.");
                }
            }
        }
        return null;
    }

    public abstract Node select ();

    public abstract void insert (Node node);
}
