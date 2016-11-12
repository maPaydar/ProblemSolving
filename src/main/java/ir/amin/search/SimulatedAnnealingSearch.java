package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class SimulatedAnnealingSearch extends HillClimbSearch {

    private double initialTemp;
    private double step;

    public SimulatedAnnealingSearch(State initialState, GoalFormul goalFormul, double initialTemp, double step) {
        super(initialState, goalFormul);
        this.initialTemp = initialTemp;
        this.step = step;
    }

    public SimulatedAnnealingSearch(State initialState, GoalFormul goalFormul, ICost iCostFunction, double initialTemp, double step) {
        super(initialState, goalFormul, iCostFunction);
        this.initialTemp = initialTemp;
        this.step = step;
    }

    public SimulatedAnnealingSearch(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor, double initialTemp, double step) {
        super(initialState, goalFormul, iCostFunction, iSuccessor);
        this.initialTemp = initialTemp;
        this.step = step;
    }

    @Override
    public List<State> run() {
        List<State> nextStates = new ArrayList<>();
        double temperature = initialTemp;
        double val = step;
        double probability;
        int delta;
        double determine;
        State nextState;
        while (getCostFunction().costFunction(getCurrentState()) != 0 && temperature > 0) {
            try {
                nextState = getSuccessor().successor(getCurrentState()).get(0);
            } catch (NullPointerException e) {
                return new ArrayList<State>();
            }
            if (getCostFunction().costFunction(nextState) == 0) {
                nextStates.add(nextState);
                return nextStates;
            }
            delta = getCostFunction().costFunction(getCurrentState()) - getCostFunction().costFunction(nextState);
            if (delta > 0) {
                setCurrentState(nextState);
            } else {
                probability = Math.exp(delta / temperature);
                determine = Math.random();
                if (determine <= probability) {
                    setCurrentState(nextState);
                }
            }
            temperature = temperature - val;
        }
        nextStates.add(getCurrentState());
        return nextStates;
    }
}
