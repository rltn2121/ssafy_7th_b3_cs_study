package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class bj4195_친구네트워크 {

	static int T, F;
	static HashMap<String, String> map = new HashMap<String, String>();
	static HashMap<String, Integer> num = new HashMap<String, Integer>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			F = Integer.parseInt(br.readLine());
			ArrayList<String> list = new ArrayList<String>();
			map.clear();
			num.clear();
			for (int i = 0; i < F; i++) {
				String str = br.readLine();
				list.add(str);
				StringTokenizer st = new StringTokenizer(str);
				String s = st.nextToken();
				if (!map.containsKey(s)) {
					map.put(s, s);
					num.put(s, 1);
				}

				s = st.nextToken();
				if (!map.containsKey(s)) {
					map.put(s, s);
					num.put(s, 1);
				}

			}

			for (String str : list) {
				StringTokenizer st = new StringTokenizer(str);
				String str1 = st.nextToken();
				String str2 = st.nextToken();
				sb.append(union(str1, str2));
				sb.append("\n");
			}
			// System.out.println("==================");
//			for(String str : map.keySet()) {
//				System.out.println(str + " / " + map.get(str));
//			}

		}
		System.out.println(sb.toString());
	}

	static String find(String str) {
		// System.out.println(str + " " + map.get(str));

		// System.out.println("find 중입니다" + str + " / " + map.get(str));
		if (map.get(str).equals(str)) {
			// System.out.println("find 끝" + str);
			return str;
		}
		
		String temp = find(map.get(str));
		map.replace(str, temp);
		return find(map.get(str));
		//return map.replace(str, find(map.get(str)));
	}
//	static int union(String str1, String str2) {
//		System.out.println("합치기 find 전 : " + str1 + " / " + str2);
//		String str1New = find(str1);
//		System.out.println("-------------------");
//		String str2New = find(str2);
//		System.out.println("합치기 find 후 : " + str1 + " / " + str2);
//		if(str1New.equals(str2New)) return num.get(str1New);
//		System.out.println("value : " + num.get(str1) + " / " + num.get(str2));
//		int value = num.get(str1New) + num.get(str2New);
//		num.replace(str1New, value);
//		num.replace(str2New, value);
//		if(str1New.compareTo(str2New) < 0) {
//			map.replace(str1New, str2New);
//		}
//		else map.replace(str2New, str1New);
//		System.out.println("합치기 후 : " + find(str1New) + " / " + find(str2New));
//		return value;
//	}

	static int union(String str1, String str2) {
		// System.out.println("합치기 find 전 : " + str1 + " / " + str2);
		str1 = find(find(str1));
		// System.out.println("-------------------");
		str2 = find(find(str2));
		// System.out.println("합치기 find 후 : " + str1 + " / " + find(str2));
		if (str1.equals(str2))
			return num.get(str1);
		// System.out.println("value : " + num.get(str1) + " / " + num.get(str2));
		int value = num.get(str1) + num.get(str2);
		num.replace(str1, value);
		num.replace(str2, value);
		if (str1.compareTo(str2) < 0) {
			map.replace(find(str1), find(str2));
		} else
			map.replace(find(str2), find(str1));
		// System.out.println("합치기 후 : " + find(str1) + " / " + find(str2));
		return value;
	}
}
