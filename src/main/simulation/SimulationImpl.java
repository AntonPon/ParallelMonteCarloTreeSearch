package main.simulation;

import main.Game;
import main.GameNode;
import main.SimulationResult;
import main.domainAction.Action;
import main.domainState.State;

public class SimulationImpl implements Simulation {


    private State start;
    private Game game;
    public SimulationImpl(State start, Game game) {
        this.game = game;
        this.start = start;
    }


    private int simulate(State startState, Game game) {
        System.out.println("actual simulation");
        State currentState = startState;
        while(!currentState.isTerminated()){
            Action action = null;
            currentState = game.getState(currentState, action);
            System.out.println(currentState);
        }
        return game.getPrice(currentState);
    }

    @Override
    public SimulationResult call() throws Exception {
        return new SimulationResult(start, simulate(start, game));
    }
}
