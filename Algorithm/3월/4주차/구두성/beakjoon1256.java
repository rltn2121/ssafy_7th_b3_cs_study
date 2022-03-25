package DP;
import java.io.*;
import java.util.*;
public class beakjoon1256 {
    static int N,M;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
    
        N +=M;
        int[][] arr = new int[N+1][M+1];
        
        arr[0][0] = 1;
        for(int i = 1; i <= N;i++){
            arr[i][0] = 1;
            for(int j = 1; j <= M; j++){
                arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
                if(arr[i][j] > 1000000000) arr[i][j] = 1000000001;
            }
        }

        if(arr[N][M] < K){
            System.out.println(-1);
            return;
        } 

        StringBuilder sb = new StringBuilder();
        for(int i = N-1; i >= 0; i--){
            if(arr[i][M] >= K){
                sb.append("a");
            }
            else{
                sb.append("z");
                K-=arr[i][M];
                M--;
            }
        }
        System.out.print(sb);

    }
    
}
