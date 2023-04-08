package com.project2;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class LightSolver_String {

    private Graph<String, DefaultEdge> KG;
    private Graph<PuzzleState, DefaultEdge> G;
    private int size;
    private int area;
    private PuzzleState startState;
    private PuzzleState endState;
    private String KstartState = "";
    private String KendState = "";
    //private String problem;

    public LightSolver_String(String lb, int board_size) {

        KstartState = lb;
        size = board_size;
        area = size * size;
        for (int i = 0; i < area; i++) {
            KendState = KendState + "0";
        }
        KG = new SimpleDirectedGraph<>(DefaultEdge.class);
    }

    public List<String> Solve() {
        printBoard(KstartState);
        ShortestPathAlgorithm<String, DefaultEdge> shortestPath = null;
        shortestPath = new DijkstraShortestPath<>(KG);
        List<String> sol = new ArrayList<String>();
        ArrayList<String> AL = new ArrayList<String>();
        String temp = "";
        int i = 0;
        AL.add(KstartState);
        while (i < AL.size()) {
            temp = AL.get(i);
            if (KG.containsVertex(KendState)) {
                break;
            }
            if (!KG.containsVertex(temp)) {
                KG.addVertex(temp);
            }
            for (int j = 0; j < area; j++) {
                String newState = KtoggledState(temp, j);
                String Button = Integer.toString(i) + " " + Integer.toString(j);
                KG.addVertex(Button);
                KG.addEdge(temp, Button);
                if (!KG.containsVertex(newState)) {
                    KG.addVertex(newState);
                    AL.add(newState);
                }
                KG.addEdge(Button, newState);
            }
            i++;
        }
        if (!KG.containsVertex(KendState)) {
            return null;
        } else {
            sol = shortestPath.getPath(KstartState, KendState).getVertexList();
        }
        return sol;
    }

    public void printLayout() {
        System.out.println("===========================Puzzle button layout========================");
        for (int k = 0; k < 6 * size + 1; k++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < area; i++) {
            if ((i + 1) % size == 0) {
                System.out.printf("| %-3d |", i + 1);
                System.out.println();
                for (int k = 0; k < 6 * size + 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            } else {
                System.out.printf("| %-3d ", i + 1);
            }
        }
    }

    public void printBoard(String s) {
        System.out.print("Bit string = ");
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
        for (int k = 0; k < 6 * size + 1; k++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1) % size == 0) {
                System.out.printf("| %-3s |", s.charAt(i));
                System.out.println();
                for (int k = 0; k < 6 * size + 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            } else {
                System.out.printf("| %-3s ", s.charAt(i));
            }

        }
    }

    public void printSolution(List<String> Solution) {
        String newState = KstartState;
        String switchButton;
        System.out.println("===========================Puzzle solution=============================");
        System.out.print("Puzzle solution: ");
        if (Solution != null) {
            ArrayList<String> AL = new ArrayList<String>();
            for (int i = 0; i < Solution.size() / 2 + 1; i++) {
                String step = Solution.get(i * 2);
                if (i != 0) {
                    System.out.print("->");
                    AL.add(step);
                }
                System.out.print(step);
            }
            System.out.println();
            System.out.println(Solution.size() / 2 + " moves to turn off all lights");
            System.out.println("Show solution:");
            for (int i = 0; i < Solution.size() / 2; i++) {
                String[] button = Solution.get(i * 2 + 1).split(" ");
                if (newState.charAt(Integer.parseInt(button[1].trim())) == '1') {
                    switchButton = "off";
                } else {
                    switchButton = "on";
                }
                int row = Integer.parseInt(button[1].trim()) / size;
                int col = Integer.parseInt(button[1].trim()) % size;
                System.out.println(">>> Move " + (i + 1) + " : turn " + switchButton + " row " + row + ", col " + col);
                printBoard(AL.get(i));
            }
        } else {
            System.out.println("No solution");
        }
        System.out.println("=====================The puzzle has been solved!=======================");
    }

    public String KtoggledState(String p, int index) {
        int pos = index + 1;
        String s = p;
        char state[] = s.toCharArray();

        state[index] = toggle(state[index]);
        if (pos % size != 0) {
            state[index + 1] = toggle(state[index + 1]);
        }
        if (pos % size != 1) {
            state[index - 1] = toggle(state[index - 1]);
        }
        if (pos <= area - size) {
            state[index + size] = toggle(state[index + size]);
        }
        if (pos > size) {
            state[index - size] = toggle(state[index - size]);
        }
        String newp = String.valueOf(state);
        return newp;
    }

    public PuzzleState toggledState(PuzzleState p, int index) {
        int pos = index + 1;
        String s = p.getState();
        char state[] = s.toCharArray();

        state[index] = toggle(state[index]);
        if (pos % size != 0) {
            state[index + 1] = toggle(state[index + 1]);
        }
        if (pos % size != 1) {
            state[index - 1] = toggle(state[index - 1]);
        }
        if (pos <= area - size) {
            state[index + size] = toggle(state[index + size]);
        }
        if (pos > size) {
            state[index - size] = toggle(state[index - size]);
        }

        PuzzleState newP = new PuzzleState(String.valueOf(state));
        return newP;
    }

    public char toggle(char a) {
        int n = Integer.parseInt(String.valueOf(a));
        n = (n + 1) % 2;
        return Character.forDigit(n, 10);
    }

    public PuzzleState getstartState() {
        return startState;
    }
}

