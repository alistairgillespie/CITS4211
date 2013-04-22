package search;

import search.*;
import wordPlayer.*;
import wordChess.*;
/**
 * Write a description of class IterativeDeepeningSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IterativeDeepeningSearch
{
    private DepthLimitedSearch dls;
    private State startState;
    private NodeInfo nodeInfo;
    private double maxDepth;
    
    public IterativeDeepeningSearch(String start, String finish, double maxDepth){
        this.startState = new WordState(start);
        this.nodeInfo = new WordNodeInfo(finish, maxDepth);
        this.maxDepth = maxDepth;
    }
    
    public Node search(){
        double depth = 0;
        Node answer = null;
        while(answer == null && depth <= maxDepth){
            System.out.println("Searching at a limit of depth " + depth);
            this.dls = new DepthLimitedSearch(this.startState, this.nodeInfo, depth);
            answer = dls.search();
            depth++;
        }
        return answer;
    }
}
