	package ps_java;
	
	
	import java.io.*;
	import java.util.*;
	
	public class s_4012 {
		static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		static StringBuilder sb = new StringBuilder();
		static StringTokenizer st;
		static int N,M,T,ans;
		static int arr[][];
		static boolean visited[];
		public static void main(String[] args) throws Exception{
			T = Integer.parseInt(br.readLine());
			for(int tc = 1; tc<=T; tc++) {
				sb.append("#").append(tc).append(" ");
				input();
				func();
				sb.append(ans).append("\n");
			}
			System.out.println(sb.toString());
		}
		
		static void func() {
			// 비트마스킹을 이용하여 팀 나누기 (모든 경우 탐색)
			int MAX = (1<<N);
			for(int mask = 0; mask<MAX; mask++) {
				int list1[] = new int[N];
				int list2[] = new int[N];
				int list1_idx = 0;
				int list2_idx = 0;
				
				// 중복 탐색을 방지하기 위해 선택된 경우의 수 저장
				// [0,1], [2,3] == [2,3], [0,1]
				int status1 = 0;
				int status2 = 0;
				for(int i = 0; i<N; i++) {
					if((mask & (1<<i)) != 0) {
						list1[list1_idx++] = i;
						status1+=(1<<i);
					}
					else {
						list2[list2_idx++] = i;
						status2+=(1<<i);
					}
				}
				
				// 중복 확인 체크
				if(visited[status1] || visited[status2]) continue;
				visited[status1] = true;
				visited[status2] = true;
				
				// 1팀 계산
				int team1 = 0;
				for(int i = 0; i<list1_idx; i++) {
					for(int j = 0; j<list1_idx; j++)
						team1+=arr[list1[i]][list1[j]];
				}
				
				// 2팀 계산
				int team2 = 0;
				for(int i = 0; i<list2_idx; i++) {
					for(int j = 0; j<list2_idx; j++)
						team2+=arr[list2[i]][list2[j]];
				}
				ans = Math.min(ans, Math.abs(team1-team2));
			}
		}
	
		static void input() throws Exception {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[1<<N];
			ans = 99999999;
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
	}	
	
