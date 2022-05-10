package lab3_Dijkstra_Algorithm;

public class DirectedEdge implements Comparable<DirectedEdge> {

    private final int m_vert1;
    private final int m_vert2;
    private final double m_weight;

    public DirectedEdge(int vert1, int vert2, double weight)
    {
        m_vert1 = vert1;
        m_vert2 = vert2;
        m_weight = weight;
    }

    public int getStart() { return m_vert1; }               //get the start of the edge

    public int getEnd() { return m_vert2; }              //get the end of the edge

    @Override
    public int compareTo(DirectedEdge another)                    //compare two edges by their weight
    {
        if (m_weight < another.m_weight) return -1;
        else if (m_weight > another.m_weight) return 1;
        else return 0;
    }

    public double getWeight() { return m_weight; }

    public void print()
    {
        String temp = String.format("%10s %10s %10s",m_vert1,m_vert2,m_weight);
        System.out.println(temp);
    }
}
