package week17.프로세서연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767 {
    static int result;
    static boolean isAllConnected;
    static int connectLength;
    static int N;
    static int[][] map;
    static List<int[]> save;
    static List<int[]> cores;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            cores = new ArrayList<>();
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(i!=0&&j!=0&&i!=N-1&&j!=N-1 && map[i][j]==1){
                        cores.add(new int[] {i, j});
                    }
                }
            }

            for(int i= cores.size(); i>=0; i--){
                isAllConnected = false;
                save = new ArrayList<>();
                result = Integer.MAX_VALUE;
                combination(0, 0, i);
                if(isAllConnected)break;
            }

            System.out.println("#"+(tc+1)+" "+result);
        }
    }

    static void combination(int depth, int start, int size){
        if(depth == size){
            visited = new boolean[N][N];
            connectLength = Integer.MAX_VALUE;
//            System.out.println("=======================");
//            System.out.println("조합 사이즈가 "+size+"일 때");
            dfs(0, 0, 0);
            result = Math.min(result, connectLength);
            return;
        }

        for(int i=start; i< cores.size(); i++){
            save.add(depth, cores.get(i));
            combination(depth+1, i+1, size);
            save.remove(depth);
        }
    }

    static void dfs(int depth, int start, int totalLength){
        if(depth == save.size()){
            //System.out.println("totalLength : "+totalLength);
            connectLength = Math.min(connectLength, totalLength);
            isAllConnected = true;
            return;
        }

        for(int i=start; i<save.size(); i++){
            int[] cur = save.get(i);
            int row = cur[0];
            int col = cur[1];
            int notConnectCount = 0;

            for(int j=0; j<4; j++){
                int nextRow = row;
                int nextCol = col;
                int length = 0;

                while(true){
                    nextRow += dr[j];
                    nextCol += dc[j];

                    //연결 불가능
                    if(visited[nextRow][nextCol] || map[nextRow][nextCol]==1) {
                        notConnectCount++;
                        //연결 처리한 부분 복원
                        while (nextRow != row || nextCol != col) {
                            nextRow -= dr[j];
                            nextCol -= dc[j];
                            visited[nextRow][nextCol] = false;
                        }
                        break;
                    }

                    //연결이 유망할 경우
                    visited[nextRow][nextCol] = true;
                    length++;

                    //연결 됐을 때
                    if((j==0&&nextCol==N-1) || (j==1&&nextRow==N-1) || (j==2&&nextCol==0) || (j==3&&nextRow==0)){
                        dfs(depth+1, i+1, totalLength+length);
                        //연결 처리한 부분 복원
                        while (nextRow != row || nextCol != col) {
                            visited[nextRow][nextCol] = false;
                            nextRow -= dr[j];
                            nextCol -= dc[j];
                        }
                        break;
                    }
                }
            }

            //4방으로 모두 연결 불가능하면 리턴
            if(notConnectCount==4)return;
        }
    }
}
/**
 * 117line
 */