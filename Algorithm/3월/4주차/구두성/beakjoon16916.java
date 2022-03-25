package 다시풀기;
import java.io.*;
import java.util.*;

public class beakjoon16916 {
    static ArrayList<Integer> list;
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;

    public static void main(String[] args)throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String a = br.readLine();
        String b = br.readLine();

        

        if(KMP(a,b)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }

    }
    static boolean KMP(String str, String p){
        int[] pi = getPI(p);

        int strLen = str.length();
        int pLen = p.length();
        int j = 0;  // 접두사 idx
        for(int i = 0 ; i < strLen; i++){
            while( j > 0 && str.charAt(i) != p.charAt(j)) j = pi[j-1];
            
            if( str.charAt(i) == p.charAt(j) ){
                if(j == pLen - 1){
                    return true;
                    // cnt++;
                    // sb.append(i - j + 1).append(" ");
                    // j = pi[j];
                }
                else{
                    j++;
                }
            }
        }
        return false;
    }
    static int[] getPI(String str){
        int[] pi = new int[str.length()];
        char[] pArr = str.toCharArray();

        int j = 0;  // 접두사 idx
        for(int i = 1 ; i < str.length(); i++){// i = 접미사 idx
            //불일치 - 패턴이 다른경우
            //j 가 0이거나 같은 패턴일 떄까지 j업데이트
            while( j > 0 & pArr[i] != pArr[j]) j = pi[j-1];
            if( pArr[i] == pArr[j] ){
                j++;
                pi[i] = j;
            }
        }

        return pi;
    }
}
