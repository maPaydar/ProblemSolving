package ir.amin.search;

import ir.amin.problemsolve.State;

/**
 * Created by Amin on 04/11/2016.
 */
public interface ISuccessor {

    public State successor(State currentState);
}
