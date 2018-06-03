package main.simulation;

import main.Game;
import main.GameNode;

public interface Simulation extends Runnable{


    void simulate(GameNode startNode, Game game);


}
