package ps_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b_1100 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static int N;
	static int M;
	static int cnt = 0;
	static char[][] arr = new char[8][8];
	public static void main(String[] args) throws Exception{
		
		for(int i = 0; i<8; i++)
			arr[i] = br.readLine().toCharArray();
		
		for(int i = 0; i<8; i+=2) {
			for(int j = 0; j<8; j+=2) {
				if(arr[i][j]=='F') cnt++;
			}
		}
		
		for(int i = 1; i<8; i+=2) {
			for(int j = 1; j<8; j+=2) {
				if(arr[i][j]=='F') cnt++;
			}
		}
			
		System.out.println(cnt);
		br.close();
	}
}
