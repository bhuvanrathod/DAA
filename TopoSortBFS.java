import java.util.*;

public class TopoSortBFS {

    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Calculate indegree
    public static void indegree(ArrayList<Edge>[] graph, int[] indeg) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }

    // Topological Sort using BFS (Kahn's Algorithm)
    public static void topsortbfs(ArrayList<Edge>[] graph) {
        int indeg[] = new int[graph.length];
        indegree(graph, indeg);

        Queue<Integer> q = new LinkedList<>();

        // add nodes with indegree 0
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");

            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indeg[e.dest]--;

                if (indeg[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        // Input edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (src dest):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();

            graph[src].add(new Edge(src, dest));
        }

        System.out.println("\nTopological Sort (BFS):");
        topsortbfs(graph);

        sc.close();
    }
}