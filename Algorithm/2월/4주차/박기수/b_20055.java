package ps_java;


import java.io.*;
import java.util.*;

public class b_20055 {
	static int N,K,arr[], cnt, ans;
	static boolean robot[];
	
	public static void main(String[] args) throws Exception{
		input();
		while(cnt<K) {
			rotate();
			move();
			ans++;
		}
		System.out.println(ans);
	}
	
	static void rotate() {
		// 벨트 회전시키기
		int temp = arr[2*N-1];
		for(int i = 2*N-1; i>0; i--) 
			arr[i] = arr[i-1];
		arr[0] = temp;
		
		// 벨트 따라 로봇 이동시키기
		for(int i = N-1; i>0; i--) 
			robot[i] = robot[i-1];
		robot[0] = false;
	}
	
	static void move() {
		// 내리는 곳 로봇 제거
		robot[N-1] = false;
		
		// 다음 칸으로 이동 가능하면 옮기기
		for(int i = N-2; i>=0; i--) {
			if(robot[i] == true && robot[i+1] == false && arr[i+1] > 0) {
				if(--arr[i+1] == 0)
					cnt++;
				robot[i+1] = true;
				robot[i] = false;
			}
		}
		
		// 올리는 칸에 올리기
		if(arr[0] > 0) {
			robot[0] = true;
			if(--arr[0] == 0)
				cnt++;
		}
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		robot = new boolean[N];
		arr = new int[N*2];
		for(int i = 0; i<2*N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		br.close();
	}
}	

