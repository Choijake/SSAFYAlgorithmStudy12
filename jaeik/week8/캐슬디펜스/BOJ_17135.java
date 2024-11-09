//package week8.캐슬디펜스;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class BOJ_17135 {
//    static int N, M, D;
//    static int[][] map;
//    static List<int[]> enemy;
//    static int[] archer;
//    static int result;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        D = Integer.parseInt(st.nextToken());
//
//        map = new int[N][M];
//        archer = new int[3];
//        enemy = new ArrayList<>();
//        for(int i=0; i<N; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j=0; j<M; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j]==1)enemy.add(new int[] {i, j});
//            }
//        }
//    }
//
//    static void
//}
