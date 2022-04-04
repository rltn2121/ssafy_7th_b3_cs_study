package study0305;

import java.util.Arrays;
import java.util.HashMap;

public class test {
	public static void main(String[] args) {
//		Position p1 = new Position(10, 20);
//		Position[] list = new Position[10];
//		for(int i = 0; i < 10; i++) {
//			list[i] = p1;
//		}
//		Position p2 = list[5];
//		System.out.println(p1 == p2);
//		System.out.println(list[5] == list[1]);
		int[][][][] x = new int[3][3][3][3];
		int idx = 0;
		for(int i = 0; i < 3; i++) {
			for(int ii = 0; ii < 3; ii++) {
				for(int iii = 0; iii < 3; iii++) {
					for(int iiii = 0; iiii < 3; iiii++) {
						x[i][ii][iii][iiii] = idx++;
					}
				}
			}
		}
		System.out.println(Arrays.toString(x[2][2][2]));
	}
		
	
	
	static class Position {
		int y, x;
		String num = "0";
		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "["+y+","+x+"]";
		}
		
	}
}
