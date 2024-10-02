package week10.주사위굴리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
    static StringBuilder sb;
    static int[][] map;
    static int[] dice;
    static int N, M, R, C, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sb = new StringBuilder();
        dice = new int[7];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int cmd = Integer.parseInt(st.nextToken());
            roll(cmd);
        }

        System.out.println(sb);
    }

    static void roll(int cmd){
        switch (cmd){
            //동
            case 1:
                //주사위 위치 업데이트
                if(C+1>=M)break;
                C++;

                //주사위 배열 업데이트
                int temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;

                //주사위 밑면 숫자 업데이트
                if(map[R][C]==0){
                    map[R][C] = dice[6];
                }
                else{
                    dice[6] = map[R][C];
                    map[R][C] = 0;
                }

                //주사위 윗면 출력
                sb.append(dice[1]).append("\n");
                break;
            //서
            case 2:
                if(C-1<0)break;
                C--;

                temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;

                if(map[R][C]==0){
                    map[R][C] = dice[6];
                }
                else{
                    dice[6] = map[R][C];
                    map[R][C] = 0;
                }

                sb.append(dice[1]).append("\n");
                break;
            //북
            case 3:
                if(R-1<0)break;
                R--;

                temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;

                if(map[R][C]==0){
                    map[R][C] = dice[6];
                }
                else{
                    dice[6] = map[R][C];
                    map[R][C] = 0;
                }

                sb.append(dice[1]).append("\n");
                break;
            //남
            case 4:
                if(R+1>=N)break;
                R++;

                temp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;

                if(map[R][C]==0){
                    map[R][C] = dice[6];
                }
                else{
                    dice[6] = map[R][C];
                    map[R][C] = 0;
                }

                sb.append(dice[1]).append("\n");
                break;
        }
    }
}
