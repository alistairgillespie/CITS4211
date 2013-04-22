package search;


/**
 * Write a description of class BreadthFirstSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BreadthFirstSearch extends GeneralSearch
{
   public BreadthFirstSearch(State startState, NodeInfo nodeInfo){
        super(startState, nodeInfo);
    }

   /**
     * Selects the Node at the far right of the unvisited List - opposite to those most recently added
     */
    public Node select (){
        return this.unvisited.get(this.unvisited.size() - 1);
    }

    /**
     * Insert new Nodes on the far left end of the unvisited List.
     */
    public void insert (Node node){
        this.unvisited.add(0, node);
    }
    
}
