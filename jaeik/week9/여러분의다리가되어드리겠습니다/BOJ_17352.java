package week9.여러분의다리가되어드리겠습니다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17352 {
    static int N;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        for(int i=0; i<N-2; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        for(int i=1; i<N; i++){
            for(int j=i+1; j<=N; j++){
                if(union(i, j)){
                    System.out.println(i+" "+j);
                    return;
                }
            }
        }
    }

    static int find(int x){
        if(x==parent[x])return x;
        return parent[x]=find(parent[x]);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y)return false;

        parent[y] = x;

        return true;
    }
}
