package week14.구슬탈출;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460 {
    static class Marble{
        int red_row;
        int red_col;
        int blue_row;
        int blue_col;
        int count;

        public Marble(){}
    }

    static int result;
    static int N, M;
    static char[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Marble marble = new Marble();
        map = new char[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j);

                if(map[i][j]=='B'){
                    marble.blue_row = i;
                    marble.blue_col = j;
                    map[i][j] = '.';
                }
                else if(map[i][j]=='R'){
                    marble.red_row = i;
                    marble.red_col = j;
                    map[i][j] = '.';
                }
            }
        }

        marble.count = 0;
        result = 0;
        bfs(marble);

        result = result==-1||result==0?0:1;
        System.out.println(result);
    }

    static void bfs(Marble marble){
        Queue<Marble> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        queue.add(marble);
        visited[marble.red_row][marble.red_col][marble.blue_row][marble.blue_col] = true;

        while(!queue.isEmpty()){
            Marble cur = queue.poll();
            int redRow = cur.red_row;
            int redCol = cur.red_col;
            int blueRow = cur.blue_row;
            int blueCol = cur.blue_col;
            int count = cur.count;

            if(count>=10){
                result = -1;
                return;
            }

            if(map[redRow][redCol]=='O'){
                result = cur.count;
                return;
            }

            for(int i=0; i<4; i++){
                boolean redInHole = false;
                boolean blueInHole = false;

                int nextRedRow = redRow;
                int nextRedCol = redCol;
                while(true){
                    nextRedRow += dr[i];
                    nextRedCol += dc[i];

                    if(map[nextRedRow][nextRedCol]=='#'){
                        nextRedRow -= dr[i];
                        nextRedCol -= dc[i];
                        break;
                    }

                    if(map[nextRedRow][nextRedCol]=='O'){
                        redInHole = true;
                        break;
                    }
                }

                int nextBlueRow = blueRow;
                int nextBlueCol = blueCol;
                while(true){
                    nextBlueRow += dr[i];
                    nextBlueCol += dc[i];

                    if(map[nextBlueRow][nextBlueCol]=='#'){
                        nextBlueRow -= dr[i];
                        nextBlueCol -= dc[i];
                        break;
                    }

                    if(map[nextBlueRow][nextBlueCol]=='O'){
                        blueInHole = true;
                        break;
                    }
                }

                if(blueInHole)continue;
                else if(redInHole){
                    result = count+1;
                    return;
                }

                Marble newMarble = new Marble();
                newMarble.red_row = nextRedRow;
                newMarble.red_col = nextRedCol;
                newMarble.blue_row = nextBlueRow;
                newMarble.blue_col = nextBlueCol;

                if (nextRedRow == nextBlueRow && nextRedCol == nextBlueCol) {
                    if (i == 0) {
                        if (cur.red_col > cur.blue_col) {
                            newMarble.blue_col -= 1;
                        } else {
                            newMarble.red_col -= 1;
                        }
                    } else if (i == 1) {
                        if (cur.red_col < cur.blue_col) {
                            newMarble.blue_col += 1;
                        } else {
                            newMarble.red_col += 1;
                        }
                    } else if (i == 2) {
                        if (cur.red_row > cur.blue_row) {
                            newMarble.blue_row -= 1;
                        } else {
                            newMarble.red_row -= 1;
                        }
                    } else {
                        if (cur.red_row < cur.blue_row) {
                            newMarble.blue_row += 1;
                        } else {
                            newMarble.red_row += 1;
                        }
                    }
                }

                newMarble.count = count+1;
                if(!visited[newMarble.red_row][newMarble.red_col][newMarble.blue_row][newMarble.blue_col]){
                    queue.add(newMarble);
                }
            }
        }
    }
}
