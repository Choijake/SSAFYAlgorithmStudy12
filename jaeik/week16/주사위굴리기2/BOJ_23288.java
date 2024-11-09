package week16.주사위굴리기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23288 {
    static class Dice{
        int[] status;
        int row;
        int col;
        int dir;
        int k;

        public Dice(int[] status, int row, int col, int dir, int k) {
            this.status = status;
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.k = k;
        }
    }

    static int result;
    static Dice dice;
    static int[][] map;
    static int N, M, K;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new Dice(new int[] {0, 1, 2, 3, 4, 5, 6}, 1, 1, 0, K);

        roll();

        System.out.println(result);
    }

    static void roll(){
        while(dice.k-->0){
            //이동
            move();

            //칸 점수 계산
            result += getScore();

            //주사위 상태 업데이트
            updateStatus();

            //주사위 방향 업데이트
            updateDiceDirection();
        }
    }

    static void move(){
        int row = dice.row;
        int col = dice.col;

        int nextRow = row + dr[dice.dir];
        int nextCol = col + dc[dice.dir];

        if(nextRow<1 || nextCol<1 || nextRow>N || nextCol>M){
            dice.dir = (dice.dir+2)%4;

            dice.row = row + dr[dice.dir];
            dice.col = col + dc[dice.dir];
            return;
        }

        dice.row = nextRow;
        dice.col = nextCol;
    }

    static int getScore(){
        int number = map[dice.row][dice.col];
        int count = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][M+1];
        queue.add(new int[] {dice.row, dice.col});
        visited[dice.row][dice.col] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            for(int i=0; i<4; i++){
                int nextRow = now[0] + dr[i];
                int nextCol = now[1] + dc[i];

                if(nextRow<1 || nextCol<1 || nextRow>N || nextCol>M)continue;
                if(visited[nextRow][nextCol])continue;

                if(map[nextRow][nextCol]==number){
                    queue.add(new int[] {nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                    count++;
                }
            }
        }

        return number*count;
    }

    static void updateStatus(){
        int[] status = dice.status;
        int tmp = 0;
        switch (dice.dir){
            //동
            case 0:
                tmp = status[4];
                status[4] = status[6];
                status[6] = status[3];
                status[3] = status[1];
                status[1] = tmp;
                break;
            //남
            case 1:
                tmp = status[1];
                status[1] = status[2];
                status[2] = status[6];
                status[6] = status[5];
                status[5] = tmp;
                break;
            //서
            case 2:
                tmp = status[4];
                status[4] = status[1];
                status[1] = status[3];
                status[3] = status[6];
                status[6] = tmp;
                break;
            //북
            case 3:
                tmp = status[1];
                status[1] = status[5];
                status[5] = status[6];
                status[6] = status[2];
                status[2] = tmp;

        }
    }

    static void updateDiceDirection(){
        int floor = dice.status[6];
        int number = map[dice.row][dice.col];

        if(floor>number){
            dice.dir = (dice.dir+1)%4;
        }
        else if(floor<number){
            if(dice.dir==0){
                dice.dir = 3;
            }
            else {
                dice.dir = (dice.dir-1)%4;
            }
        }
    }
}
/**
 * 1. roll()에서 어떤 동작을 어떤 순서로 배치할 지 와닿지 않았다 -> 설계가 미숙 했다
 * 2. 어떤 조건에 의해 주사위 의 방향이 변경되기 때문에 델타 설정할 때 고민 해봐야 했다
 * 3. 주사위 방향 변경할 때 MOD를 사용했 는데, 이 또한 델타 설정 에서 고민 해야할 필요가 있다
 * 4. move()와 getScore()에서 지도 밖으로 나가는 조건문 을 자주 수정 했다 -> 정신 좀 차리자
 * 5. updateDiceDirection()에서 조건을 잘못 설정 했다 -> 문제 잘 읽자
 * 6. updateStatus() 로직이 틀렸다 -> 구현할 때 확실히 하자
 */
