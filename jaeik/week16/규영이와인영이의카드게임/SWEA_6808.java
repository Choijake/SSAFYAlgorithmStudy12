package week16.규영이와인영이의카드게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_6808 {
    static int kWin;
    static int iWin;
    static boolean[] visited;
    static int[] save;
    static List<Integer> kyu, in;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for(int tc=0; tc<TC; tc++){
            kyu = new ArrayList<>();
            boolean[] select = new boolean[19];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<9; i++){
                kyu.add(Integer.parseInt(st.nextToken()));
                select[kyu.get(i)] = true;
            }

            in = new ArrayList<>();
            for(int i=1; i<=18; i++){
                if(!select[i])in.add(i);
            }

            iWin = 0;
            kWin = 0;
            visited = new boolean[9];
            save = new int[9];
            permutation(0);

            System.out.println("#"+(tc+1)+" "+kWin+" "+iWin);
        }
    }

    static void permutation(int depth){
        if(depth == 9){
            //규영이가 이기는 경우
            if(game()){
                kWin++;
            }
            //인영이가 이기는 경우
            else iWin++;
            
            return;
        }

        for(int i=0; i<9; i++){
            if(!visited[i]){
                save[depth] = in.get(i);
                visited[i] = true;
                permutation(depth+1);
                visited[i] = false;
            }
        }
    }

    static boolean game(){
        int K = 0;
        int I = 0;
        for(int i=0; i<9; i++){
            if(kyu.get(i)>save[i])K+=kyu.get(i)+save[i];
            else I+=kyu.get(i)+save[i];
        }

        return K>I;
    }

}
/**
 * 1. 인영이의 카드를 정하는 법에서 조금 막힘 ㅠ
 */