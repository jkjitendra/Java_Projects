package org.program;

import java.util.*;

public class TravelTown {

    // Method to remove the edge
    private static void removeEdge(List<List<Integer>> graph, int a, int b) {
        graph.get(a).remove((Integer) b);
        graph.get(b).remove((Integer) a);
    }

    // Method to add the edge
    private static void addEdge(List<List<Integer>> graph, int a, int b) {
        graph.get(a).add((Integer) b);
        graph.get(b).add((Integer) a);
    }

    // BFS method to find shortest path
    private static int bfs(List<List<Integer>> graph, int n, int source) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        int[] distance = new int[n+1];
        queue.offer(source);
        visited[source] = true;
        distance[source] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    queue.offer(neighbor);
                    if (neighbor == n) {
                        return distance[neighbor];
                    }
                }
            }
        }
        return -1; // No path found
    }

    // Main method to find shortest path with each road removed
    public static List<Integer> townTravel(int n, List<Integer> a, List<Integer> b) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < a.size(); i++) {
            graph.get(a.get(i)).add(b.get(i));
            graph.get(b.get(i)).add(a.get(i));
        }
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            removeEdge(graph, a.get(i), b.get(i));

            int pathLength = bfs(graph, n, 1);

            res.add(pathLength);

            addEdge(graph, a.get(i), b.get(i));
        }
        return res;
    }

    // Example usage
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 1);
        List<Integer> b = Arrays.asList(3, 3, 2);
        List<Integer> result = townTravel(3, a, b);
        for (int num : result) {
            System.out.println(num);
        }
    }
}
