package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj18225당구공을넣자 {
	static int A, B, x, y, p, q;
	static int aa, bb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		
		// q(x-x1) - p(y-y1) = 0
		
		// Ax + By = C 꼴로일단 만든다
		// qx - py = qx1 -py1	--------1번식
		// x, y는 미지수
		// x1, y1 이 현재 좌표 이기 때문에  아래와 같이 값 설정
		// 1번식에서 x = An, y = Bm 이 되는 값을 찾는것이 문제 (n, m은 자연수여야 한다)
		// 위 식을 다시 변환하면
		// Aqn - Bpm = qx1 - py1 이 된다
		// Aq, Bq, qx1 - py1이 서로소가 되도록 나눠준다 일단
		int c = q*x - p*y;
		
		int n = q*A;
		int m = p*B;
		
		int g = gcd(n, m);
		
		if(c!=0) {
			g = gcd(g, c);
		}
		int a = n / g;
		int b = m / g;
		int c2 = c / g;
		g = gcd(a, b);
		//System.out.println("g: " + g);
		//System.out.println(a + " " + b + " " + c2);
		// 최종 식
		// ax - by = c2
		// 일 때 정수가 되는 x, y 값을 찾는 것이다.
		
		// c가 0 이면 a,b를 더한 값에 -1 한 것이 답
		// 서로소 관계이기 때문
		if(c==0) {
			int num = a+b-1;
			System.out.println(num);
		}
		
		else if(c2%g==0) {
//			int time = 1;
//			while(time<100000) {
//				if((time*A - x)%p!=0) {
//					time++;
//					continue;
//				}
//				long t = (time*A - x)/p;
//				
//				if((y+q*t) % (2*B) == 0 || (y+q*t) % (2*B) == B ) {
//					long num = (x+p*t)/(A) + (y+q*t)/(B);
//					System.out.println(num-1);
//					return;
//				}
//				
//				time++;
//			}
			if(Math.abs(a)> Math.abs(b)) {				
				func(a, -b);
			}
			else func(-b,a);
			int num = aa+bb-1;
			System.out.println(num);
		}
		
		else {
			System.out.println(-1);
		}
	}

	static int gcd(int a, int b) {
		int tmp, n;
		// a에 큰 값을 위치시키기 위한 조건이다.
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		// b가 0 될때까지(a%b), 반복문을 돌게되고, b가 0인 순간의 a를 GCD로 판단하고 리턴한다
		while (b != 0) {
			n = a % b;
			a = b;
			b = n;
		}
		return a;
	}
	
	static void func(int a, int b){
		ArrayList<Integer> r = new ArrayList<Integer>();
		r.add(a);
		r.add(b);
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<Integer> t = new ArrayList<Integer>();
		s.add(1);
		s.add(0);
		t.add(0);
		t.add(1);
		while(r.get(r.size()-1) != 0) {
			System.out.println(r);
			int q = r.get(r.size()-2) / r.get(r.size()-1);
			r.add(r.get(r.size()-2) - q * r.get(r.size()-1));
			s.add(s.get(s.size()-2) - q * s.get(s.size()-1));
			t.add(t.get(t.size()-2) - q * t.get(t.size()-1));
		}
		aa = s.get(s.size()-2);
		bb = t.get(t.size()-2);
	}
	
}
