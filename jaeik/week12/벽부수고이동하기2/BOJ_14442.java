package week12.벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
    static class Wall{
        int row;
        int col;
        int length;
        int destroyCount;

        public Wall(int row, int col, int length, int destroyCount) {
            this.row = row;
            this.col = col;
            this.length = length;
            this.destroyCount = destroyCount;
        }
    }

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            String line = br.readLine();
            for(int j=1; j<=M; j++){
                map[i][j] = Character.getNumericValue(line.charAt(j-1));
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        int result = 0;
        Queue<Wall> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N+1][M+1][K+1];
        queue.add(new Wall(1, 1, 1, 0));
        visited[1][1][0] = true;

        while(!queue.isEmpty()){
            Wall now = queue.poll();

            if(now.row==N && now.col==M){
                result = now.length;
                break;
            }

            for(int i=0; i<4; i++){
                int nextRow = now.row+dr[i];
                int nextCol = now.col+dc[i];

                if(nextRow<1 || nextCol<1 || nextRow>N || nextCol>M)continue;

                if(map[nextRow][nextCol]==1 && now.destroyCount<K && !visited[nextRow][nextCol][now.destroyCount+1]){
                    queue.add(new Wall(nextRow, nextCol, now.length+1, now.destroyCount+1));
                    visited[nextRow][nextCol][now.destroyCount+1] = true;
                }

                if(map[nextRow][nextCol]==0 && !visited[nextRow][nextCol][now.destroyCount]){
                    queue.add(new Wall(nextRow, nextCol, now.length+1, now.destroyCount));
                    visited[nextRow][nextCol][now.destroyCount] = true;
                }
            }
        }

        return result==0?-1:result;
    }
}