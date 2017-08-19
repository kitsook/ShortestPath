package net.clarenceho.algo.shortestpath.test;

import net.clarenceho.algo.shortestpath.Node;
import net.clarenceho.algo.shortestpath.Problem;
import net.clarenceho.util.Pair;

import java.util.*;

/**
 * Idea based on http://practice.geeksforgeeks.org/problems/minimum-cost-path/0
 *
 * Given a square grid of size n, each cell of which contains integer cost which
 * represents a cost to traverse through that cell, we need to find a path from
 * top left cell to bottom right cell by which total cost incurred is minimum.
 *
 * Example 1. Input:
 * { {  31, 100,  65,  12,  18 },
 *   {  10,  13,  47, 157,   6 },
 *   { 100, 113, 174,  11,  33 },
 *   {  88, 124,  41,  20, 140 },
 *   {  99,  32, 111,  41,  20 } }
 * Expected output is 327.
 *
 * Example 2. Input:
 * { { 42, 93 },
 *   {  7, 14 } }
 * Expected output is 63.
 */
public class MinCostSqMatrix implements Problem {
    private int problemSize;
    private Map<Pair<Integer>, Node> nodeMap;

    public MinCostSqMatrix(int[][] costs) {
        problemSize = costs.length;
        nodeMap = new HashMap<>();
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs[0].length; j++) {
                nodeMap.put(new Pair<>(i, j), new MatrixNode(i, j, costs[i][j]));
            }
        }
    }

    @Override
    public Node getStartNode() {
        return nodeMap.get(new Pair<>(0, 0));
    }

    @Override
    public Node getEndNode() {
        return nodeMap.get(new Pair<>(problemSize-1, problemSize-1));
    }

    @Override
    public Collection<Node> getNodes() {
        return nodeMap.values();
    }


    private class MatrixNode implements Node {
        int i, j;
        int cost;

        MatrixNode(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        @Override
        public List<Node> getNeighbor() {
            List<Node> result = new ArrayList<>();
            if (i - 1 >= 0) {
                result.add(nodeMap.get(new Pair<>(i - 1, j)));
            }
            if (i + 1 < problemSize) {
                result.add(nodeMap.get(new Pair<>(i + 1, j)));
            }
            if (j - 1 >= 0) {
                result.add(nodeMap.get(new Pair<>(i, j - 1)));
            }
            if (j + 1 < problemSize) {
                result.add(nodeMap.get(new Pair<>(i, j + 1)));
            }
            return result;
        }

        /**
         * @param from Not used in our case. Cost is assigned to node, not path
         * @return The cost of reaching this node
         */
        @Override
        public int costFrom(Node from) {
            return cost;
        }

        @Override
        public boolean isStartNode() {
            return i == 0 && j == 0;
        }

        @Override
        public boolean isEndNode() {
            return i == problemSize-1 && j == problemSize-1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MatrixNode that = (MatrixNode) o;

            return i == that.i && j == that.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
