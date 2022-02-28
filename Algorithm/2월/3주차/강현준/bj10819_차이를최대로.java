package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj10819_차이를최대로 {
	static int N;
	static int[] A;
	static int[] tgt;

	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		tgt = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		perm(0,0);
		System.out.println(ans);
	}

	static void perm(int tgtIdx, int bit) {
		if (tgtIdx == N) {
			calc();
			return;
		}
		for(int i = 0; i < N; i++) {
			if((bit & 1 << i) != 0) continue;
			tgt[tgtIdx] = A[i];
			perm(tgtIdx+1, bit | 1 << i);
		}

	}

	static void calc() {
		int count = 0;
		for (int i = 0; i < N - 1; i++) {
			count += Math.abs(tgt[i] - tgt[i + 1]);
		}

		ans = Math.max(ans, count);
	}
}
