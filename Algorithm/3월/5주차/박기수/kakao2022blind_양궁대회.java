
public class kakao2022blind_양궁대회 {
	// 1. k점을 어피치가 더 많이 맞추거나 같게 맞춤 => 어피치가 k점 획득
	// 2. 둘 다 안맞췄으면 0점
	// 3. 최종 점수가 동점이면 어피치 승
	// 4. 라이언이 점수차 제일 크게 이겨야 함
	static class Solution {
		static int max_score = -1;
		static int answer[] = new int[11];

		static int SHOOTING_CNT;
		static int SHOOTING_RESULT[];

		static int lion_score;
		static int apeach_score;

		static int lion[];
		static int apeach[];

		public int[] solution(int n, int[] info) {
			apeach = info.clone();
			SHOOTING_CNT = n;
			SHOOTING_RESULT = new int[SHOOTING_CNT];
			perm(0, 0);

			// 라이언이 이길 수 없으면
			if (max_score == -1)
				return new int[] { -1 };
			return answer;
		}

		public void perm(int cnt, int idx) {
			// 점수 조합 다 만들었으면
			if (cnt == SHOOTING_CNT) {
				// 점수 계산하기
				// 라이언 점수 배열 계산
				lion = new int[11];
				for (int score : SHOOTING_RESULT)
					lion[10 - score]++;

				if (isLionWin(lion, apeach)) 
					update();
				
				return;
			}

			// 점수 조합 만들기
			for (int i = idx; i <= 10; i++) {
				SHOOTING_RESULT[cnt] = i;
				perm(cnt + 1, i);
				SHOOTING_RESULT[cnt] = 0;
			}
//	            // 점수 조합 만들기
//	            for(int i = idx; i<=10; i++) {
//	                lion[i]++;
//	                perm(cnt+1, i, apeach);
//	                lion[i]--;
//	            }
		}

		private boolean isLionWin(int[] lion, int[] apeach) {
			lion_score = 0;
			apeach_score = 0;

			// 점수 계산
			for (int k = 0; k <= 10; k++) {
				if (lion[k] == 0 && apeach[k] == 0)
					continue; // 둘 다 못맞췄으면 0점
				if (apeach[k] >= lion[k])
					apeach_score += (10 - k); // 어피치가 더 많이 맞추거나 같으면 어피치 점수
				else
					lion_score += (10 - k); // 라이언이 더 많이 맞추면 라이언 점수
			}

			return lion_score > apeach_score;
		}
		
		private void update() {
			int diff = lion_score - apeach_score;
			if (diff > max_score) {
				max_score = diff;
				for (int i = 0; i <= 10; i++)
					answer[i] = lion[i];
			}

			// 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우 return
			else if (diff == max_score) {
				boolean update = false;

				int score = 10;
				while (score >= 0) {
					if (lion[score] < answer[score])
						break;

					if (lion[score] > answer[score]) {
						update = true;
						break;
					}
					score--;
				}

				if (update) {
					for (int i = 0; i <= 10; i++)
						answer[i] = lion[i];
				}
			}

		}

	}
	
	

	public static void main(String[] args) {
		int n[] = { 5, 1, 9, 10, 1, 2 };
		int info[][] = { { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 } };
		Solution s = new Solution();
		int ans[] = s.solution(n[0], info[0]);
		for (int i : ans) {
			System.out.print(i + " ");
		}
	}
}
