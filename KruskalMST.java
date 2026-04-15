import java.util.*;

public class KruskalMST {

    static class Edge implements Comparable<Edge> {
        int src, dest, wt;

        Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.wt - e2.wt; // sort by weight
        }
    }

    static int parent[];
    static int rank[];

    // Find with path compression
    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by rank
    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }

    public static void kruskal(ArrayList<Edge> edges, int V) {

        Collections.sort(edges);

        parent = new int[V];
        rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int cost = 0;

        System.out.println("\nEdges in MST:");

        for (Edge e : edges) {
            int pa = find(e.src);
            int pb = find(e.dest);

            if (pa != pb) {
                union(e.src, e.dest);
                cost += e.wt;
                System.out.println(e.src + " - " + e.dest + " = " + e.wt);
            }
        }

        System.out.println("\nMinimum Cost of MST: " + cost);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();

        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int wt = sc.nextInt();

            edges.add(new Edge(src, dest, wt));
        }

        kruskal(edges, V);

        sc.close();
    }
}