package algo.study.day0208;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj15649 {
	static int n, r;
	static int[] arr;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[r];
		isSelected = new boolean[n+1];
		func(0);
	}
	public static void func(int cnt) {
		if(cnt==r) {
			for(int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		for(int i = 1; i <= n; i++) {
			if(isSelected[i]) continue;
			arr[cnt] = i;
			isSelected[i] = true;
			func(cnt+1);
			isSelected[i] = false;
		}
	}

}
