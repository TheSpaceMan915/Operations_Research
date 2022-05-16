package lab1.strongly_connected_component;
import java.util.LinkedList;
import java.util.Queue;

public class Strongly_Connected_Component {

    private boolean[] m_arr_flags;
    private int[] m_arr_id;
    private int m_counter = 0;

    public Strongly_Connected_Component(Directed_Graph graph_obj)
    {
        m_arr_flags = new boolean[graph_obj.getVerticesNumber()];
        m_arr_id = new int[graph_obj.getVerticesNumber()];
        Order order_obj = new Order(graph_obj.reverseGraph());

        for (int vert : order_obj.getReverse())
        {
            if (!m_arr_flags[vert])
            {
                breadthFirstSearch(graph_obj,vert);
                m_counter++;
            }
        }
    }

    public void breadthFirstSearch(Directed_Graph graph_obj, int source)
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

    public void printComponents()
    {
        System.out.println("--------------------------");
        System.out.printf("%10s %10s","Vertex","Id" + '\n');
        System.out.println("--------------------------");

        for (int i = 0; i < m_arr_id.length; i++)
        {
            System.out.printf("%10s %10s",i,m_arr_id[i]);
            System.out.println();
        }
    }

    //the starting point is here
    public static void main (String[] args)
    {
        final int vert_numb = 8;

        Directed_Graph graph_obj = new Directed_Graph(vert_numb);
        Strongly_Connected_Component comp_obj = new Strongly_Connected_Component(graph_obj);
        comp_obj.printComponents();

        System.out.println("Click");

    }
}
