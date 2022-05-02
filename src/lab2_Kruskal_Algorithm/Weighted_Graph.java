package lab2_Kruskal_Algorithm;

import java.util.ArrayList;

public class Weighted_Graph {

    private final int m_vertices_number;
    private final ArrayList<Edge>[] m_arr_lists;

    public Weighted_Graph(int vertices_number)
    {
        m_vertices_number = vertices_number;
        m_arr_lists = (ArrayList<Edge>[]) new ArrayList[m_vertices_number];

        for (int i = 0; i < m_vertices_number; i++)
        { m_arr_lists[i] = new ArrayList<>(); }
    }


}
