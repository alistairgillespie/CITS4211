package search;


/**
 * Write a description of class DepthLimitedSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthLimitedSearch extends GeneralSearch
{
    double maxDepth;
    public DepthLimitedSearch(State startState, NodeInfo nodeInfo, double maxDepth){
        super(startState, nodeInfo);
        this.maxDepth = maxDepth;
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
    
    public double getMaxDepth(){return maxDepth;}
}
