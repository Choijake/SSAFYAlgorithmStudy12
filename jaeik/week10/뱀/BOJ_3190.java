package week10.뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190 {
    static class Snake{
        int row;
        int col;
        int dir;
        Queue<int[]> body;

        public Snake(int row, int col, int dir, Queue<int[]> body) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.body = body;
        }
    }
    
    //시계 방향
    static int[] dr = {0 ,1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static final int APPLE = 1;
    static final int SNAKE = 2;
    static int n, k, l;
    static int[][] map;
    static List<int[]> change;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            map[row][col] = APPLE;
        }

        l = Integer.parseInt(br.readLine());
        change = new ArrayList<>();
        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            if(c.equals("L"))change.add(new int[] {x, -1});
            if(c.equals("D"))change.add(new int[] {x, 1});
        }

        int result = start(1, 1);

        System.out.println(result);
    }

    static int start(int row, int col){
        Snake snake = new Snake(1, 1, 0, new ArrayDeque<>());
        snake.body.add(new int[] {row, col});
        map[row][col] = SNAKE;

        int time = 0;
        while(true){
            time++;

            int nextRow = snake.row+dr[snake.dir];
            int nextCol = snake.col+dc[snake.dir];

            //다음 칸이 벽이거나 뱀의 몸통이면 종료
            if(nextRow<1 || nextRow>n || nextCol<1 || nextCol>n || map[nextRow][nextCol]==SNAKE)break;

            //다음 칸에 사과가 있으면 사과를 먹고 꼬리 그대로
            if(map[nextRow][nextCol]==APPLE){
                map[nextRow][nextCol] = SNAKE;
            }
            //다음 칸에 사과가 없으면 꼬리 칸을 비운다
            else if(map[nextRow][nextCol]==0 && !snake.body.isEmpty()){
                int[] tail = snake.body.poll();
                map[tail[0]][tail[1]] = 0;
                map[nextRow][nextCol] = SNAKE;
            }

            //snake 객체 상태 업데이트
            snake.row += dr[snake.dir];
            snake.col += dc[snake.dir];
            snake.body.add(new int[] {snake.row, snake.col});

            //시간에 따른 뱀의 방향 변화
            if(!change.isEmpty() && time==change.get(0)[0]){
                int nextDir;

                if(snake.dir+change.get(0)[1]==-1)nextDir=3;
                else if(snake.dir+change.get(0)[1]==4)nextDir=0;
                else nextDir = snake.dir+change.get(0)[1];

                snake.dir = nextDir;

                change.remove(0);
            }
        }

        return time;
    }
}
