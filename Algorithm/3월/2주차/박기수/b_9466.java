package ps_java;
import java.io.*;
import java.util.*;

public class b_9466 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N,T,next[],status[],ans;
	static final int SENT = 1;
	static final int FINISHED = 2;
	static Stack<Integer> s = new Stack<>();
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			input();
			
			// 모든 사람 탐색
			for(int i = 1; i<=N; i++) {
				if(status[i] != 0) continue;
					dfs(i);
			}
			sb.append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int now) {
		// 1. 지목한 사람이 이미 결정된 상태면
		if(status[next[now]] == FINISHED) {
			status[now] = FINISHED;
			return;
		}
		
		// 2. 다른 사람을 지목한 상태
		status[now] = SENT;
		s.add(now);
		
		// 3. 사이클 발견 (팀 결정됨)
		if(status[next[now]] == SENT) {
			while(!s.isEmpty() && s.peek() != next[now]) {
				status[s.pop()] = FINISHED;
				ans--;
			}
			status[s.pop()] = FINISHED;
			ans--;
			return;
		}
		 
        // 4. 내가 선택한 사람을 기준으로 탐색 시작
		dfs(next[now]);
		
		// 5. 탐색 종료. 더 이상 탐색 안해도 됨
		status[now] = FINISHED;
	}
	
	static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		next = new int[N+1];
		status = new int[N+1];
		ans = N;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) 
			next[i] = Integer.parseInt(st.nextToken());
	}
}	
