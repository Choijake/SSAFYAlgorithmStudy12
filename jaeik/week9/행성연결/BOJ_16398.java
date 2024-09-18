package week9.행성연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16398 {
    static class Edge implements Comparable<Edge>{
        int vertex;
        int weight;

        public Edge(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    static int n;
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];

        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int weight = Integer.parseInt(st.nextToken());
                graph[i].add(new Edge(j, weight));
                graph[j].add(new Edge(i, weight));
            }
        }

        long result = prim(0);

        System.out.println(result);
    }

    static long prim(int start){
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        long sum = 0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int vertex = edge.vertex;
            int weight = edge.weight;

            if(visited[vertex])continue;
            visited[vertex] = true;
            sum += weight;

            for(Edge e : graph[vertex]){
                if(visited[e.vertex])continue;
                pq.add(e);
            }
        }

        return sum;
    }
}
/**
 * 첫 정점부터 시작하여 pq에 넣고 방문 여부를 검사해주면서 신장 트리를 만든다
 * 이 과정에서 현재 방문 중인 노드의 인접 노드 객체가 pq에 있어도 최소 신장 트리를 만드는데 상관이 없다
 * pq내에 같은 노드가 여러 개 있어도 pq에 넣을 때 weight가 작은 노드가 루트 노드로 업데이트 되고 그 노드가 연결되면서 방문 체크를 해주기 때문이다
 */
