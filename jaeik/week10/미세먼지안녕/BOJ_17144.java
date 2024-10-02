package week10.미세먼지안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17144 {
    static class Cleaner{
        int row;
        int col;

        public Cleaner(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static int R, C, T;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static int[][] copyMap;
    static List<Cleaner> cleaners;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        cleaners = new ArrayList<>();
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1){
                    cleaners.add(new Cleaner(i, j));
                }
            }
        }

        copyMap = new int[R][C];
        clean();

        int total = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j]!=-1){
                    total += map[i][j];
                }
            }
        }

        System.out.println(total);
    }

    static void clean(){
        while(T-->0){
            copy();

            //먼지 확산
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(map[i][j]!=-1 && map[i][j]!=0){
                        int dust = copyMap[i][j]/5;
                        spread(i, j, dust);
                    }
                }
            }

            //공기 순환
            circulate();
        }
    }

    static void circulate(){
        Cleaner up = cleaners.get(0);
        int r = up.row;

        //상
        int temp = map[0][0];
        for(int i=0; i<C-1; i++){
            map[0][i] = map[0][i+1];
        }
        //우
        for(int i=0; i<r; i++){
            map[i][C-1] = map[i+1][C-1];
        }
        //하
        for(int i=C-1; i>0; i--){
            map[r][i] = map[r][i-1];
        }
        for(int i=r; i>2; i--){
            map[i][0] = map[i-1][0];
        }
        map[1][0] = temp;


        Cleaner down = cleaners.get(1);
        r = down.row;
        temp = map[r][0];
        for(int i=r; i<R-1; i++){
            map[i][0] = map[i+1][0];
        }

        for(int i=0; i<C-1; i++){
            map[R-1][i] = map[R-1][i+1];
        }

        for(int i=R-1; i>r; i--){
            map[i][C-1] = map[i-1][C-1];
        }

        for(int i=C-1; i>2; i--){
            map[r][i] = map[0][i-1];
        }
        map[0][1] = temp;
    }

    static void spread(int row, int col, int dust){
        int count = 0;
        for(int i=0; i<4; i++){
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if(nextRow<0 || nextCol<0 || nextRow>=R || nextCol>=C)continue;
            if(map[nextRow][nextCol]==-1)continue;

            map[nextRow][nextCol] += dust;
            count++;
        }
        map[row][col] -= (count*dust);
    }

    static void copy(){
        for(int i=0; i<R; i++){
            copyMap[i] = Arrays.copyOf(map[i], C);
        }
    }
}
