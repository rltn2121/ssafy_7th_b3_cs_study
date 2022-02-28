package algo.study.day0208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2960 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int [] arr = new int[N+1];
		int count = 0;
		int idx = (int)Math.sqrt(N) + 1;
		outer: for(int i = 2; i<= N; i++) {
			int ii = i;
			while(true) {
				if(ii > N) break;
				if(arr[ii] == 0) {
					count++;
					arr[ii] = 1;
					System.out.println(count+"번째 지워진 수: "+ii);
				}
				if(count == k) {
					System.out.println(ii);
					break outer;
				}
				ii += i;
				
			}
		}
		
	}

}
