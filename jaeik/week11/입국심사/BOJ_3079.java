package week11.입국심사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079 {
    static int N, M;
    static long[] time;
    static final long MAX = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new long[N];
        for(int i=0; i<N; i++){
            time[i] = Long.parseLong(br.readLine());
        }

        System.out.println(binarySearch());
    }

    static long binarySearch(){
        long lo = 0;
        long hi = MAX*M+1;
        while(lo+1<hi){
            long mid = (lo+hi)/2;

            long count=0;
            for(Long t : time){
                count += (mid/t);
                if(count>=M)break ;
            }

            if(M<=count){
                hi = mid;
            }else lo = mid;
        }

        return hi;
    }
}
