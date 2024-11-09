package week14.청소년상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19236 {
    static class Fish implements Comparable<Fish>{
        int row;
        int col;
        int number;
        int direction;

        public Fish(int row, int col, int number, int direction) {
            this.row = row;
            this.col = col;
            this.number = number;
            this.direction = direction;
        }

        @Override
        public int compareTo(Fish o) {
            return number-o.number;
        }
    }

    static class Shark{
        int row;
        int col;
        int score;
        int direction;

        public Shark(int row, int col, int score, int direction) {
            this.row = row;
            this.col = col;
            this.score = score;
            this.direction = direction;
        }
    }

    static boolean hasWay;
    static Shark shark;
    static int result;
    static int[] dr = {-2, 0, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {-2, -1, -1, -1, -1, 0, 1, 1, 1};
    static Fish[] fish;
    static Fish[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new Fish[4][4];
        fish = new Fish[17];
        for (int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                map[i][j] = new Fish(i, j, num, dir);

                if(i==0&&j==0)continue;
                fish[num] = map[i][j];
            }
        }

        result = 0;
        simulate();
    }

    static void simulate(){
        hasWay = true;
        placeShark();

        while(hasWay){
            fishMove();
            sharkMove();
        }
    }

    static void placeShark(){
        //상어를 (0, 0)에 배치 후, 물고기 객체 없엔다
        shark = new Shark(0, 0, map[0][0].number, map[0][0].direction);
        map[0][0] = null;
    }

    static void sharkMove(){

    }

    static void fishMove(){
        //pq가 차있을 동안 pq에서 fish를 꺼낸다

            //pq에서 나온 물고기가 이동한다
                //dir부터 for 문을 돌면서 물고기 장소 + 물고기의 방향 장소가 빈칸 또는 다른 물고기가 있을 때
                    //칸의 물고기를 교환한다
                //이동할 수 없을 때 continue;

        //Arrays.sort(fish);

        for(int i=1; i<fish.length; i++){
            Fish cur = fish[i];

            if(cur==null)continue;

            for(int j=cur.direction; j<cur.direction+8; j++){
                int idx = (j==8)?8:j%8;

                int nextRow = cur.row + dr[idx];
                int nextCol = cur.col + dc[idx];

                if(nextRow<0 || nextCol<0 || nextRow>=4 || nextCol>=4)continue;
                //다음 칸에 상어가 있을 경우
                if(nextRow==shark.row && nextCol==shark.col)continue;

                //다음 칸이 비었거나, 물고기가 있는 경우
                Fish next = map[nextRow][nextCol];


                System.out.println(next.number);

                map[cur.row][cur.col] = new Fish(cur.row, cur.col, next.number, next.direction);
                map[nextRow][nextCol] = new Fish(nextRow, nextCol, cur.number, idx);

                fish[next.number].row = cur.row;
                fish[next.number].col = cur.col;
                fish[cur.number].row = nextRow;
                fish[cur.number].col = nextCol;

                extracted();

                break;
            }
        }
    }
    private static void extracted() {
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                if(map[k][l]==null){
                    System.out.print(" ");
                    continue;
                }
                System.out.print(map[k][l].number+" ");
            }
            System.out.println();
        }
    }

}
