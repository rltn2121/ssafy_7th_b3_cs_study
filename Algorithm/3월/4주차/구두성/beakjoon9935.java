package String;
import java.io.*;
import java.util.*;

public class beakjoon9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sb = br.readLine();
        
        String subStr = br.readLine();
        Stack<Character> st = new Stack<>();
        int str_len = sb.length();
        int sub_len = subStr.length();

        for(int i = 0 ; i < str_len; i++){  //문자열 길이만큼 탐색
            st.push(sb.charAt(i));
            
            if(st.size() >= sub_len){    //마지막 글자가 같으면
                boolean f= true;
                for(int j = 0; j < sub_len ; j++){
                    if(st.get(st.size()-sub_len+j) != subStr.charAt(j)){
                        f = false;
                        break;
                    }
                }
                if(f){
                    for(int j = 0; j < sub_len ; j++)
                        st.pop();
                }
            }
        }

        StringBuilder result = new StringBuilder();
        if(st.isEmpty()){
            System.out.println("FRULA");
            return;
        }
        for(Character c : st){
            result.append(c);
        }
        System.out.println(result);
    }
}
