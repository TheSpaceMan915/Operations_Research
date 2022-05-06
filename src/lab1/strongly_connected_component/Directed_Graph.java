package lab1.strongly_connected_component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Directed_Graph {

    private final int m_vertices_number;            //кол-во вершин
    private int m_edges_number;                     //кол-во рёбер
    private final ArrayList<Integer>[] m_arr_lists;       //массив листов из вершин, которые соединены
                                                          //рёбрами

    //input vertices number with keyboard
    public Directed_Graph(int vertices_number)       //create a graph just with a number of vertices
    {
        m_vertices_number = vertices_number;
        m_arr_lists = (ArrayList<Integer>[]) new ArrayList[vertices_number];

        for (int i = 0; i < vertices_number; i++)
        { m_arr_lists[i] = new ArrayList<>(); }
    }

    public void fillGraph(File file_obj)
    {
        try
        {
            FileReader out_stream = new FileReader(file_obj.getPath());
            BufferedReader fout = new BufferedReader(out_stream);

            String temp = "";
            String[] arr_vertices_str;
            int vertex_A, vertex_B;
            while ((temp = fout.readLine()) != null)
            {
                arr_vertices_str = temp.split(" ");
                vertex_A = Integer.parseInt(arr_vertices_str[0]);
                vertex_B = Integer.parseInt(arr_vertices_str[1]);
                addEdge(vertex_A,vertex_B);
            }

            out_stream.close();
        }
        catch (IOException exep)
        { exep.printStackTrace(); }
    }

    public void addEdge(int vert1,int vert2)        //create an edge
    {
        m_arr_lists[vert1].add(vert2);              //just add vert2 to adj. vertices of vert1
        m_edges_number++;                          //increment the number of edges
    }

   // public ArrayList<Integer> reverse
    public int getVerticesNumber() { return m_vertices_number; }

    public int getEdgesNumber() { return m_edges_number; }

    public ArrayList<Integer> getAdjacencyVertices(int vert) { return m_arr_lists[vert]; }
}
