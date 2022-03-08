import java.io.*;
import java.util.*;

public class beakjoon1976 {
    static int N, M;
    static int[][] map;
    static int[] group;
    static void dfs(int idx, int cnt){
        group[idx] = cnt;

        for(int i = 1; i <= N; i++){
            if(map[idx][i] == 0) continue;
            if(group[i] != 0) continue;

            
            dfs(i, cnt);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        group = new int[N+1];
        map = new int[N+1][N+1];
        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i == j) map[i][j] = 1;
            }
        }
        int cnt = 1;
        for(int i = 1 ; i <= N; i++){
            if(group[i] != 0) continue;
            dfs(i, cnt++);
            
        }

        st = new StringTokenizer(br.readLine());
        int pre = Integer.parseInt(st.nextToken());
        boolean flag = false;
        for(int i = 1; i < M; i++){
            int cur = Integer.parseInt(st.nextToken());
            if(group[pre] != group[cur]) flag = true;
        }

        if(!flag){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
