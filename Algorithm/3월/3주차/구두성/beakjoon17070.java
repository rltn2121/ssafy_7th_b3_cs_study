package DP;
import java.io.*;
import java.util.*;

public class beakjoon17070 {
    static int N;
    static int[][] map;
    static int[][][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        DP = new int[3][N+1][N+1];  //0 가로 //1 세로 //2 대각선

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DP[0][1][2] = 1;

        for(int i = 1; i <= N; i++){
            for(int j = 2 ; j <= N; j++){
                if(map[i][j] == 0){
                //가로
                    DP[0][i][j] = DP[0][i][j] + DP[0][i][j-1] + DP[2][i][j-1];
                //세로
                    DP[1][i][j] = DP[1][i][j] + DP[1][i-1][j] + DP[2][i-1][j];
                //대각선
                    if(map[i][j-1] == 0 && map[i-1][j] == 0){
                        DP[2][i][j] = DP[2][i][j] + DP[0][i-1][j-1] + DP[1][i-1][j-1] + DP[2][i-1][j-1];
                    }
                }
                
            }
        }
        System.out.println(DP[0][N][N]+DP[1][N][N]+DP[2][N][N]);
    }
}
