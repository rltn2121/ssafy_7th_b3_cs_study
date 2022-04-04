package 삼성A형기출문제;

import java.util.Arrays;
import java.util.Scanner;

public class G3괄호추가하기_16637 {
	static int N;
	static char[] arr;
	static int[] numIdxOri;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = sc.next().toCharArray();
		numIdxOri = new int[N];
		for (int i = 0; i < N; i += 2) {
			numIdxOri[i] = arr[i] - '0';
		}
		int opNum = N / 2;
		int[] opArr = new int[opNum];
		for (int i = 0; i < opNum; i++) {
			opArr[i] = i;
		}

		for (int i = 0; i < (1 << opNum) - 1; i++) {
			boolean[] isGh = new boolean[opNum];
			int[] numIdx = new int[N];
			for(int ii =0; ii < N; ii++)
				numIdx[ii] = numIdxOri[ii];
			for (int j = 0; j < opNum; j++) {

				if ((i & (1 << j)) != 0) {
					if(j!=0 && isGh[j-1]) continue;
					isGh[j] = true;

					char op = arr[j * 2 + 1];
					int opIdx = j * 2 + 1;
					int num = 0;
					if (op == '+') {
						num = numIdx[opIdx - 1] + numIdx[opIdx + 1];
					} else if (op == '-') {
						num = numIdx[opIdx - 1] - numIdx[opIdx + 1];
					} else if (op == '*') {
						num = numIdx[opIdx - 1] * numIdx[opIdx + 1];
					}
					numIdx[opIdx - 1] = num;
					numIdx[opIdx + 1] = num;
				}
			}
			int rst = numIdx[0];
			for(int j = 0; j < opNum; j++) {
				if(!isGh[j]) {
					char op = arr[j * 2 + 1];
					int opIdx = j * 2 + 1;
					if (op == '+') {
						rst += numIdx[opIdx + 1];
					} else if (op == '-') {
						rst -= numIdx[opIdx + 1];
					} else if (op == '*') {
						rst *= numIdx[opIdx + 1];
					}
				}
			}
//			if(rst==50400) {
//				System.out.println(Arrays.toString(numIdx));
//				System.out.println(Arrays.toString(isGh));
//				System.out.println("=================================");
//			}
			max = Math.max(rst, max);
		}
		if(N==1) {
			max = numIdxOri[0];
		}
		System.out.println(max);
	}

}
