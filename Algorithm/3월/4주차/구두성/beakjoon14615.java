package Graph;
import java.io.*;
import java.util.*;

public class beakjoon14615 {
    static int N,M;
    static boolean[] visited;
    static boolean[] visited2;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] graph2;
    static void dfs(int idx){
        visited[idx] = true;

        for(int i : graph[idx]){
            if(visited[i]) continue;
            dfs(i);
        }
    }
    static void dfs2(int idx){
        visited2[idx] = true;

        for(int i : graph2[idx]){
            if(visited2[i]) continue;
            dfs2(i);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];

        graph2 = new ArrayList[N+1];
        visited2 = new boolean[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph2[b].add(a);
        }
        dfs(1);
        dfs2(N);

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int a = Integer.parseInt(br.readLine());
            if(visited[a] && visited2[a]){
                sb.append("Defend the CTP\n");
            }
            else{
                sb.append("Destroyed the CTP\n");
            }
        }

        System.out.print(sb);
    }
}
