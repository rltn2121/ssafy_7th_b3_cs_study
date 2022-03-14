package ps_java;


import java.io.*;
import java.util.Stack;

public class b_16637 {
	static int N, NUM_CNT, ans = Integer.MIN_VALUE;;
	static char input[];
	static boolean visited[];
	
	public static void main(String[] args) throws Exception{
		input();
		subset(0,0);
		System.out.println(ans);
	}
	
	static void subset(int idx, int cnt) {
		// 괄호 칠 위치 결정됐으면
		if(cnt == NUM_CNT) {
			// 마지막 숫자는 괄호 칠 수 없음
			if(visited[cnt-1]) return;
			
			ans = Math.max(ans, func());
			return;
		}
		
		// 괄호는 겹칠 수 없음. 이전 숫자에서 괄호를 쳤으면 현재 숫자는 괄호 칠 수 없음.
		// ex) (7 - (9) * 2)
		if(idx == 0 || !visited[idx-1]) {
			visited[idx] = true;	
			subset(idx+1, cnt+1);
		}
		visited[idx] = false;
		subset(idx+1, cnt+1);
	}
	
	static int func() {
		Stack<Integer> s = new Stack<>();
		int idx = 0;
		int a = 0;
		int b = 0;
		char op = '\0';
		
		while(idx<N) {
			// 1. 숫자이면
			if(input[idx] >= '0' && input[idx] <= '9') {
				// 1.1. 괄호 치면 -> 계산하고 스택에 삽입
				if(visited[idx/2]) {
					a = input[idx++]-'0';
					char temp_op = input[idx++];
					b = input[idx]-'0';
					s.add(calc(a,b,temp_op));
				}
				
				// 1.2. 괄호 안치면 -> 바로 스택에 삽입
				else
					s.add(input[idx]-'0');
			}
			// 2. 연산자이면
			else
				op = input[idx];
			
			// 계산해야 하면
			if(s.size() == 2) {
				b = s.pop();
				a = s.pop();
				s.add(calc(a,b,op));
			}
			idx++;
		}
		return s.pop();
	}

	static int calc(int a, int b, char op) {
		if(op == '+')		return a+b;
		else if(op == '-')	return a-b;
		else if(op == '*')	return a*b;
		else if(op == '/')	return a/b;
		return 0;
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = br.readLine().toCharArray();
		
		NUM_CNT = N/2+1;
		visited = new boolean[NUM_CNT];
	}
}	
