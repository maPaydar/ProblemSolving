package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class HillClimbSearch extends Search {

    private ICost iCostFucntion;
    private ISuccessor iSuccessor;


    public HillClimbSearch(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
    }

    public HillClimbSearch(State initialState, GoalFormul goalFormul, ICost iCostFucntion) {
        super(initialState, goalFormul);
        this.iCostFucntion = iCostFucntion;
    }

    public void setICostFucntion(ICost iCostFucntion) {
        this.iCostFucntion = iCostFucntion;
    }

    @Override
    public List<State> run() {

        return null;
    }
}
