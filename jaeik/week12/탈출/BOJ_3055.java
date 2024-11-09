package week12.탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    static class Water{
        int row;
        int col;

        public Water(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Hedgehog{
        int row;
        int col;
        int day;

        public Hedgehog(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int R, C;
    static char[][] map;
    static Queue<Water> waters;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        Hedgehog hedgehog = null;
        waters = new ArrayDeque<>();
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j]=='S'){
                    hedgehog = new Hedgehog(i, j, 0);
                }
                if(map[i][j]=='*')waters.add(new Water(i, j));
            }
        }

        int result = bfs(hedgehog);
        if (result == -1) {
            System.out.println("KAKTUS");
        }else{
            System.out.println(result);
        }
    }

    static int bfs(Hedgehog hedgehog){
        int day = 0;
        int result = -1;
        Queue<Hedgehog> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        queue.add(hedgehog);
        visited[hedgehog.row][hedgehog.col] = true;

        flood();
        while(!queue.isEmpty()){
            Hedgehog now = queue.poll();

            if(map[now.row][now.col]=='D'){
                result = now.day;
            }

            if(now.day>day){
                day = now.day;
                flood();
            }

            for(int i=0; i<4; i++){
                int nextRow = now.row+dr[i];
                int nextCol = now.col+dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=R || nextCol>=C)continue;
                if(visited[nextRow][nextCol])continue;
                if(map[nextRow][nextCol]=='*' || map[nextRow][nextCol]=='X')continue;

                queue.add(new Hedgehog(nextRow, nextCol, now.day+1));
                visited[nextRow][nextCol] = true;
            }
        }
        return result;
    }

    static void flood(){
        int size = waters.size();

        for(int i=0; i< size; i++){
            Water now = waters.poll();

            for(int j=0; j<4; j++){
                int nextRow = now.row+dr[j];
                int nextCol = now.col+dc[j];

                if(nextRow<0 || nextCol<0 || nextRow>=R || nextCol>=C)continue;
                if(map[nextRow][nextCol]=='.'){
                    map[nextRow][nextCol]='*';
                    waters.add(new Water(nextRow, nextCol));
                }
            }
        }
    }
}
