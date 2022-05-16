package lab3_Dijkstra_Algorithm;

public class ShortestPaths {

    private MinIndexPQ<Double> priority_queue;
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public ShortestPaths(DirectedWeightedGraph graph, int source)
    {
        priority_queue = new MinIndexPQ(graph.getVerticesNumber());
        edgeTo = new DirectedEdge[graph.getVerticesNumber()];
        distTo = new double[graph.getVerticesNumber()];

        for (int j = 0; j < graph.getVerticesNumber(); j++)
        {
           distTo[j] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;

        priority_queue.insert(source,0.0);
        while(!priority_queue.isEmpty())
        {
            int vert = priority_queue.delMin();

            for (DirectedEdge edge : graph.getAdjacencyEdges(vert))
            {
                checkDist(edge);
            }
        }

    }

    public void checkDist(DirectedEdge edge)
    {
        int start = edge.getStart();
        int end = edge.getEnd();

        if (distTo[end] > distTo[start] + edge.getWeight())
        {
            distTo[end] = distTo[start] + edge.getWeight();
            edgeTo[end] = edge;

            if (priority_queue.contains(end))
            { priority_queue.decreaseKey(end,distTo[end]); }
            else
            { priority_queue.insert(end,distTo[end]); }
        }
    }

    public void printShortestPaths()
    {
        System.out.println("----------------------------------");
        System.out.printf("%15s %15s","Vertex","Distance" + '\n');
        System.out.println("----------------------------------");

        for (int i = 0; i < distTo.length; i++)
        {
            System.out.printf("%15s %15s",i,distTo[i]);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        final int vert_numb = 8;
        DirectedWeightedGraph graph_obj = new DirectedWeightedGraph(vert_numb);

        ShortestPaths paths_obj = new ShortestPaths(graph_obj,0);
        paths_obj.printShortestPaths();
    }
}
