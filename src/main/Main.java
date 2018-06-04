package main;

import main.domainAction.ActionImpl;
import main.domainState.StateImpl;
import main.game.Game;

public class Main {

    public static void main(String ... args){
        Game game = new Game(16);
        DecisionTree dt = new DecisionTree(new ActionImpl("some step"), new StateImpl("some stage 15"),  game);
        GameNode gm = dt.getBestMove();
        System.out.println(gm);
        System.out.println("------------------------");
        GameNode father = gm.getFather();
        for (GameNode son: father.getSons()){
            System.out.println(son);
        }
    }
}
