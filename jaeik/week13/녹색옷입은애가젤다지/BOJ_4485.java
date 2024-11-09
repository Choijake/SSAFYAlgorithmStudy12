package week13.녹색옷입은애가젤다지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485 {
    static int count=1;
    static class Node implements Comparable<Node> {
        int row;
        int col;
        int rupee;

        public Node(int row, int col, int rupee) {
            this.row = row;
            this.col = col;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Node o) {
            return rupee-o.rupee;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static int[][] distance;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //입력 N이 0일 때까지 프로그램을 실행한다
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N==0)break;

            //map에 도둑 루피를 저장한다
            map = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //출발지에서 각 동굴까지의 최단 경로를 저장하기 위한 dp 배열을 만들고 INF로 초기화한다
            distance = new int[N][N];
            for(int i=0; i<N; i++){
                Arrays.fill(distance[i], INF);
            }
            
            //각 동굴 까지의 최단 경로를 계산하는 함수
            dijkstra(0, 0);

            System.out.println("Problem "+(count++)+": "+distance[N-1][N-1]);
        }
    }

    static void dijkstra(int row, int col){
        //Node.rupee의 값에 따라 우선순위 큐에 Node 객체를 저장한다
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        //경로 방문 여부를 저장하는 배열
        boolean[][] visited = new boolean[N][N];
        priorityQueue.add(new Node(row, col, map[row][col]));

        while(!priorityQueue.isEmpty()){
            Node now = priorityQueue.poll();
            
            //경로를 방문했으면 넘어간다
            if(visited[now.row][now.col])continue;
            visited[now.row][now.col] = true;

            for(int i=0; i<4; i++){
                int nextRow = now.row+dr[i];
                int nextCol = now.col+dc[i];

                if(nextRow<0 || nextCol<0 ||nextRow>=N || nextCol>=N)continue;
                if(visited[nextRow][nextCol])continue;

                //distance 배열에 저장된 도둑 루피의 수 > 현재 동굴 까지 방문한 결과 얻은 도둑 루피의 수 + 다음 방문할 동굴의 도둑 루피 수 일 때
                //distance 배열에 최단 경로를 저장해 주고 우선순위 큐에 Node를 만들어 넣어준다
                if(distance[nextRow][nextCol] > now.rupee + map[nextRow][nextCol]){
                    distance[nextRow][nextCol] = now.rupee + map[nextRow][nextCol];
                    priorityQueue.add(new Node(nextRow, nextCol, distance[nextRow][nextCol]));
                }
            }
        }
    }
}
