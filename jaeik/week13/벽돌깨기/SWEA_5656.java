package week13.벽돌깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int T, N, W, H;
    static int[][] map;
    static int[][] copy;
    static int[] destroy;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());


            map = new int[H][W];
            copy = new int[H][W];
            for(int i=0; i<H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = map[i][j];
                }
            }

            result = Integer.MAX_VALUE;
            destroy = new int[N];
            permutation(0);

            System.out.println("#"+(tc+1)+" "+result);
        }
    }

    //구슬로 깰 벽돌의 열 구하기
    static void permutation(int depth){
        if(depth==N){
            breakBrick();
            result = Math.min(result, countLeftBrick());
            copyMap();
            return;
        }

        for(int i=0; i<W; i++){
            destroy[depth] = i;
            permutation(depth+1);
        }
    }

    //구슬로 벽돌 깨기
    static void breakBrick(){
        int row = 0;
        int col = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<H; j++){
                if(map[j][destroy[i]]!=0){
                    row = j;
                    col = destroy[i];
                    break;
                }
            }
            //깨기
            bfs(row, col);
            //내리기
            brickDown();
        }
    }

    static void bfs(int row, int col){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col, map[row][col]});
        map[row][col] = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int num = cur[2]-1;


            for(int i=0; i<4; i++){
                int nextRow = cur[0];
                int nextCol = cur[1];

                for(int j=0; j<num; j++){
                    nextRow += dr[i];
                    nextCol += dc[i];

                    if(nextRow<0 || nextCol<0 || nextRow>=H || nextCol>=W)break;

                    //다음 칸이 1이면 그냥 파괴
                    if(map[nextRow][nextCol]==1)map[nextRow][nextCol]=0;

                        //다음 칸이 1보다 크면 큐에 담음
                    else if(map[nextRow][nextCol]>1){
                        queue.add(new int[] {nextRow, nextCol, map[nextRow][nextCol]});
                        /**
                         * 바로 0으로 초기화해주지 않으면 중복된 벽돌이 큐에 들어가기 때문에 시간 초과가 발생한다
                         */
                        map[nextRow][nextCol] = 0;
                    }
                }
            }
        }
    }

    static void brickDown(){
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<W; i++){
            for(int j=0; j<H; j++){
                if(map[j][i]!=0)stack.add(map[j][i]);
            }
            for(int j=H-1; j>=0; j--){
                if(stack.isEmpty())map[j][i]=0;
                else map[j][i] = stack.pop();
            }
        }
    }

    static int countLeftBrick(){
        int count = 0;
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(map[i][j]>=1)count++;
            }
        }
        return count;
    }

    static void copyMap(){
        for(int i=0; i< copy.length; i++){
            for(int j=0; j<copy[0].length; j++){
                map[i][j] = copy[i][j];
            }
        }
    }
}
