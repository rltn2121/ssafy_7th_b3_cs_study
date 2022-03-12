import java.io.*;
import java.util.*;
public class beakjoon16637 {
    static int N,M;
    static char[] str;
    static int ans = Integer.MIN_VALUE;
    static int culc(int a, char ch, int b){
        if(ch == '+'){
            return a + b;
        }
        else if(ch == '-'){
            return a - b;
        }
        else{
            return a*b;
        }
    }
    static void solve(int idx, int ret, char pre){
        if(idx == N){
            if(pre != ' ') return;
            if(ans < ret){
                ans = ret;
            }
            return;
        }
        
        //괄호를 추가해서 이번걸 먼저 계산하고 이전 걸 계산
        if(pre != ' '){
            int temp = culc(str[idx-1] - '0', str[idx], str[idx+1] - '0');
            solve(idx+2, culc(ret, pre,temp), ' ');
        }
        else{
            solve(idx+2, culc(ret, str[idx], str[idx+1]-'0'), ' ');
            solve(idx+2, ret, str[idx]);
        }

    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        str = br.readLine().toCharArray();

        solve(1, str[0] - '0', ' ');
        System.out.println(ans);
    }
}
