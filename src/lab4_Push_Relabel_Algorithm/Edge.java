package lab4_Push_Relabel_Algorithm;

public class Edge {

    private int m_vert1;
    private int m_vert2;
    private int m_flow;
    private int m_capacity;

    public Edge(int vert1, int vert2, int flow, int capacity)
    {
        m_vert1 = vert1;
        m_vert2 = vert2;
        m_flow = flow;
        m_capacity = capacity;
    }

    public int getCapacity() {
        return m_capacity;
    }

    public int getFlow() {
        return m_flow;
    }

    public int getStartVert() {
        return m_vert1;
    }

    public int getEndVert() {
        return m_vert2;
    }
}
