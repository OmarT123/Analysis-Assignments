package analysis_Assignments;

import java.util.*;

public class Assignment3 {
	private static HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();
    private static Set<Integer> recursionStack = new HashSet<>();


    private static void findAndPrintCycles() {
        for (int node : adjacencyList.keySet()) {
            if (!visited.contains(node)) {
                dfsForCycles(node);
            }
        }
    }

    private static void dfsForCycles(int currentNode) {
        visited.add(currentNode);
        recursionStack.add(currentNode);

        List<Integer> neighbors = adjacencyList.getOrDefault(currentNode, Collections.emptyList());
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsForCycles(neighbor);
            } else if (recursionStack.contains(neighbor)) {
                // Cycle detected, print the cycle
                System.out.print("Cycle: ");
                printCycle(neighbor, currentNode);
                System.out.println();
            }
        }

        recursionStack.remove(currentNode);
    }

    private static void printCycle(int startNode, int endNode) {
        boolean print = false;
        for (int node : recursionStack) {
            if (node == startNode) {
                print = true;
            }
            if (print) {
                System.out.print(node + " ");
                if (node == endNode) {
                    break;
                }
            }
        }
    }

    public static void addEdge(int u, int v) {
        adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    private static void dfs(int startNode) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(startNode, visited);
    }

    private static void dfsHelper(int currentNode, Set<Integer> visited) {
        if (!visited.contains(currentNode)) {
            visited.add(currentNode);
            System.out.print(currentNode + " ");

            List<Integer> neighbors = adjacencyList.getOrDefault(currentNode, Collections.emptyList());
            for (int neighbor : neighbors) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    private static void bfs(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                System.out.print(currentNode + " ");
                List<Integer> neighbors = adjacencyList.getOrDefault(currentNode, Collections.emptyList());
                queue.addAll(neighbors);
            }
        }
    }
    
    public static boolean isBipartite() {
        Map<Integer, Integer> color = new HashMap<>();
        for (int node : adjacencyList.keySet()) {
            if (!color.containsKey(node)) {
                color.put(node, 0);
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(node);

                while (!queue.isEmpty()) {
                    int currentNode = queue.poll();
                    List<Integer> neighbors = adjacencyList.getOrDefault(currentNode, Collections.emptyList());

                    for (int neighbor : neighbors) {
                        if (!color.containsKey(neighbor)) {
                            color.put(neighbor, 1 - color.get(currentNode));
                            queue.offer(neighbor);
                        } else if (color.get(neighbor) == color.get(currentNode)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isTree() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited.contains(node)) {
                return false;
            }
            visited.add(node);

            List<Integer> neighbors = adjacencyList.getOrDefault(node, Collections.emptyList());
            stack.addAll(neighbors);
        }
        return visited.size() == adjacencyList.size();
    }

    public static void main(String[] args) {
    	
    	final int[] V = {1,2,3,4};
    	final int[][] E = {{1,3},{1,4},{2,1},{2,3},{3,4},{4,1},{4,2}};
    	
    	for (int i = 0; i < E.length; i++)
    	{
    		addEdge(E[i][0], E[i][1]);
    	}

        System.out.print("DFS: ");
        dfs(1);
        System.out.println();

        System.out.print("BFS: ");
        bfs(1);
        System.out.println();
        if (isBipartite()) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }

        if (isTree()) {
            System.out.println("The graph is a tree.");
        } else {
            System.out.println("The graph is not a tree.");
        }
        
        findAndPrintCycles();
        
    }
}
