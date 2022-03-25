package Greedy;
import java.io.*;
import java.util.*;


public class beakjoon2812 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N,M;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        
        Queue<Integer> que = new LinkedList<>();
        int[] nums = new int[10];
        int max_val = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N; i++){
            int num = str.charAt(i)-'0';
            if(num > max_val) max_val = num;
            nums[num]++;
            que.add(num);

            if(i < M) continue;
            // 지워야하는 수가 더 많을떄 아래는 수행하지 않음
            
            sb.append(max_val);
            
            while(true){
                if(que.peek() == max_val) break;
                nums[que.poll()]--;
            }
            nums[que.poll()]--;

            if(que.isEmpty()) max_val = 0;
            else if(nums[max_val] == 0){
                max_val = 0;
                for(int n = 9; n >= 0; n--){
                    if(nums[n] > 0){
                        max_val = n;
                        break;
                    }
                }
            }//end if
            
        }
        
        System.out.println(sb);
    }
}
