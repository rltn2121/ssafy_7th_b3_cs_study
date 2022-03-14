package BruteForce;
import java.io.*;
import java.util.*;
/*
    백트래킹 할 때 값을 넘겨줄 경우 이제까지 새로운 맵을 copy해서 넘겨줬는데
    새로운 배열 만들어서 복사하는 것보다 그냥 원래 맵을 수정하는게 훨씬 빠르고 가벼움

    이 문제를 native하게 짜면 재귀호출의 최대 깊이가 16(1 * 5 + 2 * 2 * 5+ 3 * 3 * 5 + 4 * 4)까지 가는데
    길이가 10*10이라도 수행시간이 굉장히 오래 걸려서 그리디하게 5부터 탐색을 한다던지 다른 방법을 사용해야함.

    그래서 생각한 게 DP처럼 남은 1의 개수에 cnt가 많을 경우 리턴시켜줬었는데 반례가 있어서 틀렸습니다. 나옴

    재귀 호출 할 경우 호출발은 좌표 기준으로 이전 값들은 신경 쓸 필요 없음, 이미 채워지면서 오기 때문에
    아니라면 리턴해서 재귀를 받을 떄 마다 다시 탐색하지 않음으로 시간 절약

    그리고 색종이로 다 채웠을 경우의 최소값과 탐색 하는 중의 최소값을 비교해서 cnt가 같거나 크면 리턴해줌으로
    시간을 대폭 줄임

    1. 새로운 배열 + 호출 받을 떄마다 완탐 -> 시간 초과
    2. 새로운 배열 + 이전 값 계산 X -> 1224ms
    3. 원래 배열 수정 + 이전 값 계산 X -> 892ms
    4. 3) + 최소값을 비교 -> 216ms

    참고 소스//https://www.acmicpc.net/source/38468165
*/
public class beakjoon17136 {
    static int[][] paper;
    static int ans = 10000;
    static int[] DP;
    static int[] count = {0, 5, 5, 5, 5, 5};

    static boolean check(int x, int y, int size){
        if(size + x > 10 || size + y > 10) return false;

        for(int i = 0 ; i < size; i++){
            for(int j = 0 ; j < size; j++){
                if(paper[i + x][j + y] == 0) return false;            
            }
        }

        return true;
    }
    static int[][] CopyPaper(int[][] p){
        int[][] copy = new int[10][10];
        for(int i = 0 ; i < 10; i++){
            for(int j = 0 ; j < 10; j++){
                copy[i][j] = p[i][j];
            }
        }
        return copy;
    }
    static void fill(int x, int y, int size, int color){
        for(int i = 0 ; i < size; i++){
            for(int j = 0 ; j < size; j++){
                paper[i + x][j + y] = color;           
            }
        }
    }
    static void solve(int cnt, int position){
        if(position == 100){
            ans = Math.min(ans, cnt);
            return;    
        }
        if(ans <= cnt) return;

        int x = position / 10;
        int y = position % 10;
        
        if(paper[x][y] == 0){
            solve(cnt , position+1);
            return;
        }
                
        for(int size = 5; size >= 1; size--){
            if(count[size] <= 0)continue;
                             

            if(!check(x, y, size))continue;
                
            fill(x,y,size,0);
            count[size]--;
            solve(cnt + 1, position+1);
            count[size]++;
            fill(x,y,size,1); 
                
                
        }//end for_size         
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
    
        paper = new int[10][10];

        for(int i = 0 ; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 10; j++){
                paper[i][j] = st.nextToken().charAt(0) -'0';
            }
        }
    
        solve(0, 0);

        if(ans == 10000) System.out.println(-1);
        else System.out.println(ans);
    }
}
