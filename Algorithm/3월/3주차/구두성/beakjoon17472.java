package implemented;
import java.io.*;
import java.util.*;

public class beakjoon17472 {
    static int N,M;
    static int[][] map;
    static Boolean[] visited;
    static int[][] graph;
    static ArrayList<int[]>[] node;
    static int island_idx;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};

    static void DFS(){
        int cnt = 1;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j ++){
                if(map[i][j] == -1){
                    DFS(i,j,cnt++);
                }
            }
        }
        island_idx = cnt;
    }
    
    static void DFS(int y, int x, int cnt){
        map[y][x] = cnt;
        int temp[] = new int[2];
        temp[0] = y;
        temp[1] = x;
        node[cnt].add(temp);

        for(int i = 0 ; i < 4; i++){
            int cx = x + dx[i];
            int cy = y + dy[i];
            if(cy >= N || cy < 0 || cx >= M || cx < 0) continue;
            if(map[cy][cx] == -1){
                
                DFS(cy, cx, cnt);
            }
        }
        
    }

    static void create_graph(){
        for(int i =1; i < island_idx; i++){
            Arrays.fill(graph[i], 1000000);
        }

        for(int idx = 1; idx < island_idx; idx++ ){
            for(int[] p : node[idx]){
                to : for(int i = 0 ; i <4; i++){
                    int cy = p[0] + dy[i];
                    int cx = p[1] + dx[i];
                    int cnt = 0;
                    while(true){
                        if(cy >= N || cy < 0 || cx >= M || cx < 0 || map[cy][cx] == idx) continue to;
                        if(map[cy][cx] != 0) break;
                        cnt++;
                        cy+=dy[i];
                        cx+=dx[i];
                    }
                    if(cnt != 0 && cnt > 1 && graph[idx][map[cy][cx]] > cnt){    
                        graph[idx][map[cy][cx]] = cnt;
                        graph[map[cy][cx]][idx] = cnt;
                    }
                }
            }
        }

    }

    static int solve(){
        int ret = 0;
        PriorityQueue<Node17472> pq = new PriorityQueue<>();
        pq.add(new Node17472(1,0));
        while(!pq.isEmpty()){
            Node17472 idx = pq.poll();

            if(visited[idx.a]) continue;

            else{
                visited[idx.a] = true;
                ret += idx.b;
            }
            for(int i = 1; i < island_idx;i++){
                if(!visited[i]){
                    pq.add(new Node17472(i, graph[idx.a][i]));
                }
            }

        }
        return ret;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        graph = new int[7][7];
        visited = new Boolean[7];
        Arrays.fill(visited ,false);
        node = new ArrayList[7];
        for(int i = 1 ; i <= 6; i++) node[i] = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                map[i][j] = st.nextToken().charAt(0) - '0';
                map[i][j] *= -1;
            }
        }
        DFS();
        create_graph();
        int ans = solve();
        if(ans >= 1000000)System.out.println(-1);
        else System.out.println(ans);

    }

    static void print_map(){
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j ++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void print_graph(){
        for(int i = 1 ; i < island_idx; i++){
            for(int j = 1 ; j < island_idx; j ++){
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
class Node17472 implements Comparable<Node17472>{
    int a,b;
    public Node17472(int a, int b){
        this.a = a;
        this.b = b;
    }
    @Override
    public int compareTo(Node17472 o) {
        return this.b - o.b;
    }
}