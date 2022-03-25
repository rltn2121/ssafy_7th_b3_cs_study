package SlidingWindow;
import java.io.*;
import java.util.*;

public class beakjoon15961 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, d, k, c;
        N = Integer.parseInt(st.nextToken());   // 벨트
        d = Integer.parseInt(st.nextToken());   // 초밥 가짓수
        k = Integer.parseInt(st.nextToken());   // 연속해서 먹는 수
        c = Integer.parseInt(st.nextToken());   // 쿠폰 번호

        int[] belt = new int[N];
        int[] piece = new int[d+1];

        for(int i = 0 ; i < N; i++){
            belt[i] = Integer.parseInt(br.readLine());
        }

        Set<Integer> eat = new HashSet<>();
        for(int i = 0; i < k; i++){
            piece[ belt[i] ]++;
            eat.add(belt[i]);
        }
        int ans = eat.size();
        if(piece[c] == 0) ans++;

        int left = 0;
        int right = k-1;
        while(left < N){
            int left_val = belt[left];
            
            if(piece[ left_val ] == 1){
                eat.remove(left_val);
            }
            piece[ left_val ]--;
            left++;

            right++;
            right = right % N;
            int right_val = belt[right];
            if(piece[ right_val ] == 0){
                eat.add(right_val);
            }
            piece[ right_val ]++;

            int temp = eat.size();
            if(piece[c] == 0) temp++;
            if(ans < temp) ans = temp;
        }
        System.out.println(ans);
    }
}
/*  이렇게도 가능하구나..
백준 	phoebe7075 님 코드
        while (idx < N+k) {
            int tmp = arr[idx%N];
            if(size < k) {
                size++;
            }else {
                sushi[arr[(idx+N-k)%N]]--;
                if(sushi[arr[(idx+N-k)%N]] == 0) {
                    count--;
                }
            }
            if(sushi[tmp] > 0) {
                sushi[tmp]++;
            }else {
                sushi[tmp]++;
                count++;
            }

            if(sushi[c] == 0) {
                ans = Math.max(ans, count+1);
            }else {
                ans = Math.max(ans, count);
            }

            idx++;
        }

*/