package week16.상어초등학교;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21608 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int N;
    static HashMap<Integer, List<Integer>> likeFriends;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        likeFriends = new HashMap<>();
        map = new int[N][N];

        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            List<Integer> likes = new ArrayList<>();
            for(int j=0; j<4; j++){
                likes.add(Integer.parseInt(st.nextToken()));
            }
            likeFriends.put(number, new ArrayList<>(likes));
            setPlace(number, new ArrayList<>(likes));
        }

        int satisfaction = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                satisfaction += getSatisfaction(i, j);
            }
        }

        System.out.println(satisfaction);
    }

    static void setPlace(int number, List<Integer> likes){
        int maxLike = -1;
        int maxZero = -1;
        int row = 0;
        int col = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] != 0)continue;

                int like = 0;
                int zero = 0;
                for(int d=0; d<4; d++){
                    int nextRow = i+dr[d];
                    int nextCol = j+dc[d];

                    if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=N)continue;

                    if(map[nextRow][nextCol]==0)zero++;
                    if (likes.contains(map[nextRow][nextCol]))like++;
                }

                if(maxLike<like || (maxLike==like && maxZero<zero)){
                    row = i; col = j;
                    maxLike = like;
                    maxZero = zero;
                }
            }
        }

        map[row][col] = number;
    }

    static int getSatisfaction(int row, int col){
        List<Integer> list = likeFriends.get(map[row][col]);
        int count = 0;

        if(list==null) {
            System.out.println("de");
        }

        for(int i=0; i<4; i++){
            int nextRow = row+dr[i];
            int nextCol = col+dc[i];

            if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=N)continue;

            if(list.contains(map[nextRow][nextCol]))count++;
        }

        int result = 0;
        switch(count){
            case 0:
                result = 0;
                break;
            case 1:
                result = 1;
                break;
            case 2:
                result = 10;
                break;
            case 3:
                result = 100;
                break;
            case 4:
                result = 1000;
                break;
        }
        return result;
    }
}
/**
 * setPlcae 함수에서 zero와 like가 모두 0일 경우에도 row와 col이 다음 칸으로 업데이트 되어야하기 때문에 초기 값을 각각 -1로 설정해준다
 */