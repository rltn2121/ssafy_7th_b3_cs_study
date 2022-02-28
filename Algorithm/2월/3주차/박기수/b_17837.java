package ps_java;

import java.io.*;
import java.util.*;

// 말은 1~K번깢 ㅣ순서대로 이동
// 이동 시, 위에 있는 말도 함께 이동
// 4개 쌓이면 즉시 종료

// 다음칸 색에 따른 이동
// 흰색칸(0): 이동, 쌓아 올림  (A B C / D E -> D E A B C)
// 빨간색(1): 이동, 순서 반대로 (A D F G / E C B -> E C B / G F D A)
// 파란색(2): 방향 반대로 하고 이동. 반대로 한 칸도 파란색이면 가만히 있음
// * 범위 벗어나는 경우에는 파란색과 같이 이동
public class b_17837 {
	static int N,M,arr[][];	// 0: 흰색, 1: 빨간색, 2: 파란색
	static Node nodes[];
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static Stack<Integer>[][] map;
	public static void main(String[] args) throws Exception{
		input();
		int turn = 1;
		// 턴 1000번 진행
		while(turn <= 1000) {
			// 말 1번 ~ K번 이동시키기
			for(int i = 1; i<=M; i++) {
				// i번째 말 이동시키기
				move(i);
				
				// 게임 종료 조건 확인
				if(checkGameOver()) {
					System.out.println(turn);
					return;
				}		
						
			}
				
			turn++;
		}
		System.out.println(-1);
	}
	
	
	static void moveNode(Node now, int nx, int ny) {
		Stack<Integer> currentStack = map[now.x][now.y];
		Stack<Integer> temp_stack = new Stack<>();
		
		// 1. 말 상태 변경
		while(currentStack.peek() != now.idx) {
			Node temp = nodes[currentStack.pop()];
			temp.x = nx;
			temp.y = ny;
			temp_stack.add(temp.idx);
		}
		Node temp = nodes[currentStack.pop()];
		temp.x = nx;
		temp.y = ny;
		temp_stack.add(temp.idx);
		
		// 2. 맵 상태 변경
		Stack<Integer> nextStack = map[nx][ny];
		while(!temp_stack.isEmpty()) 
			nextStack.add(temp_stack.pop());
	}
	
	static void moveNodeAndReverse(Node now, int nx, int ny) {
		Stack<Integer> currentStack = map[now.x][now.y];
		Queue<Integer> temp_queue = new ArrayDeque<>();
		
		// 1. 말 상태 변경
		while(currentStack.peek() != now.idx) {
			Node temp = nodes[currentStack.pop()];
			temp.x = nx;
			temp.y = ny;
			temp_queue.add(temp.idx);
		}
		Node temp = nodes[currentStack.pop()];
		temp.x = nx;
		temp.y = ny;
		temp_queue.add(temp.idx);
		
		// 2. 맵 상태 변경
		Stack<Integer> nextStack = map[nx][ny];
		while(!temp_queue.isEmpty()) 
			nextStack.add(temp_queue.poll());
	}
	
	static void move(int idx) {
		Node now = nodes[idx];
		int nx = now.x+dx[now.direction];
		int ny = now.y+dy[now.direction];
		
//		2. 범위 벗어나면 파란색 칸 취급
//		파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다. 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
		if(nx<0||nx>=N||ny<0||ny>=N || arr[nx][ny] == 2) {
			// 방향 전환
			if(now.direction == 1) now.direction = 2;
			else if(now.direction == 2) now.direction = 1;
			else if(now.direction == 3) now.direction = 4;
			else if(now.direction == 4) now.direction = 3;
			
			nx = now.x+dx[now.direction];
			ny = now.y+dy[now.direction];
			
			if(nx<0||nx>=N||ny<0||ny>=N || arr[nx][ny] == 2)  return;
			else if(arr[nx][ny] == 0)
				moveNode(now, nx, ny);
			else if(arr[nx][ny]==1)
				moveNodeAndReverse(now, nx, ny);
		}
		
//		0. 하얀색 칸
//		흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
//		A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
//		예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
		else if(arr[nx][ny]==0) {
			moveNode(now, nx, ny);
		}
		
//		1. 빨간색 칸
//		빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
//		A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
//		A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
		else if(arr[nx][ny]==1) {
			moveNodeAndReverse(now, nx, ny);
		}
		
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		nodes = new Node[M+1];
		map = new Stack[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++)
				map[i][j] = new Stack<>();
		}
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(i,x,y,dir);
			map[x][y].push(i);
		}
		
		br.close();
	}
	static boolean checkGameOver() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) 
				if(map[i][j].size() >= 4) return true; 
		}
		return false;
	}
	static class Node{
		int idx;
		int x;
		int y;
		int direction;	// 1: 동, 2: 서, 3: 북, 4: 남
		public Node(int idx, int x, int y, int direction) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
}
