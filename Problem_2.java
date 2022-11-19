package Assignment_Java_19Nov.Problem_2;

import java.util.LinkedList;
import java.util.Scanner;

public class Problem_2 {
    static class Edge{
        String source;
        String destination;
        int weight;
        Edge(String source,String destination,int weight)
        {
            this.source=source;
            this.destination=destination;
            this.weight=weight;
        }
    }
    static class Graph{
        int vertices;
        LinkedList<Edge>[] adj;

        int paths;

        int totalDistance=0;
        Graph(int vertices)
        {
            this.vertices=vertices;
            adj=new LinkedList[vertices];
            for(int i=0;i<vertices;i++)
            {
                adj[i]=new LinkedList<>();
            }
        }
        public void addEdge(String source,String destination,int weight)
        {
            Edge e1=new Edge(source,destination,weight);
            Edge e2=new Edge(destination,source,weight);
            int index1=(int)(source.charAt(0))-65;
            int index2=(int)(destination.charAt(0))-65;
            adj[index1].addFirst(e1);
            adj[index2].addFirst(e2);
        }
        public double calculateAverageDistanceBetweenTwoPoints(String a,String b)
        {
            boolean visited[]=new boolean[vertices];
            for(int i=0;i<vertices;i++)
                visited[i]=false;
            paths=0;
            totalDistance=0;
            visited[(int)(a.charAt(0))-65]=true;
            calculate(a,b,visited,0);
            return ((double)totalDistance/(double) paths);
        }
        public void calculate(String a,String b,boolean visited[],int dis)
        {
            if(a.equals(b))
            {
                paths++;
                totalDistance+=dis;
                return;
            }
            int index=(int)(a.charAt(0))-65;
            for(int i=0;i<adj[index].size();i++)
            {
                String to=adj[index].get(i).destination;
                int indexOfDestination=(int)(to.charAt(0))-65;
                if(visited[indexOfDestination]==false)
                {
                    visited[indexOfDestination]=true;
                    calculate(to,b, visited, dis+adj[index].get(i).weight);
                    visited[indexOfDestination]=false;
                }
            }
            return;
        }
    }
    public static void main(String[] args)
    {
        Graph g=new Graph(5);
        g.addEdge("A","B",12);
        g.addEdge("A","C",13);
        g.addEdge("A","D",11);
        g.addEdge("A","E",8);
        g.addEdge("B","C",3);
        g.addEdge("C","E",4);
        g.addEdge("D","E",7);
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Node One");
        String a=sc.next();
        System.out.println("Enter Node Two");
        String b= sc.next();
        double avgDis=g.calculateAverageDistanceBetweenTwoPoints(a,b);
        System.out.println("Average Distance between them is "+avgDis);
    }
}
