package lab2_Kruskal_Algorithm;

import java.io.File;
import java.util.*;

public class Minimum_Spanning_Tree {

    private ArrayList<Edge> m_mst = new ArrayList<>();

    public Minimum_Spanning_Tree(Weighted_Graph graph)
    {
        Queue<Edge> queue_edges = new LinkedList<>(graph.getAllEdges());       //put all edges in the queue
        UnionFind union_obj = new UnionFind(graph.getVerticesNumber());

        //do while there are edges in the queue, and we don't have max possible number of edges in the tree
        while(!queue_edges.isEmpty() && m_mst.size() < graph.getVerticesNumber() - 1)
        {
            Edge edge = queue_edges.remove();
            int v1 = edge.either();
            int v2 = edge.other(v1);
            if (!union_obj.connected(v1,v2))        //if there is no path from v1 to v2, unite them
            {
                union_obj.union(v1,v2);
                m_mst.add(edge);                   //add the edge to the mst
            }
        }
    }

    public ArrayList<Edge> getTree() { return m_mst; }

    public double sumWeight()
    {
        double sum = 0.0;

        for (Edge e: m_mst)
        { sum += e.getWeight(); }
        return sum;
    }

    public static void main (String[] args)
    {
        final int vert_numb = 8;
        Weighted_Graph graph_obj = new Weighted_Graph(vert_numb);
        File file = new File("C:\\Users\\nikit\\IdeaProjects\\Operations_Research\\src\\lab2_Kruskal_Algorithm\\Edges.txt");
        graph_obj.fillGraph(file);

        Minimum_Spanning_Tree tree = new Minimum_Spanning_Tree(graph_obj);
        ArrayList<Edge> res_tree = tree.getTree();

        System.out.println("----------------------------------");
        System.out.printf("%10s %10s %10s","Vertex1","Vertex2","Weight" + '\n');
        System.out.println("----------------------------------");

        for (Edge e : res_tree)
        { e.print();}
        double weight = tree.sumWeight();
        System.out.println("The total weight is " + weight);
    }
}
