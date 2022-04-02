
public class kakao2022blind_k진수에서소수개수구하기 {
	
	static boolean notPrime[];
    public static String decToK(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while(n>=k) {
            int temp = n % k;
            sb.append(temp);
            n/=k;
        }
        sb.append(n);
        String str_rev = sb.toString();
        String ret = "";
        for(int i = str_rev.length()-1; i>=0; i--)
            ret+=str_rev.charAt(i);
        return ret;
    }

    public static int isPrime(int n) {
        return (!notPrime[n] ? 1 : 0);
    }
    public static void eratos(int n) {
        notPrime = new boolean[n+1];
        notPrime[1] = true;

        for(int i = 2; i<=Math.sqrt(n); i++) {
            if(notPrime[i]) continue;

            for(int j = i+i; j<=Math.sqrt(n); j+=i)
                notPrime[j] = true;
        }
    }

    public static int solution(int n, int k) {
        int answer = 0;
        String str = decToK(n,k);	// 1. 진법 변환
        eratos(100000000);			// 2. 소수 초기화

        int len = str.length();
        String temp = "";
        // 3. 계산
        for(int i = 0; i<len; i++) {
            char c = str.charAt(i);
            
            // 현재 숫자가 0이 아니면 temp에 저장
            if(c != '0') {
            	temp+=c;
            	continue;
            }
            
            // 현재 저장된 숫자가 없으면 continue;
            if(temp.equals("")) continue;
            
            // 저장된 숫자가 소수인지 판별
            int num = Integer.parseInt(temp);
           
            answer += isPrime(num);
            temp = "";
        }

        // 저장된 숫자가 남아있으면
        if(!temp.equals("")) {
        	// 3진법의 경우 숫자 매우 길어짐
            if(k==3 && temp.length() >= 9) {
                long num = Long.parseLong(temp);
                int i;
                for(i = 2; i<=100000; i++) {
                    if(num%i == 0) break;
                }
                if(i==100001)
                    answer++;
            }
            else {
                int num = Integer.parseInt(temp);
                answer += isPrime(num);
            }
        }

        return answer;
   }


  
	public static void main(String[] args) {
		eratos(500000000);
		int ans = 0;
		for(int i = 1; i<=1000000; i++) {
			for(int k = 3; k<=10; k++) {
				System.out.println(solution(i, k));
			}
		}
		
		System.out.println();
	}
}
