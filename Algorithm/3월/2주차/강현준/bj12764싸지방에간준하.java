package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj12764싸지방에간준하 {

	static int N;
	static int[][] pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq[i][0] = start;
			pq[i][1] = end;
		}
		Arrays.sort(pq, (e1, e2) -> e1[0] - e2[0]);
		// 최소개수 구하기
		PriorityQueue<computer> list = new PriorityQueue<>((c1, c2) -> c1.endTime - c2.endTime);
		PriorityQueue<Integer> indexList = new PriorityQueue<Integer>();
		int[] rst = new int[100001];
		int index = 1;
		for(int i = 0; i < N; i++) {
			int start = pq[i][0];
			int end = pq[i][1];
			boolean add = false;

			
			while (!list.isEmpty()) {
				if (list.peek().endTime > start)
					break;
				// 새로 들어온 시작시간보다 값이 적은 종료 시간 정리하기
				indexList.add(list.poll().index);
			}
			
			if(indexList.isEmpty()) {
				list.offer(new computer(end, index));
				rst[index++] = 1; // 새로운 컴퓨터 추가
			}
			else { // 기존 컴퓨터 중 제일 번호 작은 애꺼 증가
				int temp = indexList.poll();
				list.offer(new computer(end, temp));
				rst[temp]++; 
			}
		}
		System.out.println(index-1);
		for(int i = 1; i <= index-1; i++) {
			System.out.print(rst[i]+" ");
		}
	}

	static class computer {
		int endTime;
		int index;

		public computer(int endTime, int index) {
			super();
			this.endTime = endTime;
			this.index = index;
		}

//		@Override
//		public String toString() {
//			return "computer [endTime=" + endTime + ", count=" + count + ", index=" + index + "]";
//		}

	}
}
