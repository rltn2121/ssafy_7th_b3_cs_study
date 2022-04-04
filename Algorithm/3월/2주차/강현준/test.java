package study;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(-3,6));
	}
	private static int gcd(int a, int b) {
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
}
