package algo.study.day0208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj17952 {
	static int N;
	static Stack<int[]> stack = new Stack<int[]>();
	static int rst = 0;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// i = 현재 시간
		for(int i = 1; i <= N; i++) {
			String s = br.readLine();
			if(s.charAt(0) != '0') {
				StringTokenizer st = new StringTokenizer(s);
				st.nextToken();
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				stack.push(new int[] {score, time});
			}
			// 스택비어있으면 넘어가기
			if(stack.isEmpty()) continue;
			// 지금 하고 있는 과제가 1분 남았으면 과제 끝내고, 과제 점수 추가
			if(stack.peek()[1] == 1) {
				rst += stack.pop()[0];
			}
			// 그게 아니면 과제 남은 시간 1 감소
			else {
				stack.peek()[1]--;
			}
		}
		System.out.println(rst);
	}

}
