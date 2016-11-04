package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class HCRandomRestart extends HillClimbSearch {

    public HCRandomRestart(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
    }

    public HCRandomRestart(State initialState, GoalFormul goalFormul, ICost iCostFunction) {
        super(initialState, goalFormul, iCostFunction);
    }

    public HCRandomRestart(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor) {
        super(initialState, goalFormul, iCostFunction, iSuccessor);
    }

    @Override
    public List<State> run() {
        List<State> successor = null;
        State nextState = null;
        List<State> nextStates = new ArrayList<>();
        while (this.getCostFunction().costFunction(this.getCurrentState()) != 0) {
            successor = this.getSuccessor().successor(this.getCurrentState());
            nextState = successor.get(0);
            if(this.getCostFunction().costFunction(nextState) < this.getCostFunction().costFunction(this.getCurrentState())) {
                nextStates.add(nextState);
                this.setCurrentState(nextState);
            }
        }
        return nextStates;
    }
}
