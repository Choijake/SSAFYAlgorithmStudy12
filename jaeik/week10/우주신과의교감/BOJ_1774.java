package week10.우주신과의교감;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1774 {
    static class Vertex{
        int x;
        int y;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(weight < o.weight)return -1;
            return 1;
        }
    }
    static Vertex[] vertices;
    static int[] parent;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        vertices = new Vertex[n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            vertices[i] = new Vertex(x, y);
        }


        parent = new int[n+1];
        for (int i=1; i<=n; i++){
            parent[i] = i;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            union(start, end);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=1; i<vertices.length-1; i++){
            Vertex vertex1 = vertices[i];
            int x1 = vertex1.x;
            int y1 = vertex1.y;

            for(int j=i+1; j<vertices.length; j++){
                Vertex vertex2 = vertices[j];
                int x2 = vertex2.x;
                int y2 = vertex2.y;

                double weight = Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));

                pq.add(new Edge(i, j, weight));
            }
        }

        double result = 0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(find(edge.start)==find(edge.end))continue;

            union(edge.start, edge.end);

            result+= edge.weight;
        }

        System.out.println(String.format("%.2f", result));
    }

    static int find(int x){
        if(x==parent[x])return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y)return;

        parent[y] = x;
    }
}
