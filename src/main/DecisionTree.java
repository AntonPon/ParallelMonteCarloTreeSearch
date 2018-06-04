package main;

import main.domainAction.Action;
import main.domainState.State;
import main.game.Game;
import main.simulation.SimulationImpl;
import main.simulation.SimulationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DecisionTree {

    private GameNode head;
    private Game game;
    private int totalSimulationNumber;
    private static final int VISITS_RATIO  = 4;
    private static final int NUMBER_OF_SEARCHING_LOOPS = 20;


    public DecisionTree(Action action, State state, Game game){
        this.head = new GameNode(action, state, null);
        this.game = game;
        this.totalSimulationNumber = 0;
    }

    public GameNode getBestMove(){
        // for simplicity, my restrictions for searching the best way will be 4 loops of simulations
        for (int i = 0; i < NUMBER_OF_SEARCHING_LOOPS; i++){
            GameNode startSimuation = getSimulationStartNode();
            simulations(startSimuation);
        }
        return getNextBestNode(head);
    }

    private void simulations(GameNode start){
        int coreNumber = Runtime.getRuntime().availableProcessors();
        List<Future<SimulationResult>> results = new ArrayList();
        ExecutorService executor = Executors.newFixedThreadPool(coreNumber);
        for (int i = 0; i < coreNumber; i++){
            results.add(executor.submit(new SimulationImpl(start.getState().clone(), game.clone())));
        }

        for (Future<SimulationResult> simulationResult: results){
            try {
                SimulationResult simRes = simulationResult.get();
                totalSimulationNumber++;
               // System.out.println("payoff " + simRes.getPayOff());
                updateWeights(simRes.getPayOff(), 1, start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }


    private GameNode getSimulationStartNode(){
        GameNode result = head;
        while (!result.isLeaf()){
            result = getNextBestNode(result);
        }
        if (result.isRoot() || result.getTotalVisit() >= VISITS_RATIO){
            for(Action action: game.getActions(result.getState())){
                result.addSons(game.getState(result.getState(), action), action);
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

            if (result == null || ucb(result) < ucb(node)){
                result = node;

            }
        }
        return result;
    }

    private double ucb(GameNode current){
        double c = Math.sqrt(2);
        if (current.getTotalVisit() == 0){
            return Double.POSITIVE_INFINITY;
        }
        return current.getWinnerRatio() + c * Math.sqrt(Math.log(totalSimulationNumber)/current.getTotalVisit());
    }

    private void updateWeights(int wins, int totalRuns, GameNode leafNode){
        GameNode current = leafNode;
        while(current != null){
            current.update(totalRuns, wins);
            current = current.getFather();
        }

    }



}
