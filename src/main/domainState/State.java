package main.domainState;

public interface State extends Comparable<String> {
    String getState();
    boolean isTerminated();


    State clone();
}
