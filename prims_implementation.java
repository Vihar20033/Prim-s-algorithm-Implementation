import java.util.Arrays;

class Graph {
    private int V;
    private int[][] graph;

    public Graph(int vertices) {
        this.V = vertices;
        graph = new int[vertices][vertices];
    }

    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    public int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public void primMST() {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent);
    }

    public void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Number of cities or junctions
        int vertices = 5;
        
        // Initialize graph
        Graph g = new Graph(vertices);
        
        // Add roads (edges) with their respective distances (weights)
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        // Run Prim's Algorithm
        g.primMST();
    }
}
