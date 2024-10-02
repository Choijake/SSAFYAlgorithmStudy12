package week11.나무재테크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16235 {
    static class Tree implements Comparable<Tree>{
        int row;
        int col;
        int age;

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return o.age-age;
        }
    }

    static class Field{
        int food;
        int foodForWinter;

        public Field(int food) {
            this.food = food;
        }
    }

    static int[] dr = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] dc = {1, -1, 0, 0, -1, 1, 1, -1};
    static int N, M, K;
    static Field[][] map;
    static List<Tree> treeList;
    static List<Tree> deadTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //밭 초기화
        map = new Field[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j] = new Field(5);
            }
        }

        //겨울 양분 정보 추가
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j].foodForWinter = Integer.parseInt(st.nextToken());
            }
        }

        //나무 정보 추가
        treeList = new ArrayList<>();
        deadTree = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            Tree tree = new Tree(row, col, age);
            treeList.add(tree);
        }

        timePass();

        System.out.println(treeList.size());
    }

    static void timePass(){
        while(K-->0){
            spring();
            summer();
            autumn();
            winter();
        }
    }

    static void spring(){
        //age 순으로 정렬
        Collections.sort(treeList);

        //remove로 treeList의 원소를 지우면 O(N) 시간 복잡도 발생하므로
        //새로운 리스트에 죽지 않은 나무를 추가해주고 static 리스트가 참조하도록 한다
        List<Tree> treeList1 = new ArrayList<>();
        for(int i= treeList.size()-1; i>=0; i--){
            
            Tree tree = treeList.get(i);

            //나무 나이만큼 양분 없으면 나무 죽음
            if(map[tree.row][tree.col].food< tree.age){
                deadTree.add(tree);
                continue;
            }
            //나무 나이 만큼 양분 사라짐
            treeList1.add(tree);
            map[tree.row][tree.col].food -= tree.age;
            //나이 1 증가
            tree.age++;
        }

        treeList = treeList1;
    }

    static void summer(){
        for(int i=0; i<deadTree.size(); i++){
            Tree dead = deadTree.get(i);
            //죽은 나무 나이/2를 토지 양분으로 추가
            int food = dead.age/2;
            map[dead.row][dead.col].food += food;
        }
        deadTree = new ArrayList<>();
    }

    static void autumn(){
        for(int i=0; i< treeList.size(); i++){
            Tree tree = treeList.get(i);

            if(tree.age%5==0){
                for(int r=0; r<8; r++){
                    int nextRow = tree.row + dr[r];
                    int nextCol = tree.col + dc[r];

                    if(nextRow<1 || nextCol<1 || nextRow>N || nextCol>N)continue;

                    treeList.add(new Tree(nextRow, nextCol, 1));
                }
            }
        }
    }

    static void winter(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j].food += map[i][j].foodForWinter;
            }
        }
    }
}
