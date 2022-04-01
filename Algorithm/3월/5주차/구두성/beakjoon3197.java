package Graph;
import java.io.*;
import java.util.*;

public class beakjoon3197 {
    static int N,M;
    static int[][] bird;
    static boolean[][] map;
    static boolean[][] moveMap;

    static Queue<int[]> moveQ = new LinkedList<>();
    static Queue<int[]> nextMove = new LinkedList<>();
    static Queue<int[]> iceQ = new LinkedList<>();

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    static void melt(){
        int cnt = iceQ.size();
        /*  iceQ = 해당 라운드에 삭제해야할 리스트 
            meltMap = 녹을 얼음 체크
        */

        while(cnt-- > 0){//이전에 넣은 사이즈만큼만 뽑음
            int[] node = iceQ.poll();
            
            for(int i = 0 ; i < 4; i++){
                int cy = node[0] + dy[i];
                int cx = node[1] + dx[i];
                if(cx < 0 || cx >= M || cy < 0 || cy >= N) continue;
                //얼음이고 아직 방문하지 않은 곳 업데이트
                if(map[cy][cx]){
                    iceQ.add(new int[]{ cy, cx });
                    map[ cy ][ cx ] = false;
                } 
            }
        }
    }
    static boolean move(){
        moveQ = nextMove;
        nextMove = new LinkedList<>();

        while(!moveQ.isEmpty()){
            int[] node = moveQ.poll();
            
            for(int i = 0 ; i <4; i++){
                int cx = node[1] + dx[i];
                int cy = node[0] + dy[i];
                if(cx < 0 || cx >= M || cy < 0 || cy >= N) continue;

                //첫번째 백조가 움직일 수 있는 경로를 업데이트
                //물이고 아직 방문하지 않은 곳
                if(!map[cy][cx] && !moveMap[cy][cx]){
                    if(cx == bird[1][1] && cy == bird[1][0]) return true;
                    moveMap[cy][cx] = true;
                    moveQ.add(new int[]{ cy, cx });
                }
                //다음에 갈 수 있는 경로들을 nextMove 저장
                //해당 자리는 얼었고 다음에 녹을 예정이며 아직 방문하지 않은 곳
                else if( map[cy][cx] && !moveMap[cy][cx]) {
                    moveMap[cy][cx] = true;
                    nextMove.add(new int[]{ cy, cx });
                    
                }
            }
        }
        
        return false;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        moveMap = new boolean[N][M];

        bird = new int[2][2];
        
        int idx = 0;
        String str;
        for(int i = 0 ; i < N; i++){
            str = br.readLine();
            for(int j = 0 ;j < M; j++){
                if(str.charAt(j) == 'X'){
                    map[i][j] = true;
                }
                else{
                    if(str.charAt(j) == 'L'){
                        bird[idx][0] = i;
                        bird[idx++][1] = j;
                    }
                    map[i][j] = false;
                    iceQ.add(new int[]{ i, j });
                }
            }//end for j
        }// end for i
        

        nextMove.add(bird[0]);
        moveMap[ bird[0][0] ][ bird[0][1] ] = true;

        int ans = 0;
        while(true){
            if(move()) break;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ;j < M; j++){
                    int t = 0;
                    if(map[i][j]) t = 1;
                    else t= 0;
                    System.out.print(t + " ");
                }
                System.out.println();
            }
            System.out.println();
            

            ans++;
            melt();
        }

        
        System.out.println(ans);
    }

}
