package week11.예산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {
    static int N, M;
    static int[] deposit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        deposit = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            deposit[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(deposit);

        M = Integer.parseInt(br.readLine());

        int lo = 0;
        int hi = deposit[N-1];
        while(lo+1<hi){
            int mid = (lo+hi)/2;

            int total = check(mid);

            if(total>=M){
                hi = mid;
            }else {
                lo = mid;
            }
        }

        System.out.println(lo);
    }

    static int check(int num){
        int sum = 0;
        for(int i=0; i<N; i++){
            if(num>=deposit[i]){
                sum += deposit[i];
            }else{
                sum += num;
            }
        }
        return sum;
    }
}
