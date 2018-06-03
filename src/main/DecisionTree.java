package main;

import main.domainAction.Action;

public class DecisionTree {

    private GameNode head;
    private static final int VISITS_RATIO  = 4;


    public DecisionTree(GameNode head){
        this.head = head;

    }

    public Action getBestMove(){
        // TODO implement getting best move
        return null;
    }

    private void simulations(GameNode start){
        // TODO implement code paralelization simulation
    }


    private GameNode getSimulationStartNode(){
        // TODO should return starting point
        return null;
    }

    private double UCB(GameNode current){
        // TODO implement function for chosing the way
        return 0;
    }

    private void updateWeights(int wins, int totalRuns){
        // TODO implement wight updating for gameTree
    }

    

}
