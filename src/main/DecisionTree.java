package main;

import main.domainAction.Action;
import main.domainState.State;

import java.lang.management.GarbageCollectorMXBean;

public class DecisionTree {

    private GameNode head;
    private Game game;
    private int totalSimulationNumber;
    private static final int VISITS_RATIO  = 4;
    private static final int NUMBER_OF_SEARCHING_LOOPS = 4;


    public DecisionTree(Action action, State state, Game game){
        this.head = new GameNode(action, state, null);
        this.game = game;
        this.totalSimulationNumber = 0;
    }

    public Action getBestMove(){
        // for simplicity, my restrictions for searching the best way will be 4 loops of simulations
        for (int i = 0; i < NUMBER_OF_SEARCHING_LOOPS; i++){
            GameNode startSimuation = getSimulationStartNode();
            simulations(startSimuation);
        }
        return getNextBestNode(head).getAction();
    }

    private void simulations(GameNode start){
        int coreNumber = Runtime.getRuntime().availableProcessors();
    }


    private GameNode getSimulationStartNode(){
        GameNode result = head;
        while (!result.isLeaf()){
            result = getNextBestNode(result);
        }
        if (result.getTotalVisit() >= VISITS_RATIO){
            for(Action action: game.getActions(result.getState())){
                result.addSons( game.getState(result.getState(), action), action);
            }
            result = getNextBestNode(result);
        }
        return result;
    }

    private GameNode getNextBestNode(GameNode prev){
        if (prev.getState().isTerminated() || prev.isLeaf()){
            return prev;
        }
        GameNode result = null;
        for (GameNode node: prev.getSons()){
            if (result == null){
                result = node;
            }else{
                if(ucb(result) < ucb(node)){
                    result = node;
                }
            }
        }

        return result;
    }

    private double ucb(GameNode current){
        double c = Math.sqrt(2);
        if (current.getTotalVisit() == 0){
            return Double.MAX_VALUE;
        }
        return current.getWinnerRatio() + c * Math.sqrt(Math.log(totalSimulationNumber)/current.getTotalVisit());
    }

    private void updateWeights(int wins, int totalRuns, GameNode leafNode){
        GameNode current = leafNode;
        while(!current.isRoot()){
            current.update(totalRuns, wins);
            current = current.getFather();
        }
    }



}
