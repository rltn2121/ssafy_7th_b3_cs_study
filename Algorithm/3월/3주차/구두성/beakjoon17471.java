import java.io.*;
import java.util.*;

public class beakjoon17471 {
    static int[][] graph;
    static int N;
    static int[] area;
    static boolean[] total;
    static int bit;
    static boolean DFS(int form){
        if(bit == 0) return true;
        
        boolean ret = false;
        for(int i = 0 ; i < N; i++){
            if(graph[form][i] != 1) continue;
            if((bit & 1<<i) == 1<<i ){
                bit = bit - (1 << i);
                ret |= DFS(i);
            }
        }

        return ret;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        area = new int[N];
        graph = new int[N][N];
        total = new boolean[1<<N + 1];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            area[i] = Integer.parseInt(st.nextToken());
            sum += area[i];
        }

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < n; j++){
                int a = Integer.parseInt(st.nextToken())-1;
                graph[i][a] = 1;
                graph[a][i] = 1;
            }
        }

        int ans = sum;
        for(int i = 1; i < (1<<N) - 1 ; i++){
            if(total[i]) continue;

            int temp = 0;
            int idx = 0;
            int idx2 = 0;
            for(int b = 0; b < N; b++){
                if( (i & (1 << b)) == (1 << b)){
                    temp += area[b];
                    idx = b;
                }
                else{
                    idx2 = b;
                }
            }//업뎃

            total[i] = true;
            total[i ^ ((1 << N ) - 1) ] = true;

            int diff = Math.abs(sum - temp - temp);
            if(diff >= ans) continue;

            //두 선거구가 갈 수 있는지 확인
            bit = i - (1<<idx);
            if(!DFS(idx)) continue;
            
            bit = i ^ ((1 << N ) - 1) - (1 << idx2) ;
            if(!DFS(idx2))continue;
            
            ans = diff;
        }
        if(ans == sum) System.out.println(-1);
        else System.out.println(ans);


    }

}
