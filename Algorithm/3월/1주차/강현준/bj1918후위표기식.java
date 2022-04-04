package study;

import java.util.Scanner;
import java.util.Stack;

public class bj1918후위표기식 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < arr.length; i++) {

			// 피연산자는 그대로 순서대로 출력한다.
			if (arr[i] >= 'A' && arr[i] <= 'Z')
				sb.append(arr[i]);

			else {
				// 스택이 비어 있으면 연산자 그냥 집어 넣기
				if (stack.isEmpty())
					stack.push(arr[i]);

				// 괄호 입력 받으면 먼저 처리
				// 여는 괄호는 그냥 스택에 넣기
				else if (arr[i] == '(')
					stack.push(arr[i]);
				// 닫는 괄호이면 스택에 있던거 여는 괄호 까지 처리해주기
				else if (arr[i] == ')') {
					while (stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.pop();
				}

				// 아니면 연산자 우선순위를 고려해야한다.
				else {
					// 스택에 있는 것이랑 우선순위가 같거나 빠르면 스택 출력
					if (arr[i] == '*' || arr[i] == '/') {
						while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
							sb.append(stack.pop());
						}
						stack.push(arr[i]);
					}
					else if (arr[i] == '+' || arr[i] == '-') {
						while(!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
						stack.push(arr[i]);
					}
				}
			}
		}
		while(!stack.isEmpty()) sb.append(stack.pop());
		System.out.println(sb);
	}

}
