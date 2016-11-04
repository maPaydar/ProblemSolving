package ir.amin.problems.toys;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;
import ir.amin.search.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Amin on 04/11/2016.
 */
public class EightQueens {

    private Random random = new Random();


    private GoalFormul<List<Integer>> goalFormul = new GoalFormul<List<Integer>>() {
        @Override
        public boolean isGoal(State<List<Integer>> t) {
            List<Integer> board = t.getValue();
            for (int i = 1; i < board.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (board.get(i) == board.get(j) | Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
                        return false;
                    }
                }
            }
            return true;
        }
    };

    private ICost<List<Integer>> cost = new ICost<List<Integer>>() {
        @Override
        public int costFunction(State<List<Integer>> s) {
            ArrayList<Integer> board = (ArrayList<Integer>) s.getValue();
            int cost = 0;
            for (int i = 1; i < board.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (board.get(i) == board.get(j) | Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
                        cost++;
                    }
                }
            }
            return cost;
        }
    };

    private ISuccessor hcSteepestDescentSuccessor = new ISuccessor() {
        @Override
        public List<State> successor(State currentState) {
            ArrayList<State> nextStates = new ArrayList<State>();
            ArrayList<Integer> currnetBoard = (ArrayList<Integer>) currentState.getValue();
            for (int i = 0; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    int row = currnetBoard.get(i);
                    int newRow = row + j;
                    if (newRow > 7 && newRow % 7 != 0) {
                        newRow = (newRow % 7) - 1;
                    } else if (newRow > 7 && newRow % 7 == 0) {
                        newRow = 7;
                    }
                    ArrayList<Integer> newArrayList = (ArrayList<Integer>) currnetBoard.clone();
                    newArrayList.set(i, newRow);
                    nextStates.add(new State(newArrayList));
                }
            }
            return nextStates;
        }
    };


    private ISuccessor hcFirstBestSuccessor = new ISuccessor() {
        @Override
        public List<State> successor(State currentState) {
            ArrayList<State> nextStates = new ArrayList<State>();
            ArrayList<Integer> currnetBoard = (ArrayList<Integer>) currentState.getValue();
            int i = random.nextInt(8);
            int j = random.nextInt(7) + 1;
            int row = currnetBoard.get(i);
            int newRow = row + j;
            if (newRow > 7 && newRow % 7 != 0) {
                newRow = (newRow % 7) - 1;
            } else if (newRow > 7 && newRow % 7 == 0) {
                newRow = 7;
            }
            ArrayList<Integer> arrayList = (ArrayList<Integer>) currnetBoard.clone();
            arrayList.set(i, newRow);
            nextStates.add(new State(arrayList));
            return nextStates;
        }
    };

    private ISuccessor hcRandomRestartSuccessor = new ISuccessor() {
        @Override
        public List<State> successor(State currentState) {
            ArrayList<State> nextStates = new ArrayList<State>();
            nextStates.add(new State(generateBoard()));
            return nextStates;
        }
    };

    public EightQueens() {
        State<List<Integer>> initialState = new State<>(this.generateBoard());
        for (int t = 0; t < 500; t++) {
            HCFirstBest hcFirstBest =
                    new HCFirstBest(initialState, this.goalFormul, this.cost, this.hcFirstBestSuccessor);
            HCSteepestDescent hcSteepestDescent =
                    new HCSteepestDescent(initialState, this.goalFormul, this.cost, this.hcSteepestDescentSuccessor);
            HCRandomRestart hcRandomRestart =
                   new HCRandomRestart(initialState, this.goalFormul, this.cost, this.hcRandomRestartSuccessor);
            SimulatedAnnealingSearch simulatedAnnealingSearch =
                    new SimulatedAnnealingSearch(initialState, this.goalFormul, this.cost, this.hcFirstBestSuccessor, 28, 0.0001);
            System.out.println(solve(simulatedAnnealingSearch));
        }

    }

    public boolean solve(Search search) {
        return search.isSuccessFul(search.run());
    }

    private ArrayList<Integer> generateBoard() {
        ArrayList<Integer> al = new ArrayList<>();
        Random gen = new Random();
        for (int i = 0; i < 8; i++) {
            al.add(gen.nextInt(8));
        }
        return al;
    }

    public static void main(String[] args) {
        new EightQueens();
    }

}