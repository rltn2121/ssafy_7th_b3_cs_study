package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1939중량제한 {
	static int N, M, start, end;
	static int[] parent;
	static ArrayList<int[]>[] pq;
	static int ans;
	// static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pq = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			pq[i] = new ArrayList<int[]>();
		}
		makeSet();
		// isVisited = new boolean[N + 1][N + 1];
		int max = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Union(from, to);
//			if (map[from][to] < weight) {
//				map[from][to] = weight;
//				map[to][from] = weight;
//			}
			max = Math.max(max, weight);
			pq[from].add(new int[] { to, weight });
			pq[to].add(new int[] { from, weight });
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		int min = 1;
		while (min <= max) {
			int mid = (min + max) / 2;
			if (bfs(mid)) {
				min = mid + 1;
				ans = Math.max(ans, mid);
			} else {
				max = mid - 1;
			}
		}
		System.out.println(ans);
	}

	static boolean bfs(int n) {
		boolean[] isVisited = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		while (!q.isEmpty()) {

			int v = q.poll();
			if (v == end) {
				return true;
			}

			for (int i = 0; i < pq[v].size(); i++) {
				if (findSet(pq[v].get(i)[0]) != findSet(end))
					continue;
				if (!isVisited[pq[v].get(i)[0]] && pq[v].get(i)[1] >= n) {
					q.offer(pq[v].get(i)[0]);
					isVisited[pq[v].get(i)[0]] = true;
				}
			}

		}
		return false;
	}

	static void makeSet() {
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = findSet(parent[a]);
	}

	static boolean Union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		if (pa == pb)
			return false;
		parent[pb] = parent[pa];
		return true;
	}
}
