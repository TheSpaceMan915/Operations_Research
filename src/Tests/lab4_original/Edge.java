package Tests.lab4_original;

public class Edge {

    int m_vert1;
    int m_vert2;
    int m_flow;
    int m_capacity;

    public Edge(int vert1, int vert2, int flow, int capacity)
    {
        m_vert1 = vert1;
        m_vert2 = vert2;
        m_flow = flow;
        m_capacity = capacity;
    }

   /* public int getCapacity() {
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

    public void setFlow(int flow) {
        m_flow = flow;
    }*/
}
