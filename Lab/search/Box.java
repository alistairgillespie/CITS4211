package search;

import java.util.*;
public  class Box{
        String name;
        double value;
        ArrayList<Box> children;
        boolean isTerminal;
        
        public Box(String name, double value, Box a, Box b, boolean terminal){
            this.name = name;
            this.value = value;
            this.children = new ArrayList<Box>();
            if (a != null) children.add(a);
            if (b != null) children.add(b);
            this.isTerminal = terminal;
        }
        
        public String getName(){return name;}
        public Double getValue(){return value;}
        public ArrayList getChildren(){return children;}
        public boolean isTerminal(){return isTerminal;}
        public void addChild(Box a){children.add(a);}
    }