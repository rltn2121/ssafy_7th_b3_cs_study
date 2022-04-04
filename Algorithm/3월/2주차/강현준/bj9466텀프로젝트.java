package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj9466텀프로젝트 {
	static int T, n;
	static int[] arr;
	static int teamNum;
	static boolean[] isVisited;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			teamNum = 0;
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[n+1];
			isVisited = new boolean[n+1];
			for(int i = 1; i <= n; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			for(int i = 1; i <= n; i++) {
				if(isVisited[i]) continue;
				list.clear();
				dfs(i, i);
			}
			System.out.println(n-teamNum);
		}
	}
	
	static void dfs(int idx, int originIdx) {
		
//		if(arr[idx] == originIdx) {
//			list.add(idx);
//			teamNum += list.size();
//			for(int num : list) {
//				isVisited[num] = true; 
//			}
//			return;
//		}
		
		
		if(isVisited[idx]) {
		
			for(int i = 0; i < list.size(); i++) {
				if(idx == list.get(i)) {
					teamNum += (list.size()-i);
					//System.out.println(arr[idx] + "  " + (list.size()-i));
					break;
				}
			}
			return;
		}
		isVisited[idx] = true;
		list.add(idx);
		dfs(arr[idx], originIdx);
	}
	
}
