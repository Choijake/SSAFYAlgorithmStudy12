package week16.사다리조작;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15684 {
    static int min_result;
    static boolean possible;
    static List<int[]> save;
    static boolean[] visited;
    static int N, H, M;
    static List<int[]> ladders;
    static List<int[]> remains;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new ArrayList<>();
        for(int i=0; i<=N; i++){
            ladders.add(new int[H+1]);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladders.get(b)[a] = b+1;
            ladders.get(b+1)[a] = b;
        }

        int max = 0;
        remains = new ArrayList<>();
        for(int i=1; i<ladders.size(); i++){
            for(int j=1; j<ladders.get(i).length; j++){
                if(ladders.get(i)[j]==0){
                    remains.add(new int[] {i, j});
                    max++;
                }
            }
        }

        min_result = 0;
        for(int i=0; i<=max; i++){
            if(possible)break;
            save = new ArrayList<>();
            visited = new boolean[H+1];
            permutation(0, i);
        }

        min_result = (min_result>3||min_result==0)?-1:min_result;

        System.out.println(min_result);
    }

    static void permutation(int depth, int max){
        if(possible)return;

        if(depth == max){
            if(game()){
                min_result = max;
                possible = true;
            }
            return;
        }

        for(int i=0; i<remains.size(); i++){
            if(!visited[i]){
                save.add(remains.get(i));
                visited[i] = true;
                permutation(depth+1, max);
                save.remove(save.size()-1);
                visited[i] = false;
            }
        }
    }

    static boolean game(){

        for(int i=0; i< save.size(); i++){
            int[] connect = save.get(i);
            int b = connect[0];
            int a = connect[1];

            ladders.get(b)[a] = b+1;
            ladders.get(b+1)[a] = b;
        }

        for(int i=1; i<=N; i++){
            int[] start = ladders.get(i);
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(start);
            int destination = 0;

            while(!queue.isEmpty()){
                int[] cur = queue.poll();

                for(int j=1; j<=H; j++){
                    if(visited[j])continue;

                    int next = cur[j];
                    if(next==0){
                        visited[j] = true;
                        continue;
                    }

                    queue.add(ladders.get(next));
                    visited[j] = true;
                    destination = next;
                    break;
                }
            }

            if(destination!=i){
                return false;
            }
        }

        return true;
    }
}
