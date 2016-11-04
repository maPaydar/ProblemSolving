package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public abstract class Search {

    private State initialState;
    private GoalFormul goalFormul;
    private State currentState;
    private List<State> pathToGoal;

    public Search(State initialState, GoalFormul goalFormul) {
        this.initialState = initialState;
        this.currentState = initialState;
        this.goalFormul = goalFormul;
        this.pathToGoal = new ArrayList<>();
    }

    public GoalFormul getGoalFormul() {
        return goalFormul;
    }

    public List<State> getPathToGoal() {
        return pathToGoal;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public abstract List<State> run();

    public abstract boolean isSuccessFul(List<State> nextStates);
}
