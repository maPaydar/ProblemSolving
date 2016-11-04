package ir.amin.search;

import ir.amin.problemsolve.State;

import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public interface ISuccessor {

    public List<State> successor(State currentState);
}
