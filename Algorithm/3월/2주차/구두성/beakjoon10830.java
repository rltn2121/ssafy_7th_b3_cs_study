package Divide_And_Conquer;
import java.util.Scanner;

public class beakjoon10830 {
    static int N;
    static long B;
    static int[][] metrics;

    static public int[][] culc(int[][] a, int[][] b){
        int [][] result = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    result[i][j] += a[i][k] * b[k][j];
                    result[i][j] %= 1000;
                }
            }
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        B = sc.nextLong();
        metrics = new int[N][N];

        int[][] result = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                metrics[i][j] = sc.nextInt();
            }
            result[i][i] = 1;
        }

        while( B > 0){
            if(B % 2 == 1){
                result = culc(result, metrics);
                B--;
            }
            
            metrics = culc(metrics, metrics);
            B /= 2;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
