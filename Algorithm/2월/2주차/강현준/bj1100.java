package algo.study.day0208;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class bj1100 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int rst = 0;
		for(int i = 0; i<8; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j<8; j++) {
				if(i%2==0 && j%2 ==0 && line[j] == 'F') rst+=1;
				if(i%2==1 && j%2 ==1 && line[j] == 'F') rst+=1;
			}
		}
		System.out.println(rst);
	}

}
