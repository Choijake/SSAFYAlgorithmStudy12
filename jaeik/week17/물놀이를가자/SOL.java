package week17.물놀이를가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SOL {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    static int[][] distance;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            Queue<int[]> queue = new ArrayDeque<>();
            for(int i=0; i<N; i++){
                String line = br.readLine();
                for(int j=0; j<M; j++){
                    map[i][j] = Character.getNumericValue(line.charAt(j));
                    distance[i][j] = 1000000;
                    if(map[i][j]=='W'){
                        queue.add(new int[] {0, i,j});
                        distance[i][j] = 0;
                    }
                }
            }

            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                int dist = cur[0];
                int row = cur[1];
                int col = cur[2];

                for(int i=0; i<4; i++){
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=M)continue;

                    if(dist+1 < distance[nextRow][nextCol]){
                        queue.add(new int[] {dist+1, nextRow, nextCol});
                        distance[nextRow][nextCol] = row+1;
                    }
                }
            }
        }
    }
}
