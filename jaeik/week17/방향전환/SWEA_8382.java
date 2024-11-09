package week17.방향전환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_8382 {
    static int result;
    static int x1,x2,y1,y2;
    static List<int[]> H = new ArrayList<>();
    static List<int[]> L = new ArrayList<>();
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        H.add(new int[] {1,0});
        H.add(new int[] {-1,0});
        L.add(new int[] {0,1});
        L.add(new int[] {0,-1});

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            result = 0;
            bfs();

            System.out.println(result);
        }
    }

    static void bfs(){
        flag = false;
        Queue<int[]> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(new int[] {x1, y1, 0});
        visited.add(x1+","+x2);

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int moveCount = cur[2];

            if(x==x2 && y==y2){
                result = moveCount;
                return;
            }

            //H 세로
            if(!flag){
                for(int i=0; i<H.size(); i++){
                    int[] d = H.get(i);
                    int dx = d[0]; int dy = d[1];

                    int nextX = x+dx;
                    int nextY = y+dy;

                    if(nextX<-100 || nextY<-100 || nextX>100 || nextY>100)continue;
                    if(visited.contains(nextX+","+nextY))continue;

                    queue.add(new int[] {nextX, nextY, moveCount+1});
                    visited.add(nextX+","+nextY);
                }
                flag = !flag;
            }
            //L 가로
            else{
                for(int i=0; i<L.size(); i++){
                    int[] d = L.get(i);
                    int dx = d[0];int dy = d[1];

                    int nextX = x+dx;
                    int nextY = y+dy;

                    if(nextX<-100 || nextY<-100 || nextX>100 || nextY>100)continue;
                    if(visited.contains(nextX+","+nextY))continue;

                    queue.add(new int[] {nextX, nextY, moveCount+1});
                    visited.add(nextX+","+nextY);
                }
                flag = !flag;
            }
        }
    }
}
