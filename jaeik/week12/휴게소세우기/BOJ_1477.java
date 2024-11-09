package week12.휴게소세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1477 {
    static int N, M, L;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N+2];

        arr[0] = 0;
        arr[N+1] = L;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        System.out.println(binarySearch());
    }

    static int binarySearch(){
        int lo = 0;
        int hi = arr[N+1];
        while(lo+1<hi){
            int mid = (lo+hi)/2;

            if(getCount(mid)<=M){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        return hi;
    }

    static int getCount(int mid){
        int count = 0;
        for(int i=0; i<=N; i++){
            int dist = arr[i+1]-arr[i]-1;
            count +=  dist/mid;
        }
        return count;
    }
}
/**
 * 결정 문제 :mid 만큼의 거리마다 배치해 휴게소를 설치할 수 있는 경우 M개를 설치할 수 있는가?
 * check(mid) 의 분포를 휴게소를 M개 이하만큼 설치할 수 있을 경우 True 아닐 경우 False로 지정
 * T에 해당하는 정답 범위에서 최소값을 구해야한다
 * 그러므로 반복문 종료 T범위의 최소이 hi를 반환하도록 한다
 */