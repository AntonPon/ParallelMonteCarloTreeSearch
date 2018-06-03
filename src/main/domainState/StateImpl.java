package main.domainState;

public class StateImpl implements State<String> {

    private String state;

    public StateImpl(String state){
        this.state = state;
    }

    @Override
    public String getState(){
        return state;
    }

    @Override
    public boolean isTerminated() {
        return this.state.contains("terminated");
    }

    @Override
    public String toString(){
        return "The state is " + state;
    }

    @Override
    public int compareTo(String s) {
        return this.state.compareTo(s);
    }
}
