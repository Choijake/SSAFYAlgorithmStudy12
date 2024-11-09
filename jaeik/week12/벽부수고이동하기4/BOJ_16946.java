package week12.벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int N, M;
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static int[][] map;
    static int[][] group;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //map 초기화
        map = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j)-'0';
            }
        }

        //이동 가능한 구역을 그룹화
        group = new int[N][M];
        int index = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //이동 가능한 구역인지 확인
                if(map[i][j]==0 && group[i][j]==0){
                    //hashmap[구역 번호, 이동 가능한 공간 수]
                    hashMap.put(index, bfs(i, j, index));
                    index++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(getCount(i, j));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int getCount(int row, int col){
        int sum = 1;
        HashSet<Integer> hashSet = new HashSet<>();

        //0이면 원래 이동 가능하므로 리턴함
        if(map[row][col]==0)return 0;

        for(int i=0; i<4; i++){
            int nextRow = row+dr[i];
            int nextCol = col+dc[i];

            if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=M)continue;
            //그룹화 되어있지 않은 곳은 건너뛴다
            if(group[nextRow][nextCol]==0)continue;
            //이동 가능한 곳일 경우, 해당 지역의 그룹 번호를 중복 없이 셋에 추가한다
            if(map[nextRow][nextCol]==0)hashSet.add(group[nextRow][nextCol]);
        }

        //추가된 그룹들을 순회하면서 그룹에 포함된 지역의 개수를 더한다
        for(int idx : hashSet){
            sum += hashMap.get(idx);
        }

        //더한 값%10을 리턴한다
        return sum%10;
    }

    static int bfs(int row, int col, int index){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col});
        group[row][col] = index;
        //자기 자신을 포함해 count를 1로 초기화
        int count = 1;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            //사방 탐색
            for(int i=0; i<4; i++){
                int nextRow = now[0] + dr[i];
                int nextCol = now[1] + dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=M)continue;
                //이동가능한 장소 && 그룹화돼있지 않은 장소 일 때
                if(map[nextRow][nextCol]==0 && group[nextRow][nextCol]==0){
                    queue.offer(new int[]{nextRow, nextCol});
                    //그룹 번호 부여, 카운트 증가
                    group[nextRow][nextCol] = index;
                    count++;
                }
            }
        }
        return count;
    }
}
