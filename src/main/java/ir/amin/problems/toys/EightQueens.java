package ir.amin.problems.toys;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;
import ir.amin.search.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;

/**
 * Created by Amin on 04/11/2016.
 */
public class EightQueens {

    private Random random = new Random(120);

    private GoalFormul<List<Integer>> goalFormul = new GoalFormul<List<Integer>>() {
        @Override
        public boolean isGoal(State<List<Integer>> t) {
            List<Integer> board = t.getValue();
            for (int i = 1; i < board.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
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
                    if (Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
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
                    int q1 = currnetBoard.get(i);
                    int q2 = currnetBoard.get(j);
                    ArrayList<Integer> newArrayList = (ArrayList<Integer>) currnetBoard.clone();
                    newArrayList.set(i, q2);
                    newArrayList.set(j, q1);
                    nextStates.add(new State(newArrayList));
                }
            }
            return nextStates;
        }
    };

    private ISuccessor randomSuccessor = new ISuccessor() {
        @Override
        public List<State> successor(State currentState) {
            ArrayList<State> nextStates = new ArrayList<State>();
            ArrayList<Integer> currnetBoard = (ArrayList<Integer>) currentState.getValue();
            ArrayList<Integer> arrayList;
            int i = random.nextInt(8), j;
            do {
                j = random.nextInt(8);
            } while (i == j);
            int q1 = currnetBoard.get(i);
            int q2 = currnetBoard.get(j);
            arrayList = (ArrayList<Integer>) currnetBoard.clone();
            arrayList.set(i, q2);
            arrayList.set(j, q1);
            nextStates.add(new State(arrayList));
            return nextStates;
        }
    };

    private ISuccessor simulatedAnnuleaningSuccessor = new ISuccessor() {
        @Override
        public List<State> successor(State currentState) {
            ArrayList<State> nextStates = new ArrayList<State>();
            ArrayList<Integer> currnetBoard = (ArrayList<Integer>) currentState.getValue();
            ArrayList<Integer> arrayList;
            int i = random.nextInt(8), j;
            do {
                j = random.nextInt(8);
            } while (i == j);
            int q1 = currnetBoard.get(i);
            int q2 = currnetBoard.get(j);
            arrayList = (ArrayList<Integer>) currnetBoard.clone();
            arrayList.set(i, q2);
            arrayList.set(j, q1);
            nextStates.add(new State(arrayList));
            return nextStates;
        }
    };

    private void printList(List<List<Integer>> list) {
        for (List<Integer> l : list) {
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i) + " ");
            }
            System.out.println("");
        }
    }

    private ISuccessor hcRandomRestartSuccessor = new ISuccessor() {
        @Override
        public List<State> successor(State currentState) {
            ArrayList<State> nextStates = new ArrayList<State>();
            nextStates.add(new State(generateBoard(8)));
            return nextStates;
        }
    };

    public EightQueens() throws IOException, WriteException {
        State<List<Integer>> initialState;
        int hcfbSuccess = 0;
        int hcsdSuccess = 0;
        int hcrrSuccess = 0;
        int saSuccess = 0;
        double[][] eTimes = new double[200][4];
        long startTime, endTime, elapsedTime;

        for (int t = 0; t < 200; t++) {
            initialState = new State<>(this.generateBoard(8));
            HCFirstBest hcFirstBest
                    = new HCFirstBest(initialState, this.goalFormul, this.cost, this.randomSuccessor, 28);
            HCSteepestDescent hcSteepestDescent
                    = new HCSteepestDescent(initialState, this.goalFormul, this.cost, this.hcSteepestDescentSuccessor);
            HCRandomRestart hcRandomRestart
                    = new HCRandomRestart(initialState, this.goalFormul, this.cost, this.hcRandomRestartSuccessor, hcSteepestDescent);
            SimulatedAnnealingSearch simulatedAnnealingSearch
                    = new SimulatedAnnealingSearch(initialState, this.goalFormul, this.cost, this.randomSuccessor, 28, 0.0001);

            startTime = System.nanoTime();
            if (solve(hcFirstBest)) {
                hcfbSuccess++;
            }
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            eTimes[t][0] = (double) elapsedTime / 1000000000.0;;

            startTime = System.nanoTime();
            if (solve(hcSteepestDescent)) {
                hcsdSuccess++;
            }
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            eTimes[t][1] = (double) elapsedTime / 1000000000.0;;

            startTime = System.nanoTime();
            if (solve(hcRandomRestart)) {
                hcrrSuccess++;
            }
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            eTimes[t][2] = (double) elapsedTime / 1000000000.0;;

            startTime = System.nanoTime();
            if (solve(simulatedAnnealingSearch)) {
                saSuccess++;
            }
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            eTimes[t][3] = (double) elapsedTime / 1000000000.0;;
        }
        System.out.println("HCFirstBest : " + (hcfbSuccess / 2.0) + "%");
        System.out.println("HCSteepestDescent : " + (hcsdSuccess / 2.0) + "%");
        System.out.println("HCRandomRestart : " + (hcrrSuccess / 2.0) + "%");
        System.out.println("SimulatedAnnealing : " + (saSuccess / 2.0) + "%");
    }

    public boolean solve(Search search) {
        return search.isSuccessFul(search.run());
    }

    public static ArrayList<Integer> generateBoard(int n) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            al.add(i);
        }
        Collections.shuffle(al);
        return al;
    }

    public static void main(String[] args) {
      
        EightQueens eightQueens = new EightQueens();
        
    }

}
