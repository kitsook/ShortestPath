package net.clarenceho.algo.shortestpath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dijkstra's algorithm for finding shortest path.
 * Reference: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class Dijkstra {
    private Problem problem;

    public Dijkstra(Problem problem) {
        this.problem = problem;
    }

    public int resolve() {
        Map<Node, Integer> tentativeDist = new HashMap<>();
        Node currentNode = problem.getStartNode();
        Set<Node> visited = new HashSet<>();

        // Assign to every node a tentative distance value.
        for (Node n : problem.getNodes()) {
            if (n.isStartNode()) {
                tentativeDist.put(n, n.costFrom(n)); // initial cost of starting node
            } else {
                tentativeDist.put(n, Integer.MAX_VALUE);
            }
        }

        while (!visited.contains(problem.getEndNode())) {
            for (Node neighbor : currentNode.getNeighbor()) {
                int newDist = tentativeDist.get(currentNode) + neighbor.costFrom(currentNode);
                if (newDist < tentativeDist.get(neighbor)) {
                    tentativeDist.put(neighbor, newDist);
                }
            }
            visited.add(currentNode);

            Node smallestUnvisited = smallestUnvisited(tentativeDist, visited);
            if (smallestUnvisited == null ||
                    currentNode.equals(problem.getEndNode()) ||
                    tentativeDist.get(smallestUnvisited) == Integer.MAX_VALUE) {
                break;
            }
            currentNode = smallestUnvisited;
        }

        return tentativeDist.get(problem.getEndNode());
    }

    private Node smallestUnvisited(Map<Node, Integer> tentativeDist, Set<Node> visited) {
        Node result = null;
        for (Node n : tentativeDist.keySet()) {
            if (!visited.contains(n) &&
                    (result == null || tentativeDist.get(n) < tentativeDist.get(result))) {
                result = n;
            }
        }
        return result;
    }
}
