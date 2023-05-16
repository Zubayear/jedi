package com.zubayear.customds.graph;

import java.util.*;

public class Graph<T> {
    /**
     * 1 -> [2, 3]
     * 2 -> [1]
     * 3 -> []
     * 4 -> [1]
     */
    protected Map<T, List<T>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(T node1, T node2) {
        graph.computeIfAbsent(node1, k -> new ArrayList<>()).add(node2);
        graph.computeIfAbsent(node2, k -> new ArrayList<>()).add(node1);
    }

    public void printGraph() {
        System.out.println(graph);
    }

    public List<T> bfs(T start) {
        List<T> result = new ArrayList<>();
        Deque<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        queue.offerLast(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.pollFirst();
            result.add(current);
            List<T> neighbors = graph.get(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offerLast(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return result;
    }

    public List<T> dfs(T start) {
        List<T> result = new ArrayList<>();
        Deque<T> stack = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        stack.offerFirst(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            T current = stack.pollFirst();
            result.add(current);
            List<T> neighbors = graph.get(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.offerFirst(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return result;
    }
}
