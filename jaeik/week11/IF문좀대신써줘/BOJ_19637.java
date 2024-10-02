package week11.IF문좀대신써줘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19637 {
    static int N, M;
    static HashMap<Integer, String> alias;
    static int[] power;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        alias = new HashMap<>();

        power = new int[N+1];
        power[0] = 0;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken());
            if(!alias.containsKey(b)){
                alias.put(b, a);
            }
            power[i] = b;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            int p = Integer.parseInt(br.readLine());
            sb.append(binarySearch(p)).append("\n");
        }

        System.out.println(sb);
    }

    static String binarySearch(int p){
        int lo = 0;
        int hi = N;
        while(lo+1<hi){
            int mid = (lo+hi)/2;

            if(p<=power[mid]){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        return alias.get(power[hi]);
    }
}
