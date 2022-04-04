package study0304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14615DefendTheCTP {

	static int N, M;
	static int[] arr;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();

	static boolean flag;
	static boolean[] isVisited, isVisited2;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list2.get(b).add(a);
		}
		isVisited = new boolean[N + 1];
		isVisited2 = new boolean[N + 1];
		dfs(1);
		dfs2(N);
		isVisited[1] = true;
		isVisited2[N] = true;
		//System.out.println(Arrays.toString(isVisited));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			int tt = Integer.parseInt(br.readLine());
//			flag = false;
//			Arrays.fill(isVisited, false);
//			dfs(1, tt);
//			if (!flag)
//				continue;
//			flag = false;
//			Arrays.fill(isVisited, false);
//			dfs(tt, N);
			if (isVisited[tt] && isVisited2[tt])
				sb.append("Defend the CTP");
			else
				sb.append("Destroyed the CTP");
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

//	static void dfs(int cur, int tgt) {
//		if (cur == tgt) {
//			flag = true;
//			return;
//		}
//		if (flag)
//			return;
//
//		for (int next : list.get(cur)) {
//			if (isVisited[next])
//				continue;
//			isVisited[next] = true;
//			dfs(next, tgt);
//		}
//	}

	static void dfs(int cur) {

		for (int next : list.get(cur)) {
			if (isVisited[next])
				continue;
			isVisited[next] = true;
			dfs(next);
		}
	}

	static void dfs2(int cur) {

		for (int next : list2.get(cur)) {
			if (isVisited2[next])
				continue;
			isVisited2[next] = true;
			dfs2(next);
		}
	}
}
