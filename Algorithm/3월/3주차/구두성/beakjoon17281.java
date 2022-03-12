import java.io.*;
import java.util.*;
public class beakjoon17281 {
    static int N;
    static int[][] ening;
    static int[] arr = new int[9];
    static boolean[] selected = new boolean[9];
    static int ans = 0;
    static void solve(int cnt){
        if(cnt == 3) {
            solve(cnt + 1);
            return;
        }

        if(cnt == 9){
            int taza = 0;
            int score = 0;
            for(int eIdx = 0; eIdx < N; eIdx++){
                int out = 0;
                int[] lu = new int[3];

                while(true){
                    if(out == 3) break;
                    //3진아웃이면 이닝 종료
                    int temp = ening[eIdx][arr[taza]];
                    if(temp == 0) out++;
                    else if(temp == 1){
                        if(lu[2] == 1){
                            lu[2] = 0;
                            score++;
                        }
                        if(lu[1] == 1){
                            lu[1] = 0;
                            lu[2] = 1;
                        }
                        if(lu[0] == 1){
                            lu[1] = 1;
                        }
                        lu[0] = 1;
                    }
                    else if(temp == 2){
                        for(int a = 1 ; a < 3; a++){
                            if(lu[a] == 1) {
                                lu[a] = 0;
                                score++;
                            }
                        }
                        if(lu[0] == 1){
                            lu[2] = 1;
                            lu[0] = 0;
                        }
                        lu[1] = 1;
                    }
                    else if(temp == 3){
                        for(int a = 0 ; a < 3; a++){
                            if(lu[a] == 1) {
                                lu[a] = 0;
                                score++;
                            }
                        }
                        lu[2] = 1;
                    }
                    else if(temp == 4){
                        for(int a = 0 ; a < 3; a++){
                            if(lu[a] == 1) {
                                lu[a] = 0;
                                score++;
                            }
                        }
                        score++;
                    }
                    //타자의 타율에따른 업데이트

                    taza = (taza + 1) % 9;
                }// end while
                
            }//end for eIdx
            ans = Math.max(ans, score);
            return;
        }
        for(int i = 0 ; i < 9; i++){
            if(selected[i])continue;
            selected[i] = true;
            arr[cnt] = i;

            solve(cnt + 1);

            selected[i] = false;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        ening = new int[N][9];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 9; j++){
                ening[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        arr[3] = 0;
        selected[0] = true;
        solve(0);
        System.out.println(ans);
    }
}
