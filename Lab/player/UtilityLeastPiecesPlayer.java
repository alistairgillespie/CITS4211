package player;
import mixmeta4.*;
import agent.*;
import search.*;
import java.util.*;

public class UtilityLeastPiecesPlayer extends Player implements Agent {

    public UtilityLeastPiecesPlayer (boolean isRed) {
        super(isRed,"UtilityLeastPiecesPlayer");
    }

    public Action getAction (Percept percept) {
        Board board = (Board) percept;
        Actions moves = board.getActions();

        int n = moves.size();
        Actions best = new Actions();
        int bestUtility = 0;

        for (int x = 0; x < n; x++){
            Board test = (Board) board.clone();
            test.update((Action)moves.get(x));

            int utility = 0;
            PieceSet p;
            if (isRed) p = test.getBlackPieces();
            else p = test.getRedPieces();
            utility = p.numberOfCheckers() +
            p.numberOfEggs() +
            p.numberOfFroggers() +
            p.numberOfGalaxians() +
            p.numberOfKings() +
            p.numberOfKnights() +
            p.numberOfPods() +
            p.numberOfQueens() +
            p.numberOfWinthrops();
            
            if (utility < bestUtility || bestUtility == 0){
                best = new Actions();
                best.add((Action)moves.get(x));
                bestUtility = utility;
            } else if (utility == bestUtility) {
                best.add((Action)moves.get(x));
            }

        }
        Random r = new Random();
        return (Action)best.get(r.nextInt(best.size()));
    }

}
