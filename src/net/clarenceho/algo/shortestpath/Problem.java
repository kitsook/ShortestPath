package net.clarenceho.algo.shortestpath;

import java.util.Collection;

/**
 * Define the shortest path problem.
 */
public interface Problem {
    /**
     * Returns the starting node of the problem.
     *
     * @return the starting node
     */
    Node getStartNode();

    /**
     * Returns the ending node of the problem.
     *
     * @return the ending node
     */
    Node getEndNode();

    /**
     * Returns all the nodes of the problem.
     *
     * @return collection of nodes
     */
    Collection<Node> getNodes();
}
