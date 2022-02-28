package algo.study.day0209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj11047 {
	static int N, K;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// N개 줄에 동전 입력
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(func());
	}
	public static int func() {
		int rst = 0;	// 반환값
		int value = 0;	// 현재 저장된 값
		// 가장 큰 가치부터 탐색하기
		int idx = N-1;
		while(true) {
			if(value == K) break;
			// 루프 돌기 시작하면 동전을 사용한 것이므로 1추가
			rst++;
			
			// 채워야할 금액보다 현재 동전의 가치보다 크면 다음 동전으로
			while(arr[idx] > K - value) {
				idx--;
			}
			value += arr[idx];
			

		}
		
		
		
		
		
		return rst;
	}
}
