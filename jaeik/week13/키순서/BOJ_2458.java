package week13.키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {
    //INF=Integer.MAX_VALUE; 이렇게 설정해두면 distance[i][k]+distance[k][j] 과정에서 오버플로우 발생할 수 있음
    static final int INF = 10000;
    static int[][] distance;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //i와j가 같은 경우 아니면 INF 거리를 초기화
        distance = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j)distance[i][j] = 0;
                else distance[i][j] = INF;
            }
        }

        //a->b 즉 a<b인 경우 1로 거리를 초기화
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            distance[a][b] = 1;
        }

        //플로이드 알고리즘으로 최단거리를 구해줌
        floyd();


        //키 순서를 알려면 자신보다 자신보다 작은 사람의 수와 자신보다 큰 사람의 수를 더한 값이 N-1이어야한다
        //즉, 어떠한 경로로 자신의 노드에 도착할 수 있는 노드의 수 + 자신의 노드에서 출발해 어떠한 경로로 도착할 수 있는 노드가 N-1이어야한다
        int total = 0; //자신의 키 순서를 알 수 있는 학생 수를 저장하기 위한 변수
        for(int k=1; k<=N; k++){
            int count = 0; //K노드에 도착할 수 있는 노드의 수와 K노드에서 출발해 도착할 노드 수의 합을 저장할 변수
            //도착할 수 있는 경로가 없어서 업데이트 되지 않은 값은 건너뛴다
            //i노드에서 출발해 k노드에 도착하는 경로가 존재하는 경우 count++
            for(int i=1; i<=N; i++){
                if(distance[i][k]!=INF && distance[i][k]>=1)count++;
            }
            //도착할 수 있는 경로가 없어서 업데이트 되지 않은 값은 건너뛴다
            //k노드에서 출발해 i노드에 도착하는 경로가 존재하는 경우 count++
            for(int j=1; j<=N; j++){
                if(distance[k][j]!=INF && distance[k][j]>=1)count++;
            }
            //카운트가 N-1이면 total을 +1
            if(count==N-1)total++;
        }

        System.out.println(total);
    }

    static void floyd(){
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }
}
