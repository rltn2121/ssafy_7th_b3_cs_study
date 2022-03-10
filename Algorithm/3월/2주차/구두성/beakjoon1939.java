package 다시풀기;
import java.io.*;
import java.util.*;


public class beakjoon1939 {
    static int N,M;
    static List<node1939>[]graph;
    static boolean[] visited;
    static int to;
    static int from;
    static boolean solve(int node, int weight){
        if(node == to) return true;
        visited[node] = true;
        for(node1939 n : graph[node]){
            if(visited[n.b]) continue;
            if(n.c >= weight){
                if(solve(n.b, weight)){
                    return true;
                }
            }
        }
        visited[node] = false;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        M = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new node1939(b, c));
            graph[b].add(new node1939(a, c));
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 10_0000_0000;
        int ans = 0;
        while(left <= right){
            int mid = (right + left) >> 1;

            visited = new boolean[N+1];
            if(solve(from, mid)){
                ans = mid;
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }

        }

        System.out.println(ans);
    }
}
class node1939{
    int b,c;
    public node1939(int b, int c){
        this.b = b;
        this.c = c;
    }
}