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
        while (getCostFunction().costFunction(getCurrentState()) != 0) {
            successor = getSuccessor().successor(getCurrentState());
            nextState = successor.get(0);
            if(getCostFunction().costFunction(nextState) < getCostFunction().costFunction(getCurrentState())) {
                nextStates.add(nextState);
                setCurrentState(nextState);
            }
        }
        return nextStates;
    }
}
