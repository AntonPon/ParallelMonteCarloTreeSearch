package main;

import main.domainAction.Action;
import main.domainState.State;

import java.util.ArrayList;
import java.util.List;

public class GameNode {
    private Action action;
    private State state;
    private int winVisit;
    private int totalVisit;
    private GameNode father;
    private List<GameNode> sons;

    public State getState(){
        return state;
    }

    public boolean isLeaf(){
        return sons.size() == 0;
    }


    public Action getAction(){
        return action;
    }

    public GameNode getFather(){
        return father;
    }

    public GameNode(Action action, State state, GameNode father){
        this.action =  action;
        this.state = state;
        this.father = father;
        this.sons = new ArrayList();
    }

    public boolean isRoot(){
        return father == null;
    }


    public void update(int visited, int winVisited){
        this.winVisit += winVisited;
        this.totalVisit += visited;
    }

    public int getWinVisit(){
        return this.winVisit;
    }

    public int getTotalVisit(){
        return this.totalVisit;
    }

    public double getWinnerRatio(){
        return  winVisit/(double) totalVisit;
    }

    public boolean containSons(State state) {
        if (sons.size() > 0){
            for (GameNode node: sons){
                if(node.getState().compareTo(state) == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public void addSons(State state, Action action){
        sons.add(new GameNode(action, state, this));
    }

    public List<GameNode> getSons(){
        return sons;
    }

    @Override
    public String toString(){
        return "total visits: " + getTotalVisit() + " wins visits" + getWinVisit();
    }

}
