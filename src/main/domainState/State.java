package main.domainState;

public interface State<E> extends Comparable<E> {
    E getState();
    boolean isTerminated();
}
