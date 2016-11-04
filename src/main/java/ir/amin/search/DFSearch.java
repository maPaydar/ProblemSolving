package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ir.amin on 10/26/16.
 */
public class DFSearch extends Search {

    private Stack<State> stack;
    private List<State> visitedStates;


    public DFSearch(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
        this.stack = new Stack<>();
        this.visitedStates = new ArrayList<>();
    }

    @Override
    public List<State> run() {
        this.stack.push(super.getInitialState());
        while (this.stack.size() > 0) {
            super.setCurrentState(this.stack.pop());
            System.out.println(super.getCurrentState().getValue());
            if (this.visitedStates.contains(super.getCurrentState())) continue;
            this.visitedStates.add(super.getCurrentState());
            super.getPathToGoal().add(super.getCurrentState());
            if (super.getGoalFormul().isGoal(super.getCurrentState())) {
                return super.getPathToGoal();
            }
            for (Object nextState : super.getCurrentState().getNextStates()) {
                this.stack.push((State) nextState);
            }
        }
        return null;
    }

}
