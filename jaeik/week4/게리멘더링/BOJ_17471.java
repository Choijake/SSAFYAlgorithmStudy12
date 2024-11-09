//package week4.게리멘더링;
//import java.io.*;
//
//import java.util.*;
//
//
//class Cell{
//    int row;
//    int col;
//    int power;
//    boolean isAlive;
//    boolean isActivate;
//
//    public Cell(int row, int col, int power, boolean isAlive, boolean isActivate){
//        this.row = row;
//        this.col = col;
//        this.power = power;
//        this.isAlive = isAlive;
//        this.isActivate = isActivate;
//    }
//}
//
//public class BOJ_17471 {
//    static int[] dr = {0, 0, 1, -1};
//    static int[] dc = {1, -1, 0, 0};
//    static Cell[][] map;
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int T = Integer.parseInt(br.readLine());
//
//        for(int tc=0; tc<T; tc++){
//            st = new StringTokenizer(br.readLine());
//
//            int n = Integer.parseInt(st.nextToken());
//            int m = Integer.parseInt(st.nextToken());
//            int k = Integer.parseInt(st.nextToken());
//
//            map = new Cell[n+k+1][n+k+1];
//            for(int i=0; i<n; i++){
//                st = new StringTokenizer(br.readLine());
//
//                for(int j=0;j<m; j++){
//                    int power = Integer.parseInt(st.nextToken());
//
//                    map[map.length/2+i][map.length/2+j] = new Cell(map.length/2+i, map.length/2+j, power, false, false);
//                }
//            }
//
//        }
//    }
//
//    static void execute(int time){
//        while(time-- > 0){
//            for(int i=0; i<map.length; i++){
//                for(int j=0; j<map[0])
//            }
//        }
//    }
//}