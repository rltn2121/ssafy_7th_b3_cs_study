package algo.study.day0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1111 {
	static int N;
	static int[] arr;
	static double a, b;

	public static void main(String[] args) throws Exception {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// N이 1이면 다음에 어떤 수가 와도 괜춘
		if (N == 1)
			System.out.println('A');
		// N이 2일떄
		else if (N == 2) {
			// 두 숫자가 같으면 다음 숫자도 같은 숫자가 와야됨
			if (arr[0] == arr[1])
				System.out.println(arr[0]);
			// 두 숫자가 다르면 다음에 어떤 숫자가 와도 괜춘
			else
				System.out.println('A');
		
		// 입력이 3개 이상일 때
		} else {
			// 처음 두 숫자가 같을 때
			if (arr[0] - arr[1] == 0) {
				
				// 리스트 순회
				for (int num : arr) {
					// 하나라도 다른게 있으면 B 출력 후 종료
					if (num != arr[0]) {
						System.out.println('B');
						return;
					}
				}
				// 모두다 같으면 처음 숫자 그대로 출력 후 종료
				System.out.println(arr[0]);
				return;
			} else // a와 b 값 설정
				a = (double) ((arr[1] - arr[2]) / (arr[0] - arr[1]));
			a = Math.round(a * 10) / 10;
			b = arr[1] - a * arr[0];
			b = Math.round(b * 10) / 10;
			// 정수 아니면 B
			if (a != (int) a || b != (int) b)
				System.out.println('B');
			// 두 수가 정수이면
			else {
				int A = (int) a;
				int B = (int) b;
				// a, b를 이용한 관계가 다음과도 같은 것 체크해주기
				boolean isCollect = true;
				for (int i = 0; i < arr.length - 1; i++) {
					// 문제 조건과 다르면 B 출력
					if (A * arr[i] + B != arr[i + 1]) {
						isCollect = false;
						System.out.println('B');
						break;
					}
				}
				// 문제 조건 맞으면 A 출력
				if (isCollect)
					System.out.println(A * arr[N - 1] + B);

			}
		}

	}
}
