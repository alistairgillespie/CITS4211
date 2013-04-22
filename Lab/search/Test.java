package search;

//import agent.*;
import java.util.*;

public class Test {
    
    public static void main(){
        //Using the tree from http://www.cs.ucla.edu/~rosen/161/notes/alphabeta.html
        
        Test test = new Test();
        Random rand = new Random();
        int oo = rand.nextInt(50);
        int pp = rand.nextInt(50);
        int qq = rand.nextInt(50);
        int rr = rand.nextInt(50);
        int ss = rand.nextInt(50);
        int tt = rand.nextInt(50);
        int uu = rand.nextInt(50);
        int vv = rand.nextInt(50);
        int ww = rand.nextInt(50);
        int xx = rand.nextInt(50);
        int yy = rand.nextInt(50);
        int zz = rand.nextInt(50);
        
        Box o = new Box("O", oo, null, null, true);
        Box p = new Box("P", pp, null, null, true);
        Box q = new Box("Q", qq, null, null, true);
        Box r = new Box("R", rr, null, null, true);
        Box s = new Box("S", ss, null, null, true);
        Box t = new Box("T", tt, null, null, true);
        Box u = new Box("U", uu, null, null, true);
        Box v = new Box("V", vv, null, null, true);
        Box w = new Box("W", ww, null, null, true);
        Box x = new Box("X", xx, null, null, true);
        Box y = new Box("Y", yy, null, null, true);
        Box z = new Box("Z", zz, null, null, true);
        
        Box h = new Box("H", 0, o, p, false);
        Box i = new Box("I", 0, q, r, false);
        Box j = new Box("J", 0, s, null, false);
        Box k = new Box("K", 0, t, u, false);
        Box l = new Box("L", 0, v, w, false);
        Box m = new Box("M", 0, x, null, false);
        Box n = new Box("N", 0, y, z, false);
        
        Box d = new Box("D", 0, h, i, false);
        Box e = new Box("E", 0, j, k, false);
        Box f = new Box("F", 0, l, m, false);
        Box g = new Box("G", 0, n, null, false);
        
        Box b = new Box("B", 0, d, e, false);
        Box c = new Box("C", 0, f, g, false);
        
        Box a = new Box("A", 0, b, c, false);
        
        System.out.println("COMPLETE! With max = "+ test.alphaBeta(a, true));
        System.out.print(oo + " ");
        System.out.print(pp + " ");
        System.out.print(qq + " ");
        System.out.print(rr + " ");
        System.out.print(ss + " ");
        System.out.print(tt + " ");
        System.out.print(uu + " ");
        System.out.print(vv + " ");
        System.out.print(ww + " ");
        System.out.print(xx + " ");
        System.out.print(yy + " ");
        System.out.print(zz + " \n");
    }
    public double alphaBeta(Box visit, boolean isMax){
        if (isMax) {
            return alphaBetaMax(visit, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        } else {
            return alphaBetaMin(visit, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        } 
    }

    private double alphaBetaMax (Box visit, double alpha, double beta) {
        if (visit.isTerminal()) {
            System.out.println(visit.getName() + " is terminal with value " + visit.getValue());
            return visit.getValue();
        } else {
            ListIterator li = visit.getChildren().listIterator();
            while (li.hasNext() && alpha < beta) {
                Box child = (Box)li.next();
                System.out.println("Going down to check " + child.getName());
                double a = alphaBetaMin(child, alpha, beta);
                if (a > alpha){
                    alpha = a;
                    System.out.println("Updating alpha to " + alpha);
                }
            }
            if (alpha > beta){
                System.out.println("alpha " + alpha + " > beta " + beta);
            } else System.out.println(visit.getName() + " complete with value " + alpha);
            return alpha;                
        }
    }

    private double alphaBetaMin (Box visit, double alpha, double beta) {
        if (visit.isTerminal()) {
            System.out.println(visit.getName() + " is terminal with value " + visit.getValue());
            return visit.getValue();
        } else {
            ListIterator li = visit.getChildren().listIterator();
            while (li.hasNext() && alpha < beta) {
                Box child = (Box)li.next();
                System.out.println("Going down to check " + child.getName());
                double b = alphaBetaMax(child, alpha, beta);
                if (b < beta){
                    beta = b;
                    System.out.println("Updating beta to " + b);
                }
            }
            if (alpha > beta){
                System.out.println("alpha " + alpha + " > beta " + beta);
            } else System.out.println(visit.getName() + " complete with value " + beta);
            return beta;                
        }
    }
}

