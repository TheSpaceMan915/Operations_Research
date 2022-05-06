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
            { breadthFirstSearch(graph,vert); }
        }
    }

    public void breadthFirstSearch(Directed_Graph graph_obj, int source)
    {
        Queue<Integer> queue_obj = new LinkedList<>();

        queue_obj.add(source);
        m_arr_flags[source] = true;

        while (!queue_obj.isEmpty())
        {

            int vert = queue_obj.remove();
            m_preorder_vert.add(vert);

            for (int w : graph_obj.getAdjacencyVertices(vert))
            {
                if (!m_arr_flags[w])
                {
                    queue_obj.add(w);
                    m_arr_flags[w] = true;
                }
                else
                {
                    m_postorder_vert.add(w);
                    m_reverse_vert.push(w);
                }
            }
        }
    }

    public Queue<Integer> getPreorder() { return m_preorder_vert; }
    public Queue<Integer> getPostorder() { return m_postorder_vert; }
    public Stack<Integer> getReverse() { return m_reverse_vert; }

    public static void main (String[] args)
    {
        final int vert_numb = 13;

        Directed_Graph graph_obj = new Directed_Graph(vert_numb);
        File path = new File("C:\\Users\\nikit\\IdeaProjects\\Operations_Research\\src\\lab1\\strongly_connected_component\\Data2.txt");
        graph_obj.fillGraph(path);

        Order ord_obj = new Order(graph_obj);
        System.out.println("Check");
    }
}
