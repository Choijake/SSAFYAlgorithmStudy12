package week10.행성터널;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2887 {
    static class Vertex{
        int number;
        int x;
        int y;
        int z;

        public Vertex(int number, int x, int y, int z){
            this.number = number;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight-o.weight;
        }
    }

    static int n;
    static int[] parent;
    static Vertex[] vertexs;
    static List<Edge> edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        vertexs = new Vertex[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            vertexs[i] = new Vertex(i, x, y, z);
        }

        edgeList = new ArrayList<>();

        Arrays.sort(vertexs, (v1, v2) -> v1.x-v2.x);
        for(int i=0; i<n-1; i++){
            int weight = Math.abs(vertexs[i].x-vertexs[i+1].x);
            edgeList.add(new Edge(vertexs[i].number, vertexs[i+1].number, weight));
        }

        Arrays.sort(vertexs, (v1, v2) -> v1.y-v2.y);
        for(int i=0; i<n-1; i++){
            int weight = Math.abs(vertexs[i].y-vertexs[i+1].y);
            edgeList.add(new Edge(vertexs[i].number, vertexs[i+1].number, weight));
        }

        Arrays.sort(vertexs, (v1, v2) -> v1.z-v2.z);
        for(int i=0; i<n-1; i++){
            int weight = Math.abs(vertexs[i].z-vertexs[i+1].z);
            edgeList.add(new Edge(vertexs[i].number, vertexs[i+1].number, weight));
        }

        Collections.sort(edgeList);
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }

        int result = 0;
        for(int i=0; i<edgeList.size(); i++){
            Edge edge = edgeList.get(i);

            if(find(edge.start)==find(edge.end))continue;

            union(edge.start, edge.end);
            result += edge.weight;
        }

        System.out.println(result);
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
