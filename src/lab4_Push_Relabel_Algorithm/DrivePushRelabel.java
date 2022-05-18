package lab4_Push_Relabel_Algorithm;


public class DrivePushRelabel {

    public static void main(String[] args)
    {
        PushRelabel pr = new PushRelabel(5);
        pr.addEdge(0, 1, 12);              //source
        pr.addEdge(0, 2, 14);
        pr.addEdge(1,2,5);
        pr.addEdge(2,3,8);
        pr.addEdge(3,1,7);
        pr.addEdge(3,4,10);
        pr.addEdge(1,4,16);                 //sink

        System.out.println("The max flow is " + pr.getMaxFlow());
    }

}