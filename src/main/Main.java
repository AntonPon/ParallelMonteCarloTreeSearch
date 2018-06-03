package main;

import main.domainAction.ActionImpl;
import main.domainState.StateImpl;

public class Main {

    public static void main(String ... args){
        Game game = new Game(16);
        DecisionTree dt = new DecisionTree(new ActionImpl("some step"), new StateImpl("some stage 15"),  game);
        System.out.println(dt.getBestMove());
    }
}
