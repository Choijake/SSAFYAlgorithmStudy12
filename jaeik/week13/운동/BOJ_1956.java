package week13.운동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956 {
    static int result;
    static final int INF = 900000001;
    static int V, E;
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[V+1][V+1];
        for(int i=1; i<=V; i++){
            Arrays.fill(distance[i], INF);
        }

        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                if(i==j)distance[i][j]=0;
            }
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            distance[start][end] = length;
        }

        floyd();

        result = INF;
        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                if(i==j)continue;
                if(distance[i][j] == INF || distance[j][i]==INF)continue;
                result = Math.min(result, distance[i][j] + distance[j][i]);

            }
        }

        result = (result==INF)?-1:result;
        System.out.println(result);
    }

    static void floyd(){
        for(int k=1; k<=V; k++){
            for(int i=1; i<=V; i++){
                for(int j=1; j<=V; j++){
                    if(distance[i][j] > distance[i][k]+distance[k][j]){
                        distance[i][j] = distance[i][k]+distance[k][j];
                    }
                }
            }
        }
    }
}
