package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj3425_고스택 {

	static Stack<Long> s = new Stack<Long>();
	static ArrayList<String> list = new ArrayList<String>();
	
	static boolean isError;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean endCheck = false;
		int zz = 1;
		while(true) {

			String str = br.readLine();
			//System.out.println(str);
			if(str.equals("")) {
				System.out.println("");
				continue;
			}
			if(str.equals("QUIT")) {
				return;
			}
			if(str.equals("END")) {
				endCheck = true;
			}
			list.add(str);
			if(endCheck) {
				endCheck = false;
				int t = Integer.parseInt(br.readLine());
				for(int i = 0; i < t; i++) {
					s.clear();
					isError = false;
					long aa = Long.parseLong(br.readLine());
					s.add(aa);
					for(String com : list) {
						command(com);
						if(isError) break;
					}
					
					if(isError || s.size() != 1) System.out.println("ERROR");
					else {
						Long rst = s.pop();
						if(rst > 1000000000 || rst < -1000000000) System.out.println("ERROR");
						else System.out.println(rst);
					}
					
				}
				list.clear();
			}
			
		}
		
	}

	static void command(String str) {
		StringTokenizer st = new StringTokenizer(str);
		String ss = st.nextToken();
		Long num = -1L;
		if(ss.equals("NUM")) {
			num = Long.parseLong(st.nextToken());
		}
		long temp = 0;
		long temp2 = 0;
		switch (ss) {
		case "NUM":
			//System.out.println(num);
			s.push(num);
			break;
		case "POP":
			if(s.size() < 1) {
				isError = true;
				break;
			}
			s.pop();
			break;
		case "INV":
			if(s.size() < 1) {
				isError = true;
				break;
			}
			temp = s.pop();
			s.push(-temp);
			break;
		case "DUP":
			if(s.size() < 1) {
				isError = true;
				break;
			}
			s.push(s.peek());
			break;
		case "SWP":
			if(s.size() < 2) {
				isError = true;
				break;
			}
			temp = s.pop();
			temp2 = s.pop();
			s.push(temp);
			s.push(temp2);
			break;
		case "ADD":
			if(s.size() < 2) {
				isError = true;
				break;
			}
			temp = s.pop();
			temp2 = s.pop();
			s.push(temp+temp2);
			break;
		case "SUB":
			if(s.size() < 2) {
				isError = true;
				break;
			}
			temp = s.pop();
			temp2 = s.pop();
			s.push(temp2-temp);
			break;
		case "MUL":
			if(s.size() < 2) {
				isError = true;
				break;
			}
			temp = s.pop();
			temp2 = s.pop();
			s.push(temp2*temp);
			break;
		case "DIV":
			if(s.size() < 2) {
				isError = true;
				break;
			}
			temp = s.pop();
			temp2 = s.pop();
			if(temp == 0) {
				isError = true;
				break;
			}
			s.push(temp2/temp);
			break;
		case "MOD":
			if(s.size() < 2) {
				isError = true;
				break;
			}
			temp = s.pop();
			if(temp == 0) {
				isError = true;
				break;
			}
			temp2 = s.pop();
			s.push(temp2%temp);
			break;

		default:
			break;
		}

	}

}
