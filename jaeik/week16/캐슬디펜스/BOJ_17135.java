package week16.캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17135 {
    static class Archer{
        int row;
        int col;

        public Archer(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static int maxKill;
    static List<int[]> place;
    static Archer[] archers;
    static int[][] map;
    static int[][] copy;
    static int N, M, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        copy = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }

        //Archer를 저장할 자료구조를 좀 오래 고민함
        archers = new Archer[3];
        place= new ArrayList<>();
        for(int i=1; i<=M; i++){
            place.add(new int[]{N+1, i});
        }

        maxKill = 0;
        combination(0, 0);

        System.out.println(maxKill);
    }

    static void combination(int depth, int start){
        if(depth == 3){
            maxKill = Math.max(maxKill, simulate());
            return;
        }

        for(int i=start; i<M; i++){
             archers[depth] = new Archer(place.get(i)[0], place.get(i)[1]);
             combination(depth+1, i+1);
        }
    }

    static int simulate(){
        int killCount = 0;
        
        // #1 MAP 초기화
        restoreMap();

        // #2 시뮬레이션 시작 (MAP에 모든 적이 없을 때까지)
        while(!isAllKill()){
            HashMap<Integer, int[]> killEnemy = new HashMap<>();
            
            // #2-1 배치된 궁수가 죽일 적을 저장
            for(int i=0; i<archers.length; i++){
                killEnemy.put(i, getKillEnemy(archers[i]));
            }

            // #2-2 저장된 적을 모두 죽이고 죽인 궁수의 수를 업데이트
            for(int i=0; i< killEnemy.size(); i++){
                int[] kill = killEnemy.get(i);
                
                //궁수가 적을 죽이지 못하는 경우가 있기 때문에 null exception을 방지해준다
                if(kill==null){
                    continue;
                }
                int row = kill[0];
                int col = kill[1];

                if(map[row][col]==0)continue;
                map[row][col] = 0;
                killCount++;
            }

            // #3 적들을 MAP상에서 이동시킨다
            moveEnemy();
        }

        return killCount;
    }

    static int[] getKillEnemy(Archer archer){
        int row = archer.row;
        int col = archer.col;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            //궁수가 죽일 적을 결정 하는 핵심 적인 부분
            //궁수와 적의 거리가 같으면 열을 기준으로 오름차순
            //이외에는 궁수와 적의 거리를 기준으로 오름차순
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2]==o2[2]){
                    return o1[1]-o2[1];
                }
                else return o1[2]-o2[2];
            }
        });

        //열보다 D가 작으면 D범위 안의 적만 죽일 수 있기 때문에 범위를 벗어나느 곳을 탐색하지 않도록 start를 초기화해준다
        int start = (N>D)?N-D+1:1;
        for(int i=start; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(map[i][j]==0)continue;

                //적과 궁수의 거리를 계산하고 pq에 넣어준다
                int dist = Math.abs(i-row)+Math.abs(j-col);
                if(dist<=D){
                    pq.add(new int[] {i, j, dist});
                }
            }
        }

        return pq.poll();
    }

    static void moveEnemy(){
        for(int i=1; i<=M; i++){
            for(int j=N; j>=1; j--){
                if(j==N){
                    map[j][i] = 0;
                }
                else if(map[j][i]==1){
                    map[j+1][i] = map[j][i];
                    map[j][i] = 0;
                }
            }
        }
    }

    static boolean isAllKill() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (map[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    static void restoreMap(){
        for(int i=0; i<=N; i++){
            for(int j=0; j<=M; j++){
                map[i][j] = copy[i][j];
            }
        }
    }
}
