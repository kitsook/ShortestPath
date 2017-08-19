package net.clarenceho.algo.shortestpath;

import java.util.List;

/**
 *
 */
public interface Node {

    /**
     * Returns neighbors of current node
     *
     * @return list of neighbors
     */
    List<Node> getNeighbor();

    /**
     * Returns the cost of reaching current node from a neighbor node.
     * Note that in some problems, cost can be assigned to the node itself
     * instead of the path.  In these cases, the from parameter may not be used.
     *
     * @param from originating neighbor node
     * @return cost of reaching this node from originating neighbor node
     */
    int costFrom(Node from);

    /**
     * Checks whether the node is the starting node.
     *
     * @return whether the node is the starting node.
     */
    boolean isStartNode();

    /**
     * Checks whether the node is the ending ndoe.
     *
     * @return whether the node is the ending node.
     */
    boolean isEndNode();
}
