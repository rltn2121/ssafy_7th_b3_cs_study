package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj12789도키도키간식드리미 {
	
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int num = 1;
		int idx = 0;
		Stack<Integer> stack = new Stack<Integer>();
		while(true) {
			
			
			
			if(idx < N && num == arr[idx]) {
				num++;
				idx++;
				continue;
			}
			while(!stack.isEmpty()) {
				int temp = stack.peek();
				if(num == temp) {
					num++;
					stack.pop();
				}
				else break;
			}
			
			if(idx==N) {
				if(num==N+1) System.out.println("Nice");
				else System.out.println("Sad");
				break;
			}
			
			stack.push(arr[idx]);
			idx++;
			
		}
		
		
	}
}
