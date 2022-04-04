package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class bj1351무한수열 {
	
	static Map <Long, Long> map = new HashMap<>();
	static long n, p, q;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		p = Long.parseLong(st.nextToken());
		q = Long.parseLong(st.nextToken());
		
		System.out.println(func(n));
	}
	static long func(long n) {
		if(n == 0) return 1;
		if(map.containsKey(n)) return map.get(n);
		long a = n / p;
		long b = n / q;
		map.put(n, func(a) + func(b));
		return map.get(n);
	}

}
