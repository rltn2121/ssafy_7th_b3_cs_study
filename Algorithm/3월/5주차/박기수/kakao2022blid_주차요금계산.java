import java.util.*;

public class kakao2022blid_주차요금계산 {
	static class Solution {
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.car - b.car);
		Map<String, String> m = new HashMap<>();
		Map<String, Integer> car_info = new HashMap<>();
		
		int basic_time;
		int basic_fee;
		int unit_time;
		int unit_fee;
		
	    public int[] solution(int[] fees, String[] records) {
	    	basic_time = fees[0];
	    	basic_fee = fees[1];
	    	unit_time = fees[2];
	    	unit_fee = fees[3];
	    	
	    	// 입출차 내역 점검
	    	for(String str : records) {
	    		StringTokenizer st = new StringTokenizer(str);
	    		String time = st.nextToken();
	    		String car = st.nextToken();
	    		String cmd = st.nextToken();
	    		
	    		// 입차
	    		if("IN".equals(cmd)) 
	    			m.put(car, time);
	    		
	    		// 출차
	    		else if("OUT".equals(cmd)) {
	    			String start = m.get(car);
	    			m.remove(car);
	    			String end = time;
	    			
	    			int t = getTime(start, end);
	    			
	    			// 시간 누적하기
	    			int current_time = 0;
	    			if(car_info.containsKey(car))
	    				current_time = car_info.get(car);
	    			int temp = t + current_time;
	    			car_info.put(car, temp);
	    		}
	    	}
	    	
	    	// 출차 안된 차들 처리
	    	Set<String> left = m.keySet();
	    	for(String car : left) {
    			String start = m.get(car);
	    		String end = "23:59";
	    		
	    		int t = getTime(start, end);
	    		int current_time = 0;
	    		if(car_info.containsKey(car))
	    			current_time = car_info.get(car);
    			car_info.put(car, current_time + t);
	    	}
	    	
	    	// 요금 계산
	    	Set<String> cars = car_info.keySet();
	    	
	    	for(String s : cars) {
	    		int car = Integer.parseInt(s);
	    		int time = car_info.get(s);
	    		int fee = getFee(time);
	    		pq.add(new Node(car, fee));
	    	}
	    	
	        int[] answer = new int[pq.size()];
	        int idx = 0;
	        while(!pq.isEmpty()) {
	        	Node now = pq.poll();
	        	answer[idx++] = now.fee;
	        }
	        return answer;
	        
	    }
	    int getFee(int t) {
	    	int fee = 0;
			if(t <= basic_time) {
				fee = basic_fee;
			} else {
				fee += basic_fee;
				t -= basic_time;
				
				fee += (t / unit_time) * unit_fee;
				if(t%unit_time != 0)
					fee+=unit_fee;
			}
			return fee;
	    }
	    int getTime(String start, String end) {
	    	int start_h = Integer.parseInt(start.substring(0,2));
			int start_m = Integer.parseInt(start.substring(3,5));
			
			int end_h = Integer.parseInt(end.substring(0,2));
			int end_m = Integer.parseInt(end.substring(3,5));
			
			if(end_m < start_m) {
				end_h--;
				end_m+=60;
			}
			
			return (end_h - start_h)*60 + (end_m - start_m);
	    }
	}
	static class Node{
		int car;
		int fee;
		public Node(int car, int fee) {
			super();
			this.car = car;
			this.fee = fee;
		}
		
	}
	
	public static void main(String[] args) {
//		int fees[] = {180, 5000, 10, 600};
//		String records[] = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

		int fees[] = {1, 461, 1, 10};
		String records[] = {"00:00 1234 IN"};

		String test = "123456789";
		System.out.println(test.substring(0,3));
		Solution solution = new Solution();
		int[] ans = solution.solution(fees, records);
		for(int i : ans)
			System.out.println(i);
	}
}
