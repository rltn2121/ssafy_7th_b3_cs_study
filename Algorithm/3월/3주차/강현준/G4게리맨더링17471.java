package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4게리맨더링17471 {
	static int N;
	static int[] arr;
	static ArrayList<Integer> list[];
	static int ans = Integer.MAX_VALUE;
	static int aSum, bSum;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		list = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j = 0; j < n; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		ArrayList<Integer> aList = new ArrayList<Integer>();
		ArrayList<Integer> bList = new ArrayList<Integer>();
		for(int i = 1; i < (1<<N)-1; i++) {
			aSum = 0;
			bSum = 0;
			aList.clear();
			bList.clear();
			for(int j = 0; j < N; j++) {
				int idx = j + 1;
				if( ((1<<j) & i) != 0 ) {
					aList.add(idx);
				}
				else {
					bList.add(idx);
				}
			}
			
			if(isSet(aList, 1) && isSet(bList, 2)) {
				ans = Math.min(ans, Math.abs(aSum-bSum));	
			}			
			
		}
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
	}

	static boolean isSet(ArrayList<Integer> a, int x) {
		if (a.isEmpty())
			return false;
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] isVisited = new boolean[N + 1];
		q.add(a.get(0));
		while (!q.isEmpty()) {
			int cur = q.poll();
			isVisited[cur] = true;
			for (int i = 0; i < list[cur].size(); i++) {
				int num = list[cur].get(i);
				if (isVisited[num])
					continue;
				boolean isOK = false;
				for(int ii = 0; ii < a.size(); ii++) {
					if(num == a.get(ii)) {
						isOK = true;
						break;
					}
				}
				if(!isOK) continue;
				q.add(num);
			}
		}
		if (x == 1) {
			for (int i = 0; i < a.size(); i++) {
				if (!isVisited[a.get(i)])
					return false;
				aSum += arr[a.get(i)];
			}
		}
		if (x == 2) {
			for (int i = 0; i < a.size(); i++) {
				if (!isVisited[a.get(i)])
					return false;
				bSum += arr[a.get(i)];
			}
		}
		return true;
	}
}
