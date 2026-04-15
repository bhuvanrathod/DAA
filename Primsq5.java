import java.util.*;

public class Primsq5 {

    static class Edge {
        int src, dest, wt;

        Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static class pairp implements Comparable<pairp> {
        int v;
        int cost;

        public pairp(int v, int c) {
            this.v = v;
            this.cost = c;
        }

        @Override
        public int compareTo(pairp p1) {
            return this.cost - p1.cost; // min heap
        }
    }

    public static void prims(ArrayList<Edge>[] graph) {
        boolean visi[] = new boolean[graph.length];
        PriorityQueue<pairp> pq = new PriorityQueue<>();

        pq.add(new pairp(0, 0)); // start from vertex 0
        int finalcost = 0;

        while (!pq.isEmpty()) {
            pairp curr = pq.remove();

            if (!visi[curr.v]) {
                visi[curr.v] = true;
                finalcost += curr.cost;

                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i);
                    if (!visi[e.dest]) {
                        pq.add(new pairp(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("Minimum Cost of MST: " + finalcost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        // edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int wt = sc.nextInt();

            // undirected graph
            graph[src].add(new Edge(src, dest, wt));
            graph[dest].add(new Edge(dest, src, wt));
        }

        prims(graph);

        sc.close();
    }
}