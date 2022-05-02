package lab2_Kruskal_Algorithm;

public class Edge implements Comparable<Edge> {

    private final int m_vert1;
    private final int m_vert2;
    private final double m_weight;

    public Edge(int vert1, int vert2, double weight)
    {
        m_vert1 = vert1;
        m_vert2 = vert2;
        m_weight = weight;
    }

    public int either() { return m_vert1; }         //get one of the vertices

    public int other(int vert)                     //get another vertex if I've got one
    {
        if (vert == m_vert1) return m_vert2;
        else return m_vert1;
    }

    @Override
    public int compareTo(Edge another)                    //compare two edges by their weight
    {
        if (m_weight < another.m_weight) return -1;
        else if (m_weight > another.m_weight) return 1;
        else return 0;
    }
}
