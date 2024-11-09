package week17.물놀이를가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10966 {
    static Queue<int[]> queue;
    static boolean[][] visited;
    static int N, M;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int T=0; T<tc; T++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for(int i=0; i<N; i++){
                String line = br.readLine();
                for(int j=0; j<M; j++){
                    char sign = line.charAt(j);
                    map[i][j] = (sign=='W')?1:0;
                }
            }

            result = 0;
            queue = new ArrayDeque<>();
            visited = new boolean[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j]==1){
                        queue.add(new int[]{i, j, 0});
                        visited[i][j] = true;
                    }
                }
            }

            bfs();

            System.out.println("#"+(T+1)+" "+result);
        }
    }

    static void bfs(){
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int count = now[2];

            for(int i=0; i<4; i++){
                int nextRow = now[0] + dr[i];
                int nextCol = now[1] + dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=M)continue;
                if(visited[nextRow][nextCol])continue;

                queue.add(new int[] {nextRow, nextCol, count+1});
                visited[nextRow][nextCol] = true;
                result += count+1;
            }
        }
    }
}
/***
 * L이 아니라 W를 기준으로 방문을 시작해 탐색 횟수를 줄인다. 또한 방문 배열과 큐를 재선언 하며 탐색을 할 필요가 없기 때문에 더 효율적이다
 * bfs탐색 시작 전에 W인 인덱스를 큐에 넣고 방문 처리한다
 * bfs()에서 큐에있는 W인 곳 부터 탐색을 시작해 방문하지 않은 L을 큐에 넣는다
 * 큐에 저장해놨던 모든 W를 시작으로 탐색이 시작되기 때문에 방문하지 않은 곳의 경로 길이가 곧 최소 경로 길이가 된다
 */