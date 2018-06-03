package main;

import main.domainAction.Action;
import main.domainAction.ActionImpl;
import main.domainState.State;
import main.domainState.StateImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private int terminateSteps;
    private int currentIteration;
    private List<String> moves;
    private Random random;

    public Game(int terminateSteps){
        moves = new ArrayList();
        moves.add("move right");
        moves.add("move left");
        moves.add("move back");
        moves.add("move straight");
        moves.add("cross the road");
        moves.add("jump up");
        moves.add("sit down");

        this.terminateSteps = terminateSteps;
        random = new Random();
    }

    public Game(List<String> moves, int currentItertion, int terminateSteps, Random random){
        this.moves = moves;
        this.currentIteration = currentItertion;
        this.terminateSteps = terminateSteps;
        this.random = random;
    }



    public List<Action> getActions(State state){
        List<Action> actions = new ArrayList<>();
        Collections.shuffle(moves);
        actions.add(new ActionImpl(moves.get(0)));
        actions.add(new ActionImpl(moves.get(1)));
        actions.add(new ActionImpl(moves.get(2)));
        return actions;
    }

    public State getState(State state,  Action action){
        currentIteration ++;
        System.out.println("state number " + currentIteration);
        int currentNumber = new Integer(state.getState().replaceAll("\\D+", ""));

        if (isTerminated()){
            int resultGame  = 0;
            if (currentNumber >= 0){
                resultGame = 1;
            }
            return new StateImpl("terminated" + resultGame);
        }
        currentNumber = (int) Math.pow(-1, random.nextInt(2)) * (currentNumber + random.nextInt(30));
        return new StateImpl("not final state, points = " + currentNumber);
    }

    public boolean isTerminated(){
        return currentIteration >= terminateSteps;
    }

    public int getPrice(State currentState) {
        return new Integer(currentState.getState().replaceAll("\\D+", ""));
    }

    @Override
    public Game clone(){
        return new Game(moves, currentIteration, terminateSteps, random);
    }

}
