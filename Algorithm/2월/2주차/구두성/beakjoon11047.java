package Greedy;
import java.util.Arrays;
import java.util.Scanner;

public class beakjoon11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] coin = new int[N];

        for(int i = 0 ; i < N; i++){
            coin[i] = sc.nextInt();
        }
        Arrays.sort(coin);
        int ans = 0;
        for(int i = N-1; i >= 0; i--){
            ans += (K / coin[i]);
            K = K % coin[i];
        }
        System.out.println(ans);
        sc.close();
    }
}
