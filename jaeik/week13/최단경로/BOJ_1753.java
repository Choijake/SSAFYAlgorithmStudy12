package week13.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
    static class Node implements Comparable<Node>{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight-o.weight;
        }
    }

    static int[] distance;
    static List<List<Node>> graph;
    static int V, E, K;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0; i<V+1; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        }

        distance = new int[V+1];
        for(int i=1; i<=V; i++){
            distance[i] = INF;
        }

        setDijkstra();

        for(int i=1; i<=distance.length-1; i++){
            if(distance[i]==INF) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    static void setDijkstra(){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(K, 0));
        distance[K] = 0;

        while(!priorityQueue.isEmpty()){
            Node cur = priorityQueue.poll();

            //dp테이블의 값이 현재 노드로의 가중치보다 작다면 이미 작은 경로 길이로 업데이트 된 것이기 때문에 굳이 다시 탐색할 필요 없다
            if(cur.weight>distance[cur.index])continue;

            for(int i=0; i<graph.get(cur.index).size(); i++){
                Node next = graph.get(cur.index).get(i);
                if(distance[next.index] > cur.weight + next.weight){
                    distance[next.index] = cur.weight + next.weight;
                    priorityQueue.add(new Node(next.index, distance[next.index]));
                }
            }
        }
    }
}
