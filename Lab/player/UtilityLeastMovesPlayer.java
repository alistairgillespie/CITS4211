package player;
import mixmeta4.*;
import agent.*;
import search.*;
import java.util.*;

public class UtilityLeastMovesPlayer extends Player implements Agent {

   public UtilityLeastMovesPlayer (boolean isRed) {
     super(isRed,"UtilityLeastMovesPlayer");
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
         
         int utility = -1*test.getActions().size();
         if (utility > bestUtility || bestUtility == 0){
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
