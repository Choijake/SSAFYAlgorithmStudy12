package week13.미확인도착지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9370 {
    static class Node implements Comparable<Node>{
        int index;
        int length;

        public Node(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public int compareTo(Node o) {
            return length-o.length;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, M, T, S, G, H;
    static List<List<Node>> graph;
    static int[] distance;
    static List<Integer> candidate;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int tc=0; tc<TC; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            distance = new int[N+1];
            Arrays.fill(distance, INF);
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++){
                graph.add(new ArrayList<>());
            }

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());

                if((start==G&&end==H) || start==H&&end==G){
                    graph.get(start).add(new Node(end, length*2-1));
                    graph.get(end).add(new Node(start, length*2-1));
                }
                else {
                    graph.get(start).add(new Node(end, length*2));
                    graph.get(end).add(new Node(start, length*2));
                }
            }

            candidate = new ArrayList<>();
            for(int i=0; i<T; i++){
                candidate.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(candidate);

            setDistance();

            StringBuilder sb = new StringBuilder();
            for (int idx : candidate){
                if(distance[idx]==INF)continue;
                if(distance[idx]%2==1){
                    sb.append(idx).append(" ");
                }
            }

            System.out.println(sb);
        }
    }

    static void setDistance(){
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(S, 0));
        distance[S] = 0;

        while(!priorityQueue.isEmpty()){
            Node cur = priorityQueue.poll();

//            if(visited[cur.index])continue;
//            visited[cur.index] = true;
            if(distance[cur.index]< cur.length)continue;

            for(int i=0; i<graph.get(cur.index).size(); i++){
                Node next = graph.get(cur.index).get(i);

                if(distance[next.index] > cur.length+ next.length){
                    distance[next.index] = cur.length+ next.length;
                    priorityQueue.add(new Node(next.index, distance[next.index]));
                }
            }
        }
    }
}