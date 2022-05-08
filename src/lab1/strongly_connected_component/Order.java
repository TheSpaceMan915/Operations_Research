package lab1.strongly_connected_component;

import lab1.connected_component.Graph;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Order {

    private boolean[] m_arr_flags;
    private Queue<Integer> m_preorder_vert;           //vertices in their initial order
    private Queue<Integer> m_postorder_vert;          //vertices that were removed from the first queue
    private Stack<Integer> m_reverse_vert;            //vertices that were already visited form the Stack

    public Order(Directed_Graph graph)
    {
        m_arr_flags = new boolean[graph.getVerticesNumber()];
        m_preorder_vert = new LinkedList<>();
        m_postorder_vert = new LinkedList<>();
        m_reverse_vert = new Stack<>();

        for (int vert = 0; vert < graph.getVerticesNumber(); vert++)
        {
            if (!m_arr_flags[vert])
            { depthFirstSearch(graph,vert); }
        }
    }

    public void depthFirstSearch(Directed_Graph graph_obj, int vert)
    {
        m_preorder_vert.add(vert);
        m_arr_flags[vert] = true;
        for (int w : graph_obj.getAdjacencyVertices(vert))
        { if (!m_arr_flags[w]) depthFirstSearch(graph_obj,w); }

        m_postorder_vert.add(vert);
        m_reverse_vert.push(vert);
    }

    public Queue<Integer> getPreorder() { return m_preorder_vert; }
    public Queue<Integer> getPostorder() { return m_postorder_vert; }
    public Stack<Integer> getReverse() { return m_reverse_vert; }

    public static void main (String[] args)
    {
    }
}
