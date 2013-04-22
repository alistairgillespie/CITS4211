package search;

import search.*;
import wordPlayer.*;
import wordChess.*;

/**
 * Write a description of class DepthFirstSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFirstSearch extends GeneralSearch
{

    public DepthFirstSearch(State startState, NodeInfo nodeInfo){
        super(startState, nodeInfo);
    }

    /**
     * Selects the Node at the far right of the unvisited List - those most recently added
     */
    public Node select (){
        return this.unvisited.get(this.unvisited.size() - 1);
    }

    /**
     * Insert new Nodes on the far right end of the unvisited List.
     */
    public void insert (Node node){
        this.unvisited.add(node);
    }
}
