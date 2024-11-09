package week12.랜선자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654 {
    static long max = Integer.MIN_VALUE;
    static int K, N;
    static long[] cables;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cables = new long[K];
        for(int i=0; i<K; i++){
            cables[i] = Long.parseLong(br.readLine());
            max = Math.max(max, cables[i]);
        }

        System.out.println(binarySearch());
    }

    static long binarySearch(){
        long lo = 0;
        long hi = max+1;
        while(lo+1<hi){
            long mid = (lo+hi)/2;

            if(check(mid)>=N){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return lo;
    }

    static long check(long mid){
        long count = 0;
        for(int i=0; i<K; i++){
            if(mid>cables[i])continue;
            count += cables[i]/mid;
        }
        return count;
    }
}
/**
 * 1. 46 Line의 조건을 주의 해야한다. mid>=cables[i]로 하면 정답이 max값일 때는 건너 뛰게 되어 max-1을 정답으로 출력하기 때문이다.
 * 2. lo, hi, mid의 타입을 long으로 선언하여야 한다. 31 Line mid를 초기화하는 과정에서 lo+hi가 Integer의 MAX보다 커질 수 있기 때문이다.
 */