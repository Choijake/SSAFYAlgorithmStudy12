package week14.오리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12933 {
    static int result;
    static char[] quack = {'q', 'u', 'a', 'c', 'k'};
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       char[] line = br.readLine().toCharArray();

       if(line.length%5!=0){
           System.out.println(-1);
           return;
       }

       result = 0;
       int total = line.length;
       while (true){
           int quack_idx = 0;
           int temp_idx = 0;
           boolean flag = false;
           int[] temp = new int[5];
           for(int i=0; i<line.length; i++){
                if(line[i]==quack[quack_idx]){
                    temp[temp_idx++] = i;
                    quack_idx++;
                }

                if(quack_idx==5){
                    flag = true;
                    quack_idx = 0;
                    temp_idx = 0;
                    total -= 5;
                    for(int j=0; j<temp.length; j++){
                        line[temp[j]] = '_';
                    }
                }
           }

           if (flag)
               result++;
           else {
               System.out.println(-1);
               return;
           }

           if(total==0)break;
       }

        System.out.println(result);
    }
}
