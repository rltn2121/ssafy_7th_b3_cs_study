package ps_java;
import java.io.*;
import java.util.*;

public class b_1202 {
	static PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
	static Node jewels[];
	static int bags[];
	static int N,K;
	static long ans;
	public static void main(String[] args) throws Exception{
		input();
	
		Arrays.sort(jewels, (n1, n2) -> n1.weight - n2.weight);	// 보석 가벼운 순 정렬
		Arrays.sort(bags);										// 가방 가벼운 순 정렬
		
		int idx = 0;
		for(int bag : bags) {
			// 현재 가방에 넣을 수 있는 보석을 모두 pq에 넣음
			while(idx<N && bag >= jewels[idx].weight)
				pq.add(jewels[idx++].value);
			
			// 가장 비싼 보석을 pq에서 꺼냄
			if(!pq.isEmpty())
				ans+=pq.poll();
		}
		System.out.println(ans);
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jewels = new Node[N];
		bags = new int[K];

		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for(int i = 0; i<K; i++) 
			bags[i] = Integer.parseInt(br.readLine());
		br.close();
	}
	
	static class Node{
		int weight;
		int value;
		public Node(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
}	
