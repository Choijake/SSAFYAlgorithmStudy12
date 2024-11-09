//package week5.무선충전;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//class BC{
//    int row;
//    int col;
//    int range;
//    int power;
//
//    BC(int row, int col, int range, int power){
//        this.row = row;
//        this.col = col;
//        this.range = range;
//        this.power = power;
//    }
//}
//
//class Player{
//    int row;
//    int col;
//
//    Player(int row, int col){
//        this.row = row;
//        this.col = col;
//    }
//}
//
//public class SWEA_5644 {
//    static int[] dr = {0, -1, 0, 1, 0};
//    static int[] dc = {0, 0, 1, 0, -1};
//    static int[][] map;
//    static List<BC> bcs = new ArrayList<>();
//    static int[] a_move;
//    static int[] b_move;
//    static Player A;
//    static Player B;
//    static int move, n;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int T = Integer.parseInt(br.readLine());
//        for(int tc=0; tc<T; tc++){
//            st = new StringTokenizer(br.readLine());
//
//            move = Integer.parseInt(st.nextToken());
//            n = Integer.parseInt(st.nextToken());
//
//            A = new Player(1, 1);
//            B = new Player(10, 10);
//
//            a_move = new int[move+1];
//            b_move = new int[move+1];
//
//            st = new StringTokenizer(br.readLine());
//            for(int i=1; i<=move; i++){
//                a_move[i] = Integer.parseInt(st.nextToken());
//            }
//
//            st = new StringTokenizer(br.readLine());
//            for(int i=1; i<=move; i++){
//                b_move[i] = Integer.parseInt(st.nextToken());
//            }
//
//            bcs = new ArrayList<>();
//            map = new int[11][11];
//            for(int i=0; i<n; i++){
//                st = new StringTokenizer(br.readLine());
//
//                int row = Integer.parseInt(st.nextToken());
//                int col = Integer.parseInt(st.nextToken());
//                int range = Integer.parseInt(st.nextToken());
//                int power = Integer.parseInt(st.nextToken());
//
//                bcs.add(new BC(row, col, range, power));
//            }
//        }
//    }
//
//    static int check(int )
//
//    static int getMaxCharge(){
//
//    }
//
//    static int move(){
//        int totalCharge = 0;
//
//        for(int i=0; i<=move; i++){
//            A.row += dr[a_move[i]];
//            A.col += dc[a_move[i]];
//
//            B.row += dr[b_move[i]];
//            B.col += dc[b_move[i]];
//
//            totalCharge += getMaxCharge();
//        }
//
//        return totalCharge;
//    }
//}
