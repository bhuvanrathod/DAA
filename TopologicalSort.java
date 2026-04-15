import java.util.*;

public class TopologicalSort {

    static void dfs(int node, boolean[] visited,
                    Stack<Integer> stack,
                    List<List<Integer>> adj) {

        visited[node] = true;

        for (int neighbor : adj.get(node)) {   
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack, adj);
            }
        }

        stack.push(node);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices:");
        int V = sc.nextInt();

        System.out.println("Enter number of edges:");
        int E = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        System.out.println("Enter custom starting order of all vertices:");
        //starting vertex for each dfs traversal
        //all vertices must be entered 
        //example: 4 3 2 1 5 0
        int[] order = new int[V];
        for (int i = 0; i < V; i++) {
            order[i] = sc.nextInt();
        }

        for (int i = 0; i < V; i++) {
            if (!visited[order[i]]) {
                dfs(order[i], visited, stack, adj);
            }
        }

        System.out.println("Topological Order:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}