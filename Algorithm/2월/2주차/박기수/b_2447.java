package ps_java;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class b_2447 {
	static int n;
	static char arr[][];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		initArr();
		func(n, 0, 0);
		print();

		br.close();
		bw.close();
	}
	
	static void initArr() {
		for(int i = 0; i<n; i++) 
			Arrays.fill(arr[i], ' ');
	}
	static void func(int n, int old_i, int old_j) {
		if(n==3) {
			for(int i = 0; i<3; i++) {
				for(int j = 0; j<3; j++) {
					if(i==1&&j==1) continue;
					arr[old_i+i][old_j+j] = '*';
				}
			}
			return;
		}
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if(i==1&&j==1) continue;
				func(n/3, old_i+i*n/3, old_j+j*n/3);
			}
		}
	}
	static void print() throws Exception{
		for(int i = 0; i<n; i++) {
			bw.write(arr[i]);
			bw.write("\n");
		}
	}
}
