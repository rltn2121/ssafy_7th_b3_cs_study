import java.io.*;
import java.util.*;

public class beakjoon12764 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][]arr  = new int [N][3];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        PriorityQueue<int[]> computer = new PriorityQueue<>((a,b)->a[1] - b[1]);
        PriorityQueue<Integer> empty = new PriorityQueue<>();
        int[] count = new int[N];

        computer.add(arr[0]);
        count[0] = 1;
        int cnt = 1;
        for(int i = 1 ; i < N; i++){
            if(){
                arr[i][2] = empty.poll();
                count[arr[i][2]]++;
                computer.add(arr[i]);
                continue;
            }

            if(!empty.isEmpty() || computer.peek()[1] <= arr[i][0]){
                while(computer.peek()[1] <= arr[i][0]){
                    empty.add(computer.poll()[2]);
                }

                arr[i][2] = empty.poll();
                count[arr[i][2]]++;
                computer.add(arr[i]);
            }
            
            else{
                arr[i][2] = cnt;
                computer.add(arr[i]);
                count[cnt] = 1;
                cnt++;
            }
            
        }

        System.out.println(cnt);
        for(int i = 0; i < cnt; i++){
            System.out.print(count[i] + " ");
        }

    }
}
