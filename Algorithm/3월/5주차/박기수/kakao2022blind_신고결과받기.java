import java.util.*;

public class kakao2022blind_신고결과받기 {
    public static int[] solution(String[] id_list, String[] report, int k) {
    	int len = id_list.length;
    	int reported_cnt[] = new int[len];
    	int visited[][] = new int[len][len];
    	int answer[] = new int[len];
    	int idx = 0;
    	// 1. 이름에 번호 부여
    	Map<String, Integer> map = new HashMap<>();

    	for(String str : id_list) {
    		map.put(str, idx++);
    	}
    	// 2. 신고 접수
    	
    	for(String str : report) {
    		StringTokenizer st = new StringTokenizer(str);
    		String from = st.nextToken();
    		String to = st.nextToken();
    		
    		int from_int = map.get(from);
    		int to_int = map.get(to);
    		
    		visited[from_int][to_int] = 1;
    	}
    	// 3. 숫자 세기
    	for(int j = 0; j<len; j++) {
    		for(int i = 0; i<len; i++) {
       			reported_cnt[j] += visited[i][j];
       		}
       	}
    	
        for(int i = 0; i<len; i++) {
    		for(int j = 0; j<len; j++) {
    			if(visited[i][j] == 1 && reported_cnt[j] >= k)
    				answer[i]++;
    		}
    		
    	}
        
        return answer;
    }
    
    public static void main(String[] args) {
    	String[] id_list = new String[] {"muzi", "frodo", "apeach", "neo"};
    	String[] report = new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
    	int k = 2;
		int[] ans = solution(id_list, report, k);
		
		for(int i : ans)
			System.out.println(i);
		
	}
}
