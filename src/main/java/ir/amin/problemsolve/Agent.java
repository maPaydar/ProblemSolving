package ir.amin.problemsolve;

import ir.amin.search.DFSearch;
import ir.amin.search.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 03/11/2016.
 */
public class Agent<T> {

    private List<State> sequence = new ArrayList();
    private List<Action> actions = new ArrayList();
    private State initialState;
    private State goalState;
    private GoalFormul goalFormul;
    private Problem problem;

    public Agent(State<T> initialState, GoalFormul<T> goalFormul) {
        this.initialState = initialState;
        this.goalFormul = goalFormul;
    }

    public Action act(Percept percept) {
        Search search = new DFSearch(this.initialState, this.goalFormul);
        this.sequence = search.run();
        if(this.sequence == null)
            return null;
        // else : when is a solution to goal
        return new Action();
    }
}
