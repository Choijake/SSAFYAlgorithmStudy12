package week16.백설공주와일곱난쟁이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3040 {
    static int[] number;
    static int[] save;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = new int[9];
        save = new int[7];
        for(int i=0; i<9; i++){
            number[i] = Integer.parseInt(br.readLine());
        }

        subset(0, 0);
    }

    static void subset(int depth, int start){
        if(depth==7){
            if(check()){
                for(int i=0; i<7; i++){
                    System.out.println(save[i]);
                }
                return;
            }
            return;
        }
        for(int i=start; i<9; i++){
            save[depth] = number[i];
            subset(depth+1, i+1);
        }
    }

    static boolean check(){
        int sum = 0;
        for(int i=0; i<7; i++){
            sum += save[i];
        }
        return sum==100;
    }
}
