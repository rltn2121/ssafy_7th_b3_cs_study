package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14891톱니바퀴 {

	static char[][] wheel = new char[5][8];

	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel[1] = br.readLine().toCharArray();
		wheel[2] = br.readLine().toCharArray();
		wheel[3] = br.readLine().toCharArray();
		wheel[4] = br.readLine().toCharArray();

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			move(num, dir, 0);
//			for(int j = 1; j<=4; j++) {
//				System.out.println(Arrays.toString(wheel[j]));
//			}
		}
		int rst = 0;
		if(wheel[1][0] == '1') rst+=1;
		if(wheel[2][0] == '1') rst+=2;
		if(wheel[3][0] == '1') rst+=4;
		if(wheel[4][0] == '1') rst+=8;
		System.out.println(rst);
	}

	static void move(int num, int dir, int a) {
		// a는 어디로 가는지 보여준다
		// 0번은 양방향
		// -1번은 왼쪽 , 1번은 오른쪽

		if (a == 0) {

			int left = wheel[num][6];
			int right = wheel[num][2];
			// 본인 바퀴 회전
			//System.out.println("회전 전 " + dir + " " + num + " " + Arrays.toString(wheel[num]));
			rotate(wheel[num], dir);
			//System.out.println("회전 후 " + dir + " " + num + " " + Arrays.toString(wheel[num]));
			// 양쪽 탐색
			for (int i = -1; i <= 1; i += 2) {
				int nNum = num + i;
				// 1~4번이 아니면 컨티뉴
				if (nNum < 1 || nNum > 4)
					continue;
				// 왼쪽 바퀴 움직이면 left와 비교 (left = 왼쪽바퀴의 오른쪽이 다를때 움직임)
				if (i == -1 && left != wheel[nNum][2]) {
					// 반대방향으로 회전
					move(nNum, -dir, -1);
				}
				// 오른쪽은 반대로
				if (i == 1 && right != wheel[nNum][6]) {
					// 반대방향으로 회전
					move(nNum, -dir, 1);
				}
			}

		}

		if (a == -1) {
			int left = wheel[num][6];
			// 본인 바퀴 회전
			rotate(wheel[num], dir);
			// 왼쪽만 탐색
			for (int i = -1; i <= -1; i += 2) {
				int nNum = num + i;
				// 1~4번이 아니면 컨티뉴
				if (nNum < 1 || nNum > 4)
					continue;
				// 왼쪽 바퀴 움직이면 left와 비교 (left = 왼쪽바퀴의 오른쪽이 다를때 움직임)
				if (i == -1 && left != wheel[nNum][2]) {
					// 반대방향으로 회전
					move(nNum, -dir, -1);
				}
			}
		}

		if (a == 1) {
			int right = wheel[num][2];
			// 본인 바퀴 회전
			rotate(wheel[num], dir);
			// 양쪽 탐색
			for (int i = 1; i <= 1; i += 2) {
				int nNum = num + i;
				// 1~4번이 아니면 컨티뉴
				if (nNum < 1 || nNum > 4)
					continue;
				// 오른쪽은 반대로
				if (i == 1 && right != wheel[nNum][6]) {
					// 반대방향으로 회전
					move(nNum, -dir, 1);
				}
			}
		}

	}

	static void rotate(char[] arr, int dir) {
		// 시계방향
		if (dir == 1) {
			char temp = arr[7];
			for (int i = 7; i >= 1; i--) {
				arr[i] = arr[i - 1];
			}
			arr[0] = temp;
		}
		// 반시계방향
		if (dir == -1) {
			char temp = arr[0];
			for (int i = 0; i < 7; i++) {
				arr[i] = arr[i + 1];
			}
			arr[7] = temp;
		}
	}

}
