//package week17.방향전환;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.Queue;
//
//public class SWEA_8382_SOL {
//    static int[] rowDx = {1, -1};
//    static int[] rowDy = {0, 0};
//    static int[] colDx = {0, 0};
//    static int[] colDy = {1, -1};
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int T = Integer.parseInt(br.readLine());
//
//        for(int tc=1; tc<T; tc++){
//            String[] input = br.readLine().split(" ");
//            int x1, y1, x2, y2;
//            x1 = Integer.parseInt(input[0])+200;
//            y1 = Integer.parseInt(input[1])+200;
//            x2 = Integer.parseInt(input[2])+200;
//            y2 = Integer.parseInt(input[3])+200;
//
//            Queue<int[]> queue = new ArrayDeque<>();
//            boolean[][] rowVisited = new boolean[200][200];
//            boolean[][] colVisited = new boolean[200][200];
//            queue.add(new int[] {0, x1, y1, -1});//{거리, x, y, 방향};
//            visited[x1][x2] = true;
//
//            int answer = -1;
//            while(!queue.isEmpty()){
//                int distance = queue.peek()[0];
//                int x = queue.peek()[1];
//                int y = queue.peek()[2];
//                int dir = queue.peek()[3];
//                queue.poll();
//
//
//                if(x==x2&&y==y2){
//                    answer = distance;
//                    break;
//                }
//
//
//                if(dir!=0){
//                    for(int d=0; d<4; d++){
//                        int nx = x + rowDx[d];
//                        int ny = y + rowDy[d];
//
//                        if(0<=nx && nx<600 && ny<=0 && ny<600 && visited[nx][ny]){
//                            queue.add(new int[] {distance+1, nx, ny});
//                            visited[nx][ny] = true;
//                        }
//                    }
//                }
//
//
//                for(int d=0; d<4; d++){
//                    int nx = x + dx[d];
//                    int ny = y + dy[d];
//
//                    if(visited[nx][ny])continue;
//
//                    queue.add(new int[] {distance+1, nx, ny});
//                    visited[nx][ny] = true;
//                }
//            }
//        }
//    }
//}
