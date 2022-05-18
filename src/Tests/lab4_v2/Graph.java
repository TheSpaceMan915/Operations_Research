package Tests.lab4_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Not sure that it's not working properly
public class Graph{
    int m_number_vertices;
    int m_number_edges;
    Vertex[] m_arr_vertices;
    int[][] m_arr_capacity;
    int[][] m_arr_flow;

    //int m_source;

    //int m_sink;

    public Graph(int number_vertices,int number_edges )
    {
        this.m_number_vertices =number_vertices;
        this.m_number_edges =number_edges;

        m_arr_vertices =new Vertex[number_vertices];

        for(int i=0;i<number_vertices;i++)
        { m_arr_vertices[i]=new Vertex(); }

        m_arr_capacity =new int[number_vertices][number_vertices];
        m_arr_flow =new int[number_vertices][number_vertices];

        for(int i=0;i<number_vertices;i++)                              //after the network is created, the flow in each edge is 0
        {
            for(int j=0;j<number_vertices;j++)
            { m_arr_flow[i][j]=0; }
        }

        File path = new File("C:\\Users\\nikit\\IdeaProjects\\Operations_Research\\src\\lab4_version2\\Network.txt");
        fillGraph(path);
    }

    private void fillGraph(File file_obj)
    {
        try
        {
            FileReader out_stream = new FileReader(file_obj.getPath());
            BufferedReader fout = new BufferedReader(out_stream);

            String temp = "";
            String[] arr_vertices_str;
            int vertex_A, vertex_B, capacity, sink, source;

            /*temp = fout.readLine();
            source = Integer.parseInt(temp);

            temp = fout.readLine();
            sink = Integer.parseInt(temp);

            m_sink = sink;
            m_source =*/

            while ((temp = fout.readLine()) != null)
            {
                arr_vertices_str = temp.split(" ");
                vertex_A = Integer.parseInt(arr_vertices_str[0]);
                vertex_B = Integer.parseInt(arr_vertices_str[1]);
                capacity = Integer.parseInt(arr_vertices_str[2]);
                addEdge(vertex_A,vertex_B,capacity);
            }

            out_stream.close();
        }
        catch (IOException exep)
        { exep.printStackTrace(); }
    }

    void addEdge(int x, int y, int cap) { m_arr_capacity[x][y]=cap; }

    int getOverflowVertex(int s,int t)
    {
        for(int i = 1; i< m_number_vertices -1; i++)
        {
            if(m_arr_vertices[i].excess_flow>0)
            {
                for(int j = 0; j< m_number_vertices; j++)
                {
                    if(m_arr_capacity[i][j]!=0)
                    {
                        if(m_arr_capacity[i][j]!= m_arr_flow[i][j])
                        { return i; }
                    }
                }
            }
        }
        return -1;
    }
    public void preflow(int s)
    {
        m_arr_vertices[s].h= m_number_vertices;

        for(int i = 1; i< m_number_vertices; i++)
        {
            if(m_arr_capacity[s][i]!=0)
            {
                m_arr_flow[s][i]= m_arr_capacity[s][i];
                m_arr_vertices[i].excess_flow+= m_arr_flow[s][i];
                addEdge(i, s, 0);
                m_arr_flow[i][s]=-m_arr_flow[s][i];
            }
        }
    }
    boolean push(int u)
    {
        for(int i = 0; i< m_number_vertices; i++)
        {
            if(m_arr_capacity[u][i]!=0)
            {
                if(m_arr_flow[u][i]== m_arr_capacity[u][i])
                { continue; }

                if(m_arr_vertices[u].h> m_arr_vertices[i].h)
                {
                    int Flow=Math.min(m_arr_capacity[u][i]- m_arr_flow[u][i], m_arr_vertices[u].excess_flow );
                    m_arr_vertices[u].excess_flow-=Flow;
                    m_arr_vertices[i].excess_flow+=Flow;
                    m_arr_flow[u][i]+=Flow;
                    m_arr_flow[i][u]-=Flow;
                    return true;
                }
            }
        }
        return false;
    }
    void relabel(int u)
    {
        System.out.println(u+"preflow");
        int minh=Integer.MAX_VALUE;

        for(int i = 0; i< m_number_vertices; i++)
        {
            if(m_arr_capacity[u][i]!=0)
            {
                if(m_arr_capacity[u][i]== m_arr_flow[u][i])
                { continue; }
                if(m_arr_vertices[i].h<minh)
                {
                    minh = m_arr_vertices[i].h;
                    m_arr_vertices[u].h = minh+1;
                }
            }
        }
    }

    int maxFlow(int s,int t)
    {
        preflow(s);

        int u = getOverflowVertex(s,t);
        while(u!=-1)
        {
            if(!push(u))
            { relabel(u); }
            u=getOverflowVertex(s,t);
        }
        return m_arr_vertices[t].excess_flow;
    }

    public static void main(String[] args)
    {
        final int vert_numb = 6;
        final int edges_numb = 7;
        final int sink = 3;
        final int source = 0;

        Graph graph_obj = new Graph(vert_numb,edges_numb);

        System.out.println("The max flow is : "+graph_obj.maxFlow(source,sink));
    }
}

