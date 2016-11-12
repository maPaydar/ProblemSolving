package ir.amin.search;

import ir.amin.problems.toys.EightQueens;
import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class HCRandomRestart extends HillClimbSearch {

    private HCSteepestDescent hCSteepestDescent;

    public HCRandomRestart(State initialState, GoalFormul goalFormul, HCSteepestDescent hCSteepestDescent) {
        super(initialState, goalFormul);
        this.hCSteepestDescent = hCSteepestDescent;
    }

    public HCRandomRestart(State initialState, GoalFormul goalFormul, ICost iCostFunction, HCSteepestDescent hCSteepestDescent) {
        super(initialState, goalFormul, iCostFunction);
        this.hCSteepestDescent = hCSteepestDescent;
    }

    public HCRandomRestart(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor, HCSteepestDescent hCSteepestDescent) {
        super(initialState, goalFormul, iCostFunction, iSuccessor);
        this.hCSteepestDescent = hCSteepestDescent;
    }

    @Override
    public List<State> run() {
        List<State> successor = null;
        State nextState = null;
        List<State> nextStates = new ArrayList<>();
        while (getCostFunction().costFunction(getCurrentState()) != 0) {
            successor = this.hCSteepestDescent.run();
            if (!successor.isEmpty()) {
                nextState = successor.get(successor.size() - 1);
                if (getCostFunction().costFunction(nextState) != 0) {
                    this.hCSteepestDescent.setInitialState(getSuccessor().successor(null).get(0));
                } else {
                    nextStates.add(nextState);
                    setCurrentState(nextState);
                }
            } else {
                State initialState = getSuccessor().successor(null).get(0);
                this.hCSteepestDescent.setInitialState(initialState);
                this.hCSteepestDescent.setCurrentState(initialState);
            }
        }
        return nextStates;
    }
}
