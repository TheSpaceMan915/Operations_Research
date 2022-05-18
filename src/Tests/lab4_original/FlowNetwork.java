package Tests.lab4_original;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//it's not working, get an infinite cycle

public class FlowNetwork {

    private final int m_vertices_number;
    private final List<Vertex> m_arr_vertices;
    private final List<Edge> m_arr_all_edges;

    public FlowNetwork(int vertices_number)
    {
        m_vertices_number = vertices_number;
        m_arr_all_edges = new ArrayList<>();
        m_arr_vertices = new ArrayList<>();

        for (int j = 0; j < m_vertices_number; j++)
        {
            m_arr_vertices.add(new Vertex(0,0));          //create the vertices
        }

        File path = new File("C:\\Users\\nikit\\IdeaProjects\\Operations_Research\\src\\lab4_Push_Relabel_Algorithm\\Network.txt");
        this.fillNetwork(path);
    }

    private void fillNetwork(File file_obj)
    {
        try
        {
            FileReader out_stream = new FileReader(file_obj.getPath());
            BufferedReader fout = new BufferedReader(out_stream);

            String temp = "";
            String[] arr_vertices_str;
            int vertex_A, vertex_B, capacity;
            final int flow = 0;                                             //after the network is created, the flow in each edge is 0
            while ((temp = fout.readLine()) != null)
            {
                arr_vertices_str = temp.split(" ");
                vertex_A = Integer.parseInt(arr_vertices_str[0]);
                vertex_B = Integer.parseInt(arr_vertices_str[1]);
                capacity = Integer.parseInt(arr_vertices_str[2]);
                addEdge(vertex_A,vertex_B,capacity,flow);
            }

            out_stream.close();
        }
        catch (IOException exep)
        { exep.printStackTrace(); }
    }

    public void addEdge(int vert1, int vert2, int capacity, int flow)
    {
        m_arr_all_edges.add(new Edge(vert1,vert2,flow,capacity));
    }

    public void preFlow(int source)
    {
        m_arr_vertices.get(source).m_height = m_arr_vertices.size();
        //make the height of the source equal to number of vertices

        for (int i = 0; i < m_arr_all_edges.size(); i++)
        {
            if (m_arr_all_edges.get(i).m_vert1 == source)
            {
                m_arr_all_edges.get(i).m_flow = m_arr_all_edges.get(i).m_capacity;
                //make the flow of the edge equal to the capacity of the edge

                m_arr_vertices.get(m_arr_all_edges.get(i).m_vert2).m_excess_flow += m_arr_all_edges.get(i).m_flow;
                //set excess flow for adjacent vertices

                addEdge(m_arr_all_edges.get(i).m_vert2, source, 0, -m_arr_all_edges.get(i).m_flow);
                //add an edge from vert2 (the end of the edge) to the source
            }
        }
    }

    public int checkOverFlow()
    {
        int res = -1;

        for (int i = 1; i < m_arr_vertices.size() - 1; i++)
        {
            if (m_arr_vertices.get(i).m_excess_flow > 0)
            { res = i; }
        }
        return res;             //return the index of the overflowing vertex if there is one.
    }                           //otherwise, return -1

    public void updateReverseEdgeFlow(int ind_edge, int flow)       //update reverse flow that was added to the edge
    {
        int vert_start = m_arr_all_edges.get(ind_edge).m_vert2;
        int vert_end = m_arr_all_edges.get(ind_edge).m_vert1;

        for (int i = 0; i < m_arr_all_edges.size(); i++)
        {
            if (m_arr_all_edges.get(i).m_vert2 == vert_end && m_arr_all_edges.get(i).m_vert1 == vert_start)
                {
                    m_arr_all_edges.get(i).m_flow -= flow;
                    return;
                }
        }

        addEdge(vert_start,vert_end,0,flow);      //add an edge in the residual graph
    }


    public boolean push(int vert)
    {
        for (int j = 0; j < m_arr_all_edges.size(); j++)
        {
            if (m_arr_all_edges.get(j).m_vert1 == vert)     //check if there is an edge with this vert
            {
                if (m_arr_all_edges.get(j).m_flow == m_arr_all_edges.get(j).m_capacity)
                { continue; }
                //if it is not possible to push more water through the edge, skip the edge


                //check if vert.height > adjacentVert.height
                if (m_arr_vertices.get(vert).m_height > m_arr_vertices.get(m_arr_all_edges.get(j).m_vert2).m_height)
                {
                    int remaining_flow = m_arr_all_edges.get(j).m_capacity - m_arr_all_edges.get(j).m_flow;
                    int pushed_flow = Math.min(remaining_flow, m_arr_vertices.get(vert).m_excess_flow);
                    //choose what amount of water to push. It should be min

                    m_arr_vertices.get(vert).m_excess_flow -= pushed_flow;
                    //decrease excess flow for an overflowing vertex

                    m_arr_vertices.get(m_arr_all_edges.get(j).m_vert2).m_excess_flow += pushed_flow;
                    //increase excess flow for an adjacent vertex

                    //add flow in reverse edge
                    m_arr_all_edges.get(j).m_flow += pushed_flow;

                    updateReverseEdgeFlow(j, pushed_flow);

                    return true;
                }
            }
        }
        return false;
    }


    public void relabel(int vert)
    {
        int min_height = Integer.MAX_VALUE;

        for (int j = 0; j < m_arr_all_edges.size(); j++)
        {
            if (m_arr_all_edges.get(j).m_vert1 == vert)     //find the right vert to relabel
            {
                if (m_arr_all_edges.get(j).m_flow == m_arr_all_edges.get(j).m_capacity) {
                    continue;
                }


                if (m_arr_vertices.get(m_arr_all_edges.get(j).m_vert2).m_height < min_height) {
                    min_height = m_arr_vertices.get(m_arr_all_edges.get(j).m_vert2).m_height;
                    //set min height in the network

                    m_arr_vertices.get(vert).m_height = min_height + 1;
                    //set a new height to the vertex that is being relabeled
                }
            }
        }
    }


    public int maxFlow(int source, int sink)
    {
        preFlow(source);
        int check_vert = 0;

        while((check_vert = checkOverFlow()) != -1)
        {
            if (!push(check_vert))
            { relabel(check_vert); }
        }

        return m_arr_vertices.get(m_arr_vertices.size() - 1).m_excess_flow;
    }

    public static void main (String[] args)
    {
        int number_vert = 6;
        FlowNetwork network_obj = new FlowNetwork(number_vert);
        int source = 0, sink = 5;       // Initialize source and sink

        System.out.println(network_obj.maxFlow(source,sink));
    }
}
