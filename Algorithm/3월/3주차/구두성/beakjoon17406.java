package BruteForce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class beakjoon17406 {
    static int N;
    static int M;
    static int K;
    static int[][][] map2;
    static int[][] role;
    static int ans = Integer.MAX_VALUE;
    static boolean[] visited;

    static void DFS(int depth){
        if(depth == K+1){
            int temp1 = Integer.MAX_VALUE;
            for(int i =1; i <= N; i++){
                int temp = 0;
                for(int j =1; j <= M; j++){
                    temp += map2[K][i][j];
                }
                temp1 = Math.min(temp1, temp);
            }
            ans = Math.min(ans, temp1);
            return;
        }
        
        for(int i = 0; i < K; i++){
            if(visited[i]) continue;

            for(int x = 1; x <= N; x ++){   //copy array
                for(int y = 1; y <=M;y++){
                    map2[depth][x][y] = map2[depth-1][x][y];
                }
            }

            for(int k = 1; k <= role[i][2]; k++){   //rotate
                int x1 = role[i][0] - k;
                int y1 = role[i][1] - k;
                int x2 = role[i][0] + k;
                int y2 = role[i][1] + k;
    
                for(int j = 1; j <= x2 - x1; j++){
                    map2[depth][x1][y1 + j] =  map2[depth - 1][x1][y1 + j - 1];
                    map2[depth][x1 + j][y2] =  map2[depth - 1][x1 + j - 1][y2];
                    map2[depth][x2][y2 - j] =  map2[depth - 1][x2][y2 - j + 1];
                    map2[depth][x2 - j][y1] =  map2[depth - 1][x2 - j + 1][y1];
                }
            }
            visited[i] = true;
            DFS(depth+1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map2 = new int[K+1][N+1][M+1];
        role = new int[K+1][3];
        visited = new boolean[K+1];
        Arrays.fill(visited, false);

        for(int i =1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =1; j <= M; j++){
                map2[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            role[i][0] = Integer.parseInt(st.nextToken());
            role[i][1] = Integer.parseInt(st.nextToken());
            role[i][2] = Integer.parseInt(st.nextToken());
        }

        DFS(1);
        System.out.println(ans);
    }
}
