import java.lang.RuntimeException;
import agent.*;
import wordChess.*;
import wordPlayer.*;
import search.*;
/**
 * Write a description of class RunPuzzle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RunPuzzle
{
    public static void main(String[] args) throws RuntimeException{
        if (args.length != 2) {
            throw new RuntimeException("Two arguments expected: starting word (1) and target word (2).");
        }
        String start = args[0].toUpperCase();
        String finish = args[1].toUpperCase();
        System.out.println("Let's play Word Chess!");
        System.out.println("Change "+ start + " to " + finish + ".\n");
        long starttime = System.currentTimeMillis();

        /*WordPuzzle wordpuzzle = new WordPuzzle(args[0].toUpperCase());
        WordSmith wordsmith = new WordSmith();
        SnapShot snapshot = new SnapShot(wordpuzzle);
        wordpuzzle.setPercept(snapshot);
        String target = args[1].toUpperCase();
        boolean found = false;

        while(!found && !snapshot.isComplete()){
        wordpuzzle.update(wordsmith.getAction(snapshot));
        if (wordpuzzle.equals(target) == 0) found = true;
        }
        if (found) System.out.println("Solution found!");
        else System.out.println("Solution not found.");
         */
        
        WordState startstate = new WordState(start);
        WordNodeInfo wni = new WordNodeInfo(finish);
        //WordSmith smith = new WordSmith();
        //Node answer = smith.depthFirstSearch(start, finish);
        
        //IterativeDeepeningSearch its = new IterativeDeepeningSearch(start, finish, 4);
        //Node answer = its.search();
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(startstate, wni);
        Node answer = bfs.search();
        if (answer == null) System.out.println("Solution not found after "+ ((System.currentTimeMillis() - starttime) / 1000) + "s.");
        else {
            System.out.println("Solution found in "+ ((System.currentTimeMillis() - starttime) / 1000) + "s.");
            Object[] path = answer.getPath().toArray();
            WordState state = new WordState(start);
            System.out.print(start);
            for (int i = 0; i < path.length; i++) {
                state.update((Step)path[i]);
                System.out.print(" > " + state.getState());
            }
            System.out.println("\n");
        }

    }
}
