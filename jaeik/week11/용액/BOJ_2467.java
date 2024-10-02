package week11.용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
    static int N;
    static int[] sol;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sol = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sol[i] = Integer.parseInt(st.nextToken());
        }

        int[] idx = binarySearch();

        System.out.println(sol[idx[0]]+" "+sol[idx[1]]);
    }

    static int[] binarySearch(){
        int min = Integer.MAX_VALUE;
        int[] result = new int[2];
        int lo = 0;
        int hi = N-1;
        while(lo<hi){
            int sum = sol[lo]+sol[hi];

            if(Math.abs(min)>=Math.abs(sum)){
                min = sum;
                result = new int[] {lo, hi};
            }

            if(sum==0)return new int[] {lo, hi};
            else if(sum<0)lo++;
            else hi--;
        }
        return result;
    }
}
/**
 * 35 line에서 min값과 sum을 비교할 때 각 값의 절대값으로 비교해야한다
 */