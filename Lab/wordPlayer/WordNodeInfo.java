package wordPlayer;

import search.*;


/**
 * Write a description of class WordNodeInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordNodeInfo implements NodeInfo
{
    private String wordGoal;
    private double depthLimit;
    
    public WordNodeInfo(String goal, double depthLimit){
        this.wordGoal = goal;
        this.depthLimit = depthLimit;
    }
    public WordNodeInfo(String goal){
        this(goal, -1);
    }
    
    /**
     * @return true if this node is a goal node.
     */
    public boolean isGoal (Node node){
        State state = node.getState();
        if (state instanceof WordState){
            WordState wordstate = (WordState) state;
            if (this.wordGoal.compareTo(wordstate.getState()) == 0) return true;
        }
        return false;
    }

    /**
     * @return true in cases where the search should terminate
     */
    public boolean isTerminal (Node node){
        return node.getCost() > depthLimit && depthLimit != -1;
    }

    /**
     * @return the utility or estimated utility (evalutation function) of the node
     */ 
    public double utility (Node node){
        return -1;
    }

    /**
     * Set the depth/cost limit for depth limited and iterative deepending searches.
     * @param limit the depth limit
     */
    public void setDepthLimit (double limit){
        this.depthLimit = limit;
    }

    /**
     * Get the depth/cost limit for depth limited and iterative deepening searches.
     * @return the depth limit
     */
    public double getDepthLimit (){
        return depthLimit;
    }
}
