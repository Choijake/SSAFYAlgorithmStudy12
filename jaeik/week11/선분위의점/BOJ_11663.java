package week11.선분위의점;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11663 {
    static int N, M;
    static int[] dots;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dots = new int[N+2];
        dots[N+1] = 1000000001;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            dots[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(dots);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int num1 = binarySearch(start, 0);
            int num2 = binarySearch(end, 1);

            System.out.println(num2-num1+1);
        }
    }

    static int binarySearch(int target, int mod){
        //target보다 크거나 같은 첫 번째 인덱스
        if(mod==0){
            int lo = 0;
            int hi = N+1;
            while(lo+1<hi){
                int mid = (lo+hi)/2;

                if(target==dots[mid])return mid;

                if(target<dots[mid]){
                    hi = mid;
                }else{
                    lo = mid;
                }
            }
            return hi;
        }
        //target보다 작거나 같은 첫 번째 인덱스
        else{
            int lo = 0;
            int hi = N+1;
            while(lo+1<hi){
                int mid = (lo+hi)/2;

                if(target==dots[mid])return mid;

                if(target<dots[mid]){
                    hi = mid;
                }else{
                    lo = mid;
                }
            }
            return lo;
        }
    }
}
