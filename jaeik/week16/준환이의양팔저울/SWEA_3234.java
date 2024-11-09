package week16.준환이의양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3234 {
    static int N;
    static int TC;
    static int[] weight;
    static int[] save;
    static int result;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for(int tc=0; tc<TC; tc++){
            N = Integer.parseInt(br.readLine());

            weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                weight[i] = Integer.parseInt(st.nextToken());
            }

            save = new int[N];
            visited = new boolean[N];
            result = 0;

            permutation(0);

            System.out.println("#"+(tc+1)+" "+result);
        }
    }

    static void permutation(int depth){
        if(depth == N){
            subset(0, 0, 0);
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                save[depth] = weight[i];
                visited[i] = true;
                permutation(depth+1);
                visited[i] = false;
            }
        }
    }

    static void subset(int right, int left, int depth){
        if(right>left)return;

        if(depth==N){
            result++;
            return;
        }

        subset(right+save[depth], left, depth+1);
        subset(right, left+save[depth], depth+1);
    }
}
