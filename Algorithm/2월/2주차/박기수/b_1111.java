package ps_java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b_1111 {
	static int N, a, b, arr[], cnt, nextNum;
	static final int MAX = 1000000;
	static boolean[] visited = new boolean[2*MAX+1];
	public static void main(String[] args) throws Exception {
		input();
		func();
		print();
	}
	static void func() {
		int i = 0;
		for(a = -20000; a<=20000; a++) {
			for(b = -20000; b<=20000; b++) {
				// 개수가 2개 이상이면 더 이상 보지 않음
				if(cnt > 1) return;
				
				// a, b 돌려가면서 규칙 찾아보기
				for(i = 0; i<N-1; i++) {
					if(arr[i]*a+b != arr[i+1])
						break;
				}
				
				// 끝까지 도착 -> a, b 규칙 만족
				if(i==N-1) {
					nextNum = arr[N-1]*a + b;
					if(!visited[nextNum + MAX]) {
						visited[nextNum + MAX] = true;
						cnt++;
					}
				}
			}
		}
	}
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		br.close();
	}
	static void print() {
		if(cnt==0) System.out.println("B");
		else if(cnt==1) System.out.println(nextNum);
		else if(cnt>1) System.out.println("A");
	}
}
