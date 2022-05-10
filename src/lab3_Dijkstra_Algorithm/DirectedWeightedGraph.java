package lab3_Dijkstra_Algorithm;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DirectedWeightedGraph {

    private final int m_vertices_number;
    private ArrayList<DirectedEdge> m_arr_all_edges;
    private final ArrayList<DirectedEdge>[] m_arr_lists;
    //массив листов c рёбрами. Индекс массива - номер вершины, с которой соединены эти рёбра

    public DirectedWeightedGraph(int vertices_number)
    {
        m_vertices_number = vertices_number;
        m_arr_lists = (ArrayList<DirectedEdge>[]) new ArrayList[m_vertices_number];
        m_arr_all_edges = new ArrayList<>();

        for (int i = 0; i < m_vertices_number; i++)
        { m_arr_lists[i] = new ArrayList<>(); }

        File file = new File("C:\\Users\\nikit\\IdeaProjects\\Operations_Research\\src\\lab3_Dijkstra_Algorithm\\Edges.txt");
        this.fillGraph(file);
    }

    public void addEdge(DirectedEdge edge)          //the edges are added in a different way
    {
        int vert = edge.getStart();

        m_arr_lists[vert].add(edge);
        m_arr_all_edges.add(edge);
    }

    public ArrayList<DirectedEdge> getAdjacencyEdges(int vert) { return m_arr_lists[vert]; }

    public ArrayList<DirectedEdge> getAllEdges() { return m_arr_all_edges; }

    public int getVerticesNumber() { return m_vertices_number; }

    public void fillGraph(File file_obj)
    {
        try
        {
            FileReader out_stream = new FileReader(file_obj.getPath());
            BufferedReader fout = new BufferedReader(out_stream);

            String temp = "";
            String[] arr_words;
            int vertex_A = 0, vertex_B = 0;
            double weight = 0.0;
            while ((temp = fout.readLine()) != null)
            {
                arr_words = temp.split(" ");
                vertex_A = Integer.parseInt(arr_words[0]);
                vertex_B = Integer.parseInt(arr_words[1]);
                weight = Double.parseDouble(arr_words[2]);
                addEdge(new DirectedEdge(vertex_A,vertex_B,weight));
            }

            out_stream.close();
        }
        catch (IOException exep)
        { exep.printStackTrace(); }
    }
}
