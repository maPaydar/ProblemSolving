package ir.amin.search;

import ir.amin.problemsolve.State;

/**
 * Created by ir.amin on 11/1/16.
 */
public interface ICost<T> {
    public int costFunction(State<T> s);
}
