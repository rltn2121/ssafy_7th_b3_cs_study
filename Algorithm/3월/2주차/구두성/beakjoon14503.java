package implemented;

import java.util.Scanner;

public class beakjoon14503 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 1;
    static int N = 0;
    static int M = 0;
    static void DFS(int x, int y, int d, int[][] map){
        map[x][y] = -1;
        
        for(int i = 0; i < 4 ; i++){
            d = (d + 3) % 4;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                ans++; 
                DFS(nx, ny, d, map);
                return;
            }
        }

        int direction = (d + 2) % 4;
        int cx = x + dx[direction];
        int cy = y + dy[direction];
        if(cx >= 0 && cx < N && cy >= 0 && cy < M && map[cx][cy] != 1) {
            DFS(cx, cy, d,map);
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();
        int d = sc.nextInt();

        int[][] map = new int[N+1][M+1];
        for(int i = 0; i < N ; i++){
            for(int j = 0; j < M ; j++){
                map[i][j] = sc.nextInt();
            }
        }
        DFS(x,y,d,map);
        System.out.println(ans);
        sc.close();
    }
}
