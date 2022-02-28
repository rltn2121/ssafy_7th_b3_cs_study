package ps_java;


import java.io.*;
import java.util.*;

public class b_17952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int ans;
	static ArrayDeque<Node> s = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if(st.nextToken().equals("1"))
				s.push(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
			if(!s.isEmpty() && --s.peek().day==0)
				ans+=s.pop().score;
				
		}
		System.out.println(ans);
		br.close();
	}
	static class Node{
		int score;
		int day;
		public Node(int s, int d) {
			this.score = s;
			this.day = d;
		}
	}
}	
