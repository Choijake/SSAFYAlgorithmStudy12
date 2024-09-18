package week9.특정거리의도시찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {
    static int N, M, K, X;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }

        dijkstra(X);
    }

    static void dijkstra(int start){
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(distance, INF);
        distance[start] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            int now = queue.poll();

            if(visited[now])continue;
            visited[now]=true;

            for(int next : graph[now]){
                if(distance[next] > distance[now] + 1){
                    distance[next] = distance[now] + 1;
                    queue.offer(next);
                }
            }
        }

        int count = 0;
        for(int i=0; i<distance.length; i++){
            if(distance[i]==K){
                count++;
                System.out.println(i);
            }
        }

        if (count == 0) {
            System.out.println(-1);
        }
    }
}
