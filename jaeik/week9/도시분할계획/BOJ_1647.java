package week9.도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 {
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Edge(start, end, weight));
        }

        int sum = 0;
        int max = 0;
        while (!pq.isEmpty()){
            Edge edge = pq.poll();
            if(find(edge.start)==find(edge.end))continue;
            union(edge.start, edge.end);
            sum += edge.weight;
            max = Math.max(max, edge.weight);
        }

        System.out.println(sum-max);
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

/**
 * MST를 만들고 그 중 가장 큰 간선을 뺀다
 * 54line을 해주지 않으면 union은 일어나지 않아도 sum에 간선의 비용이 더해진다
 */