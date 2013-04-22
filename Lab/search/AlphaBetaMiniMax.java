package search;

import agent.*;
import java.util.*;
import mixmeta4.*;

public class AlphaBetaMiniMax {

    NodeInfo nodeInfo;

    public AlphaBetaMiniMax (NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public double alphaBetaMax (Node visit, double alpha, double beta, int d) {
        ListIterator li;
        Action arc;
        Node child;
        if (nodeInfo.isTerminal(visit)) {
            return nodeInfo.utility(visit);
        } else {
            li = visit.getState().getActions().listIterator();
            while (li.hasNext() && alpha < beta) {
                arc = (Action)li.next();
                child = (Node)visit.clone();
                child.update(arc);
                alpha = Math.max(alpha, alphaBetaMin(child, alpha, beta, d+1));
                //for (int i = 0; i < d; i++) System.out.print("\t");//
                //System.out.println(alpha+": " + ((Move)arc).toString());//
            }
            return alpha;                
        }
    }

    public double alphaBetaMin (Node visit, double alpha, double beta, int d) {
        ListIterator li;
        Action arc;
        Node child;
        if (nodeInfo.isTerminal(visit)) {
            return nodeInfo.utility(visit);
        } else {
            li = visit.getState().getActions().listIterator();
            while (li.hasNext() && alpha < beta) {
                arc = (Action)li.next();
                child = (Node)visit.clone();
                child.update(arc);
                beta = Math.min(beta, alphaBetaMax(child, alpha, beta, d+1));
                //for (int i = 0; i < d; i++) System.out.print("\t");//
                //System.out.println(beta+": " + ((Move)arc).toString());//
            }
            return beta;                
        }
    }
}

