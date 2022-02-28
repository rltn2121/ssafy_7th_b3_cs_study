package ps_java;

import java.io.*;
import java.util.*;

public class b_5904 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, length[] = new int[28];
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		
		// s(k)의 길이 계산
		length[0] = 3;
		for(int i = 1; i<length.length; i++)
			length[i] = length[i-1]*2+(i+3);

		int i = 0;
		while(true) {
			// 기저조건
			if(N<=3) {
				if(N==1)
					System.out.println('m');
				else 
					System.out.println('o');
				break;
			}
			
			// N이 몇 번째 수열에 포함되는지 찾기
			for(i = 0; i<=28; i++) {
				if(N<=length[i])
					break;
			}
			
			// 1) m...ooo 수열에 포함
			if(N>=length[i-1]+1 && N <=length[i-1]+1+(i+2)) {
				if(N==length[i-1]+1) 
					System.out.println('m');
				else 
					System.out.println('o');
				break;
			}
				
			// 2) s(k-1)에 포함
			else 
				N -=(length[i-1]+1+(i+2));
		}
		br.close();
	}
}	
