package week12.나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805 {
    static long max;
    static int[] trees;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        System.out.println(binarySearch());
    }

    static long binarySearch(){
        long lo = 0;
        long hi = max*2;
        while(lo+1<hi){
            long mid = (lo+hi)/2;

            long sum = getSum(mid);

            if(sum>=M){
               lo = mid;
            }else{
                hi = mid;
            }
        }
        return lo;
    }

    static long getSum(long mid){
        long sum = 0;
        for(int i=0; i<N; i++){
            if(trees[i]<=mid)continue;
            sum += (trees[i]-mid);
        }
        return sum;
    }
}
/**
 * 결정 문제 : mid 높이에 절단기가 위치했을 때 m이상의 나무를 얻을 수 있는가?
 * check(mid)의 분포 : T~F
 * 정답인 mid값 : T의 분포 중 최댓값
 * 그러므로 반복문 종료 후 T분포의 최대인 lo를 반환한다
 *
 * + 추가 +
 * 다른 문제에서 if(check(mid)==true)return mid; 방식으로 답을 도출했었는데
 * 이는 Line 38의 조건을 if(sum>=M)으로 정의하는 것과 같다
 * 왜냐하면 sum과 mid가 같을 경우에도 lo = mid가 되고 곧 바로 lo값이 반환되기 때문이다.
 */