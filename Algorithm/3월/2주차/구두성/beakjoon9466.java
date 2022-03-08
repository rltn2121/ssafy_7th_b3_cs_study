import java.io.*;
import java.util.*;


public class beakjoon9466 {
    static int N,T;
    static int[] arr = new int[100_001];
    static int[] team = new int[100_001];
    /*
    static int dfs(int root, int idx){
        if(arr[idx] == root){
            team[idx] = 1;
            return 1;
        }
        if(team[idx] != 0) return 0;

        team[idx] = -1;
        int ret = dfs(root, arr[idx]);
        team[idx] = ret;
        
        if(ret <= 0){
            return 0;
        }
        else{
            return ret + 1;
        }
    }
    */
    static int dfs(int idx, int cnt){
        team[idx] = cnt;

        if(team[ arr[ idx ] ] < 0) {    //이미 방문한 곳이면 사이클이 생기지 않음
            team[idx] = -2;
            return 0;
        }

        if(team[ arr[ idx ] ] > 0){     //현재 지나간 경로에서 사이클이 생긴 경우
            int ret = cnt - team[ arr[idx] ];
            team[idx] = -1;
            return ret;
        }
        
        int ret = dfs(arr[idx], cnt + 1);   //사이클을 형성한 개수

        if(ret > 0){                    //남은 사이클의 개수가 남았으면 -1
            team[idx] = -1;
            return ret - 1;
        }
        else{                           //사이클을 형성하지 않는 경로일 경우 -2
            team[idx] = -2;
            return 0;
        }
        
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i <= N; i++){
                team[i] = 0;
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int ans = N;
            for(int i = 1 ; i <= N; i++){
                if(team[i] == 0) dfs(i, 1);
                if(team[i] == -1) ans--; 
                
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);


    }   
    
}
