package ps_java;


import java.io.*;
import java.util.*;

public class b_10799 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Stack<Character> s = new Stack<>();
	static String input;
	static char before='0';
	static int sum;
	public static void main(String[] args) throws Exception{
		input = br.readLine();
		int N = input.length();
		for(int i = 0; i<N; i++) {
			char c = input.charAt(i);
			if(c=='(') s.push(c);
			else {
				s.pop();
				if(before=='(')
					sum+=s.size();
				else
					sum++;
			}
			before = c;
		}
		System.out.println(sum);
		br.close();
		bw.close();
	}
}	
