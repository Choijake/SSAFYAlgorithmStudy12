package week12.연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142 {
    static class Virus{
        int row;
        int col;
        int time;

        Virus(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    static List<Virus> virusList;
    static int[][] map;
    static Virus[] active;
    static int N, M;
    static int min_time = Integer.MAX_VALUE;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int empty;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        active = new Virus[M];
        virusList = new ArrayList<>();
        empty = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j]==0)empty++;
                else if(map[i][j]==2)virusList.add(new Virus(i, j, 0));
            }
        }

        if (empty == 0) {
            System.out.println(0);
        }else{
            combination(0, 0);
            System.out.println(min_time==Integer.MAX_VALUE?-1:min_time);
        }
    }

    static void combination(int start, int depth){
        if(depth==M){
            spread(empty);
            return;
        }

        for(int i=start; i<virusList.size(); i++){
            active[depth] = virusList.get(i);
            combination(i+1, depth+1);
        }
    }

    static void spread(int empty){
        Queue<Virus> queue = new ArrayDeque<>();
        boolean[][] infected = new boolean[N][N];

        for(int i=0; i< active.length; i++){
            Virus virus = active[i];
            int row = virus.row;
            int col = virus.col;

            queue.add(virus);
            infected[row][col] = true;
        }

        while(!queue.isEmpty()){
            Virus virus = queue.poll();

            for(int i=0; i<4; i++){
                int nextRow = virus.row + dr[i];
                int nextCol = virus.col + dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=N)continue;
                if(infected[nextRow][nextCol] || map[nextRow][nextCol]==1)continue;

                if(map[nextRow][nextCol]==0)empty--;

                if(empty==0){
                    min_time = Math.min(min_time, virus.time+1);
                    return;
                }

                queue.add(new Virus(nextRow, nextCol, virus.time+1));
                infected[nextRow][nextCol] = true;
            }
        }
    }
}
