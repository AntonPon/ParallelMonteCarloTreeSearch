package main.simulation;

import main.domainState.State;

public class SimulationResult {

    private State node;
    private int payOff;

    public SimulationResult(State node, int payOff){
        this.node = node;
        this.payOff = payOff;
    }

    public int getPayOff() {
        return payOff;
    }

    public State getNode() {

        return node;
    }
}
