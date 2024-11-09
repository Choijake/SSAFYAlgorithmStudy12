package week12.구간나누기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13397 {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        System.out.println(binarySearch());
    }

    static int binarySearch(){
        int lo = -1;
        int hi = max+1;

        while(lo+1<hi){
            int mid = (lo+hi)/2;

            if(solve(mid)<=M){
                hi = mid;
            }
            else {
                lo = mid;
            }
        }
        return hi;
    }

    static int solve(int mid){
        int count = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if(max-min > mid){
                count++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--;
            }
        }
        return count;
    }
}
