package week17.NQUEEN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {
    static int[] set;
    static int count = 0;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        set = new int[N];
        dfs(0);
        System.out.println(count);
    }

    /**
     * 일차원 배열 set은 보드의 (row, col)에 말을 놓은 상태를 저장한다
     * set의 인덱스가 row, 저장된 값이 col이다
     * 파라미터 row는 dfs 탐색의 깊이와 배열의 row 역할을 한다(한 행에 말을 놓았으면 그 행에는 말을 놓지 못하기 때문에 다음 행으로 바로 넘어가 중복 검사를 할 필요가 없다)
     * 반복문에서는 N 크기만큼 col을 순회하면서 말을 놓는 행위에 대해 가능 여부를 판단한다
     * dfs메서드의 깊이 즉 row가 끝까지 도달하면 말을 N개만큼 놓는 것이 성공한 것이므로 count를 올려준다
     */
    static void dfs(int row){
        if(row==N){
            count++;
            return;
        }

        for(int col=0; col<N; col++){
            //현재 탐색 깊이인 row에 col을 저장한 후 possible로 검사하고 가능하면 다음 깊이 즉 다음 row로 넘어간다
            //불가능하면 다음 col 자리를 순회하여 같은 동작을 반복한다
            set[row] = col;

            if(possible(row)){
                dfs(row+1);
            }
        }
    }

    /**
     * 1. 열 검사
     * set[row]에는 col값이 저장된다
     * 같은 col에는 말을 놓을 수 없기 때문에 행을 순회하며 현재 놓으려는 col에 말이 놓여져 있는지 확인한다
     *
     * 2. 대각선 검사
     * (row1, col1), (row2, col2)가 있을 때 (row1-row2)의 절댓값이 (col1-col2)의 절댓값과 같으면 대각선을 이룬다
     * 이를 똑같이 적용한다
     */
    static boolean possible(int row){
        for(int i=0; i<row; i++){
            if(set[i]==set[row])return false;

            else if(Math.abs(row-i)==Math.abs(set[row]-set[i]))return false;
        }
        return true;
    }
}
