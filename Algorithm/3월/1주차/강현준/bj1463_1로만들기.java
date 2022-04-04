package study;

import java.util.Scanner;

public class bj1463_1로만들기 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] arr = new int[n + 1];

		for (int i = 1; i <= n; i++) {

			if (i == 1)
				arr[i] = 0;
			else if (i <= 3)
				arr[i] = 1;
			else {

				if (i % 6 == 0) {
					arr[i] = Math.min(arr[i / 2] + 1, arr[i / 3] + 1);
					arr[i] = Math.min(arr[i], arr[i - 1] + 1);
				} else if (i % 3 == 0) {
					arr[i] = Math.min(arr[i - 1] + 1, arr[i / 3] + 1);
				} else if (i % 2 == 0) {
					arr[i] = Math.min(arr[i - 1] + 1, arr[i / 2] + 1);
				} else
					arr[i] = arr[i - 1] + 1;

			}

		}

		System.out.println(arr[n]);

	}

}
