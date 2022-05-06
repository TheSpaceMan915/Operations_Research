package lab1.connected_component;

import java.util.*;

public class Connected_Component {

    private boolean[] m_arr_flags;
    private int[] m_arr_id;
    private int m_counter;

    public Connected_Component(Graph graph_obj)
    {
        m_arr_flags = new boolean[graph_obj.getVerticesNumber()];
        m_arr_id = new int[graph_obj.getVerticesNumber()];

        for (int vert = 0; vert < graph_obj.getVerticesNumber(); vert++)
        {
            if (!m_arr_flags[vert])
            {
                breadthFirstSearch(graph_obj,vert);
                m_counter++;
            }
        }
    }

    public void breadthFirstSearch(Graph graph_obj, int source)
    {
        Queue<Integer> queue_obj = new LinkedList<>();

        queue_obj.add(source);
        m_arr_flags[source] = true;
        m_arr_id[source] = m_counter;

        while (!queue_obj.isEmpty())
        {
            int vert = queue_obj.remove();
            for (int w : graph_obj.getAdjacencyVertices(vert))
            {
                if (!m_arr_flags[w])
                {
                    queue_obj.add(w);
                    m_arr_flags[w] = true;
                    m_arr_id[w] = m_counter;
                }
            }
        }
    }

    public static void main (String[] args)
    {
        final int vert_numb = 8;

        Graph graph1 = new Graph(vert_numb);
        Connected_Component component = new Connected_Component(graph1);

        System.out.println("Click");

    }
}
