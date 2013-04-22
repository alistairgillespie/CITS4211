package player;
import mixmeta4.*;
import agent.*;
import search.*;
import java.util.*;

public class ReflexPlayer extends Player implements Agent {

   public ReflexPlayer (boolean isRed) {
     super(isRed,"ReflexPlayer");
   }

   public Action getAction (Percept percept) {
     Board board = (Board) percept;
     Actions moves = board.getActions();
     
     Random r = new Random();
     return (Action)moves.get(r.nextInt(moves.size()));
   }
   
}
