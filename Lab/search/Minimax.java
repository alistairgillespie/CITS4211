package search;

import agent.*;
import java.util.*;

/**
 * This file contains a template for getting started writing a minimax 
 * search algorithm. Before editing the class it should first be
 * written to a file called Minimax.java.
 * 
 * It requires an application specific NodeInfo object which tailors the
 * search to the particular application - see the NodeInfo documentation
 * for more details.
 * 
 * Note that the algorithm is recursive, with maxValue calling minValue,
 * and vice versa.
 *
 * @author Cara MacNish
 */

public class Minimax {

    NodeInfo nodeInfo;

    public Minimax (NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    /**
     * @return the highest value Max can achieve at this node with optimal play
     */
    public double maxValue (Node visit) {
        //double maxSoFar = Double.NEGATIVE_INFINITY;
        ListIterator li;
        Action arc;
        Node child = null;
        double childValue;
        if (nodeInfo.isTerminal(visit)) {
            return nodeInfo.utility(visit);
        }
        else {
            li = visit.getState().getActions().listIterator();
            childValue = Double.POSITIVE_INFINITY;
            while (li.hasNext()) {
                arc = (Action)li.next();
                Node clone = (Node)visit.clone();
                clone.update(arc);
                double cloneMin = minValue(child);
                if (cloneMin < childValue){
                    child = clone;
                    childValue = cloneMin;
                }
            }
            if (child == null) return nodeInfo.utility(visit);
            else return maxValue(child);
        }
    }

    /**
     * @return the lowest value Min can achieve at this node with optimal play
     */
    public double minValue (Node visit) {
        //double minSoFar = Double.POSITIVE_INFINITY;
        ListIterator li;
        Action arc;
        Node child = null;
        double childValue;
        if (nodeInfo.isTerminal(visit)) {
            return nodeInfo.utility(visit);
        }
        else {
            li = visit.getState().getActions().listIterator();
            childValue = Double.NEGATIVE_INFINITY;
            while (li.hasNext()) {
                arc = (Action)li.next();
                Node clone = (Node)visit.clone();
                clone.update(arc);
                double cloneMax = maxValue(clone);
                if (cloneMax < childValue){
                    child = clone;
                    childValue = cloneMax;
                }
            }
            if (child == null) return nodeInfo.utility(visit);
            else return minValue(child);
        }
    }
}
