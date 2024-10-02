package week12.톱니바퀴;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14891 {
    static int[] rotateDir;
    static int[] dr = {-1, 1};
    static int[][] cogwheel;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cogwheel = new int[4][8];
        for(int i=0; i<4; i++){
            String line = br.readLine();
            for(int j=0; j<8; j++){
                cogwheel[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int wheel = Integer.parseInt(st.nextToken());
            int cmd = Integer.parseInt(st.nextToken());
            execute(wheel-1, cmd);
        }

        System.out.println(getResult());
    }

    static void execute(int wheel, int cmd){
        rotateDir = new int[4];
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[4];

        rotateDir[wheel] = cmd;
        queue.add(wheel);
        visited[wheel] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int i=0; i<2; i++){
                int next = now+dr[i];

                if(next<0 || next>=4)continue;
                if(visited[next])continue;
                if(i==0 && cogwheel[now][6]==cogwheel[next][2])continue;
                if(i==1 && cogwheel[now][2]==cogwheel[next][6])continue;

                rotateDir[next] = rotateDir[now]*-1;
                queue.add(next);
                visited[next] = true;
            }
        }

        for(int i=0; i<4; i++){
            rotate(i, rotateDir[i]);
        }
    }

    static void rotate(int wheel, int dir){
        if(dir==1){
            int temp = cogwheel[wheel][7];
            for(int i=7; i>0; i--){
                cogwheel[wheel][i] = cogwheel[wheel][i-1];
            }
            cogwheel[wheel][0] = temp;
        }
        else if(dir==-1){
            int temp = cogwheel[wheel][0];
            for(int i=0; i<7; i++){
                cogwheel[wheel][i] = cogwheel[wheel][i+1];
            }
            cogwheel[wheel][7] = temp;
        }
    }

    static int getResult(){
        int sum = 0;
        for(int i=0; i<4; i++){
            switch (i+1){
                case 1:
                    if(cogwheel[i][0]==0)sum+=0;
                    else if(cogwheel[i][0]==1)sum+=1;
                    break;
                case 2:
                    if(cogwheel[i][0]==0)sum+=0;
                    else if(cogwheel[i][0]==1)sum+=2;
                    break;
                case 3:
                    if(cogwheel[i][0]==0)sum+=0;
                    else if(cogwheel[i][0]==1)sum+=4;
                    break;
                case 4:
                    if(cogwheel[i][0]==0)sum+=0;
                    else if(cogwheel[i][0]==1)sum+=8;
                    break;
            }
        }
        return sum;
    }
}
