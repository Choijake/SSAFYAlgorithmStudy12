package week9.친구네트워크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4195 {
    static int F;
    static HashMap<String, Integer> map;
    static int[] size;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            F = Integer.parseInt(br.readLine());

            map = new HashMap<>();
            parent = new int[F*2+1];
            size = new int[F*2+1];
            for(int i=1; i<=F*2; i++){
                parent[i] = i;
                size[i] = 1;
            }

            int idx=1;
            for(int i=0; i<F; i++){
                st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                if(!map.containsKey(a))map.put(a, idx++);
                if(!map.containsKey(b))map.put(b, idx++);

                sb.append(union(map.get(a), map.get(b))).append("\n");
            }
        }

        System.out.print(sb);
    }

    static int find(int x){
        if(x==parent[x])return x;
        return parent[x] = find(parent[x]);
    }

    static int union(int x, int y){
        x = find(x);
        y = find(y);

        if(x!=y){
            parent[y] = x;
            size[x] += size[y];
            size[y] = 1;
        }

        return size[x];
    }
}
