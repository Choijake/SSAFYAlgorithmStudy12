package week13.경사로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(execute());
    }

    static int execute(){
        int count = 0;
        for(int i=0; i<N; i++){
            //i번째 행 탐색
            if(checkRow(i))count++;
            //i번째 열 탐색
            if(checkCol(i))count++;
        }
        return count;
    }

    static boolean checkRow(int row){
        boolean[] isInstalled = new boolean[N];

        for(int i=0; i<N-1; i++){
            int diff = map[row][i]-map[row][i+1];
            //다음 지면과의 높이 차이가 1 이상인 경우
            if(Math.abs(diff)>1)return false;

            //다음 칸이 1 높은 경우
            else if(diff==-1){
                for(int j=0; j<L; j++){
                    //이전의 L칸에 이미 경사로가 설치되어있는 경우
                    if(i-j<0 || isInstalled[i-j])return false;
                    //지면의 높이가 다른 경우
                    if(map[row][i]!=map[row][i-j])return false;
                    //설치
                    isInstalled[i-j] = true;
                }
            }

            else if(diff==1){
                for(int j=1; j<=L; j++){
                    //이전의 L칸에 이미 경사로가 설치되어있는 경우
                    if(i+j>=N || isInstalled[i+j])return false;
                    //지면의 높이가 다른 경우
                    if(map[row][i]-1!=map[row][i+j])return false;
                    //설치
                    isInstalled[i+j] = true;
                }
            }
        }
        return true;
    }

    static boolean checkCol(int col){
        boolean[] isInstalled = new boolean[N];

        for(int i=0; i<N-1; i++){
            int diff = map[i][col]-map[i+1][col];
            //다음 지면과의 높이 차이가 1 이상인 경우
            if(Math.abs(diff)>1)return false;

                //다음 칸이 1 높은 경우
            else if(diff==-1){
                for(int j=0; j<L; j++){
                    //이전의 L칸에 이미 경사로가 설치되어있는 경우
                    if(i-j<0 || isInstalled[i-j])return false;
                    //지면의 높이가 다른 경우
                    if(map[i][col]!=map[i-j][col])return false;
                    //설치
                    isInstalled[i-j] = true;
                }
            }

            else if(diff==1){
                for(int j=1; j<=L; j++){
                    //이전의 L칸에 이미 경사로가 설치되어있는 경우
                    if(i+j>=N || isInstalled[i+j])return false;
                    //지면의 높이가 다른 경우
                    if(map[i][col]-1!=map[i+j][col])return false;
                    //설치
                    isInstalled[i+j] = true;
                }
            }
        }
        return true;
    }
}
