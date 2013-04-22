package player;
import mixmeta4.*;
import agent.*;
import search.*;
import java.util.*;

public class MiniMaxPlayer extends Player implements Agent{

    public MiniMaxPlayer (boolean isRed) {
        super(isRed,"MiniMaxPlayer");
    }

    public Action getAction (Percept percept){
        System.out.print("Thinking... ");
        Board board = (Board) percept;
        Actions moves = board.getActions(); 
        Node stateBoard = new Node(board);
        ListIterator li = moves.listIterator();
        ChexNodeInfo nodeInfo = new ChexNodeInfo(4, isRed);
        AlphaBetaMiniMax ab = new AlphaBetaMiniMax(nodeInfo);
        Action arc;
        Actions best = new Actions();
        Node child;
        
        if(nodeInfo.max()){
            double max = Double.NEGATIVE_INFINITY;
            while (li.hasNext()) {
                arc = (Action)li.next();
                child = (Node)stateBoard.clone();
                child.update(arc);
                double x = ab.alphaBetaMin(child, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
                //System.out.println(x+": " + ((Move)arc).toString());
                if (x > max){
                    best = new Actions();
                    best.add(arc);
                    max = x;
                } else if (x == max) best.add(arc);
            }
        } else{
            double min = Double.POSITIVE_INFINITY;
            while (li.hasNext()) {
                arc = (Action)li.next();
                child = (Node)stateBoard.clone();
                child.update(arc);
                double x = ab.alphaBetaMax(child, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
                //System.out.println(x+": " + ((Move)arc).toString());
                if (x < min){
                    best = new Actions();
                    best.add(arc);
                    min = x;
                } else if (x == min) best.add(arc);
            }
            
        }
        Random r = new Random();
        Move result = (Move)best.get(r.nextInt(best.size()));
        System.out.println("MOVE: " + (result).toString()+"\n");
        return result;
    }

}