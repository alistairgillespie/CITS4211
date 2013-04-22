package player;

import mixmeta4.*;
import agent.*;
import search.*;
import java.util.*;
import java.io.*;

/**
 * Write a description of class ChexNodeInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChexNodeInfo implements NodeInfo
{
    private boolean max = true;
    private double depthLimit = -1;
    private boolean playerIsRed;
    private double maxPieces;

    public ChexNodeInfo(double limit, boolean playerIsRed){
        setDepthLimit(limit);
        this.playerIsRed = playerIsRed;

        String cfg = "config.txt";
        try {
            BufferedReader in = new BufferedReader( new FileReader(cfg) );
            String setup = null;
            String s;
            while( (s=in.readLine()) != null ) {
                StringTokenizer token = new StringTokenizer( s, ":" );
                String name = token.nextToken();
                if( name.trim().toUpperCase().equals("SETUP") ) {
                    setup = "";
                    while( (s=in.readLine()) != null ) {
                        String str = (new StringTokenizer(s,":")).nextToken().trim();
                        if( str.toUpperCase().equals("SETUP") )
                            break;
                        setup += str + "\n";
                    }
                }
            }
            if( setup == null ) {
                this.maxPieces = -1;
            } else {
                this.maxPieces = 0;
                char[] array = setup.toCharArray();
                for(int i = 0; i < array.length; i++) {
                    if(Character.isLetter(array[i])) maxPieces++;
                }
                //System.out.println("Max pieces: " + maxPieces);
            }
        }
        catch (Exception e) {}

    }

    public boolean max(){return max;}

    /**
     * @return true if this node is a goal node.
     */
    public boolean isGoal (Node node){
        return false;
    }

    /**
     * @return true in cases where the search should terminate
     */
    public boolean isTerminal (Node node){
        if (this.depthLimit != -1 && node.getPath().size() >= this.depthLimit) return true;

        Board board = (Board)node.getState();
        return (board.redKingTaken() || board.blackKingTaken());
    }

    /**
     * @return the utility or estimated utility (evalutation function) of the node
     */ 
    public double utility (Node node){
        //Maximising utility
        Board board = (Board)node.getState();
        boolean redToMove = board.redToMove();
        PieceSet red = board.getRedPieces();
        PieceSet black = board.getBlackPieces();/*
        if ((playerIsRed && board.redKingTaken()) || (!playerIsRed && board.blackKingTaken())){
        return Double.NEGATIVE_INFINITY;
        }
        if ((playerIsRed && board.blackKingTaken()) || (!playerIsRed && board.redKingTaken())){
        return Double.POSITIVE_INFINITY;
        }*/

        double pieceUtility =
        10 * (red.numberOfEggs() - black.numberOfEggs()) +
        10 * (red.numberOfPods() - black.numberOfPods()) +
        30 * (red.numberOfKnights() - black.numberOfKnights()) +
        50 * (red.numberOfWinthrops() - black.numberOfWinthrops()) +
        80 * (red.numberOfFroggers() - black.numberOfFroggers()) +
        80 * (red.numberOfGalaxians() - black.numberOfGalaxians()) +
        90 * (red.numberOfQueens() - black.numberOfQueens()) +
        10000 * (red.numberOfKings() - black.numberOfKings());

        /*double pieceUtility;
        if (playerIsRed) {
            pieceUtility = 
            10 * (red.numberOfEggs() - 0.95*black.numberOfEggs()) +
            10 * (red.numberOfPods() - 0.95*black.numberOfPods()) +
            30 * (red.numberOfKnights() - 0.95*black.numberOfKnights()) +
            50 * (red.numberOfWinthrops() - 0.95*black.numberOfWinthrops()) +
            80 * (red.numberOfFroggers() - 0.95*black.numberOfFroggers()) +
            80 * (red.numberOfGalaxians() - 0.95*black.numberOfGalaxians()) +
            90 * (red.numberOfQueens() - 0.95*black.numberOfQueens()) +
            10000 * (red.numberOfKings() - 0.95*black.numberOfKings());
        } else {
            pieceUtility = 
            10 * (0.95*red.numberOfEggs() - black.numberOfEggs()) +
            10 * (0.95*red.numberOfPods() - black.numberOfPods()) +
            30 * (0.95*red.numberOfKnights() - black.numberOfKnights()) +
            50 * (0.95*red.numberOfWinthrops() - black.numberOfWinthrops()) +
            80 * (0.95*red.numberOfFroggers() - black.numberOfFroggers()) +
            80 * (0.95*red.numberOfGalaxians() - black.numberOfGalaxians()) +
            90 * (0.95*red.numberOfQueens() - black.numberOfQueens()) +
            10000 * (0.95*red.numberOfKings() - black.numberOfKings());
        }*/

        double utility;
        if (maxPieces != -1){
            Actions moves = board.getActions();
            int redMoves, blackMoves;
            if (redToMove) {
                redMoves = moves.size();
                board.update(null);
                blackMoves = board.getActions().size();
            } else {
                blackMoves = moves.size();
                board.update(null);
                redMoves = board.getActions().size();
            }
            double moveUtility = redMoves - blackMoves;

            double pieces = 
                (red.numberOfEggs() + black.numberOfEggs()) +
                (red.numberOfPods() + black.numberOfPods()) +
                (red.numberOfKnights() + black.numberOfKnights()) +
                (red.numberOfWinthrops() + black.numberOfWinthrops()) +
                (red.numberOfFroggers() + black.numberOfFroggers()) +
                (red.numberOfGalaxians() + black.numberOfGalaxians()) +
                (red.numberOfQueens() + black.numberOfQueens()) +
                (red.numberOfKings() + black.numberOfKings());

            utility = ((pieces / maxPieces) * pieceUtility) + 
            (((maxPieces - pieces) / maxPieces) * moveUtility);
        } else {
            utility = pieceUtility;
        }


        if (playerIsRed) return utility;
        else return -1 * utility;

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
        return this.depthLimit;
    }
}
