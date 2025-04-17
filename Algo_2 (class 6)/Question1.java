import java.util.*;

interface UndirectedGraph {

    boolean isConnected(int nodes);

    List<Integer> reachable(int a);

    List<Edge> mst(int nodes);

    List<Integer> shortestPath(int a, int b, int nodes);
}

class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph implements UndirectedGraph {

    List<Edge> edges;

    public Graph(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isConnected(int nodes) {

        int n = nodes;

        int[] visited = new int[n + 1];
        for (int i = 1; i <= n; i++)
            visited[i] = 0;

        Stack<Integer> st = new Stack<>();

        int srcNode = edges.get(0).source;

        st.push(srcNode);
        visited[srcNode] = 1;

        while (!st.isEmpty()) {
            int node = st.pop();

            for (Edge e : edges) {
                if (e.source == node) {
                    if (visited[e.destination] == 0) {
                        st.push(e.destination);
                        visited[e.destination] = 1;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 1)
                cnt++;
        }

        return (cnt == n);
    }

    public List<Integer> reachable(int a) {
        List<Integer> nodesReached = new ArrayList<>();

        for (Edge e : edges) {
            if (e.source == a) {
                nodesReached.add(e.destination);
            }
        }

        return nodesReached;
    }

    public List<Edge> mst(int nodes) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (Integer) a[0]));

        List<Edge> mst = new ArrayList<>();

        int n = nodes;

        int[] visited = new int[n + 1];
        for (int i = 1; i <= n; i++)
            visited[i] = 0;

        int srcNode = edges.get(0).source;

        pq.add(new int[] { 0, srcNode, -1 });

        while (!pq.isEmpty()) {
            int wt = pq.peek()[0];
            int node = pq.peek()[1];
            int parentNode = pq.peek()[2];
            pq.poll();

            if (visited[node] == 1)
                continue;
            visited[node] = 1;

            if (parentNode != -1) {
                Edge e = new Edge(parentNode, node, wt);
                mst.add(e);
            }

            for (Edge e : edges) {
                if (e.source == node) {
                    int adjNode = e.destination;
                    int adjWt = e.weight;
                    if (visited[adjNode] == 0) {
                        pq.add(new int[] { adjWt, adjNode, node });
                    }
                }
            }
        }

        return mst;
    }

    // yeh same as printing the dijikstra algo using prim's
    public List<Integer> shortestPath(int a, int b, int nodes) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> (Integer) s[0]));

        int srcNode = a;
        int n = nodes;

        int dist[] = new int[n + 1];
        int parent[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = i;
        }

        dist[srcNode] = 0;

        pq.add(new int[] { 0, srcNode });

        while (!pq.isEmpty()) {
            int node = pq.peek()[1];
            int dis = pq.peek()[0];
            pq.poll();

            for (Edge e : edges) {
                if (e.source == node) {
                    int adjWt = e.weight;
                    int adjNode = e.destination;
                    if (dis + adjWt < dist[adjNode]) {
                        dist[adjNode] = dis + adjWt;
                        parent[adjNode] = node;

                        pq.add(new int[] { dis + adjWt, adjNode });
                    }
                }
            }
        }

        if (dist[b] == Integer.MAX_VALUE) {
            return List.of(-1);
        }

        List<Integer> path = new ArrayList<>();
        int node = b;

        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }

        path.add(node);
        Collections.reverse(path);

        return path;

    }

}

class Question1 {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        List<Edge> edges = new ArrayList<>();

        try {
            System.out.println("Enter the number of nodes in a graph");
            int nodes = scn.nextInt();
            if (nodes <= 0) {
                throw new IllegalArgumentException("Nodes cannot be less than 1");
            }

            System.out.println("Enter the number of edges");
            int nEdges = scn.nextInt();
            if (nEdges <= 0) {
                throw new IllegalArgumentException("Edges cannot be less than 1");
            }

            for (int i = 0; i < nEdges; i++) {
                System.out.println("Enter the source node");
                int src = scn.nextInt();
                if (src <= 0) {
                    throw new IllegalArgumentException("Source node must be greater than or equal to 1");
                }

                System.out.println("Enter the destination node");
                int dest = scn.nextInt();
                if (dest <= 0) {
                    throw new IllegalArgumentException("Destination node must be greater than or equal to 1");
                }

                System.out.println("Enter the weight of a edge");
                int wt = scn.nextInt();
                if (wt <= 0) {
                    throw new IllegalArgumentException("Weight must be postive");
                }

                Edge e1 = new Edge(src, dest, wt);
                Edge e2 = new Edge(dest, src, wt);
                edges.add(e1);
                edges.add(e2);

            }

            while (true) {
                System.out.println("Enter 1 to to check if graph is connected or not");
                System.out.println("Enter 2 to get the all reachable nodes from a given node");
                System.out.println("Enter 3 to get the minimum spanning tree of a graph");
                System.out.println("Enter 4 to get the shortest Path");
                System.out.println("Enter 5 to exit the program");

                String choice = scn.next();
                Graph g = new Graph(edges);
                if ("1".equals(choice)) {
                    System.out.println("Checking graph is connected or not");

                    if (g.isConnected(nodes)) {
                        System.out.println("Graph is connected");
                    } else {
                        System.out.println("Graph is not connected");
                    }

                } else if ("2".equals(choice)) {
                    System.out.println("Enter the source node to get the reachable nodes from it");
                    int src = scn.nextInt();
                    if (src <= 0) {
                        throw new IllegalArgumentException("Source node must be greater than or equal to 1");
                    }

                    int flag = 1;
                    for (Edge e : edges) {  
                        if (e.source != src) {
                            flag = 1;
                        } else {
                            flag = 0;
                            break;
                        }
                    }

                    if (flag == 1) {
                        System.out.println("The source node is not exist in our graph");
                    } else {
                        List<Integer> nodesReach = g.reachable(src);

                        if (nodesReach.size() == 0) {
                            System.out.println("There is no destination node to reach");
                        } else {
                            System.out.println("The destination nodes which have reached is ");
                            for (Integer dest : nodesReach) {
                                System.out.print("destination node: " + dest + " ");
                            }
                            System.out.println();
                        }
                    }

                } else if ("3".equals(choice)) {
                    List<Edge> minSpanningTree = g.mst(nodes);

                    System.out.println("The minimum spanning tree is");
                    for (Edge e : minSpanningTree) {
                        System.out.println(e.source + " <-> " + e.destination + "weight: " + e.weight);
                    }
                } else if ("4".equals(choice)) {
                    System.out.println("Enter the source node");
                    int src = scn.nextInt();
                    if (src <= 0) {
                        throw new IllegalArgumentException("Source node must be greater than or equal to 1");
                    }

                    System.out.println("Enter the destination node");
                    int dest = scn.nextInt();
                    if (dest <= 0) {
                        throw new IllegalArgumentException("Destination node must be greater than or equal to 1");
                    }

                    int flag = 1;
                    for (Edge e : edges) {
                        if (e.source != src) {
                            flag = 1;
                        } else {
                            flag = 0;
                            break;
                        }
                    }

                    if (flag == 1) {
                        System.out.println("The source node is not exist in our graph");
                    } else {
                        List<Integer> path = g.shortestPath(src, dest, nodes);

                        for (Integer p : path) {
                            if (p == -1) {
                                throw new IllegalArgumentException("You cannot reach to that node");
                            }
                            System.out.print(p + " -> " + " ");
                        }

                        System.out.println();

                    }

                }

                else if ("5".equals(choice)) {
                    break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error occurs: " + e.getMessage());
        }

    }
}