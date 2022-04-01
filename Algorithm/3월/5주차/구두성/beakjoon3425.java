package implemented;
import java.io.*;
import java.util.*;

public class beakjoon3425 {
    static int N,M;
    static ArrayList<String> command;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> stack = new Stack<>();
    static int INF = 1_000_000_000;
    static void solve(){
        stack.clear();

        for(int i = 0 ; i < N; i++){
            stack.add(arr[i]);
            for(String str : command){
                String[] com = str.split(" ");
                if(com[0].equals("NUM")){
                    stack.add(Integer.parseInt(com[1]));
                    continue;
                }
                if(stack.isEmpty()) break;
                
                switch(com[0]){
                    case "NUM":
                    stack.add(Integer.parseInt(com[1]));
                        continue;
                    case "POP":
                        stack.pop();
                        continue;
                    case "INV":                        
                        int a = stack.pop();
                        stack.add(-a);
                        continue;
                    case "DUP":
                        stack.add(stack.peek());
                        continue;
                }
                if(stack.size() < 2){
                    stack.clear();
                    break;
                }
                switch(com[0]){
                    case "SWP":
                        int b = stack.pop();
                        int c = stack.pop();
                        stack.add(b);
                        stack.add(c);
                    break;
                    case "ADD":
                        int e = stack.pop();
                        int f = stack.pop();
                        if(Math.abs(e+f) > INF){
                            stack.clear();
                        }
                        else{
                            stack.add(e+f);
                        }
                    break;
                    case "MUL":
                        long o = stack.pop();
                        long p = stack.pop();
                        if(Math.abs(o*p) > INF){
                            stack.clear();
                        }
                        else{
                            stack.add( (int)(o*p) );
                        }
                    break;
                    case "SUB":
                        int h = stack.pop();
                        int g = stack.pop();
                        if(Math.abs(g-h) > INF){
                            stack.clear();
                        }
                        else{
                            stack.add(g-h);
                        }
                    break;
                    case "DIV":
                        int m = stack.pop();
                        int n = stack.pop();
                        if(m==0){
                            stack.clear();
                        }
                        else{
                            if( (m > 0 && n > 0) || (m < 0 && n < 0) ){
                                stack.add(n/m);
                            }
                            else{
                                stack.add(-(Math.abs(n)/Math.abs(m)));
                            }
                        }
                    break;
                    case "MOD":
                        int x = stack.pop();
                        int y = stack.pop();
                        if(x==0){
                            stack.clear();
                        }
                        else{
                            int temp = Math.abs(y) % Math.abs(x);
                            if(temp > INF) stack.clear();
                            else{
                                if(y < 0) temp = -temp;
                                stack.add(temp);
                            }
                            
                        }
                    break;
                }
                
            }//end for list
            if(stack.isEmpty() || stack.size() != 1){
                sb.append("ERROR").append("\n");
            }
            else sb.append(stack.pop()).append("\n");
            
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        
        String str = "";
        while(br.ready()){
            command = new ArrayList<>();
            while(true){
                str = br.readLine();
                //System.out.println(str);
                if(str.equals("END") || str.equals("QUIT"))break;
                command.add(str);
            }

            if(str.equals("QUIT"))break;

            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            for(int i = 0 ; i < N; i++){
                arr[i] = Integer.parseInt(br.readLine());
            }

            solve();
            br.readLine();
            sb.append("\n");
        }
        // System.setIn(new FileInputStream("output.txt"));
        // br = new BufferedReader(new InputStreamReader(System.in));
        // String[] answer = sb.toString().split("\n");
        // int idx = 0;
        // while(br.ready()){
        //     str = br.readLine();
        //     if(!str.equals(answer[idx])){
        //         System.out.println(str + " " + answer[idx] + " " +idx);
        //         break;
        //     }
        //     idx++;
        // }

        System.out.print(sb);
    }
}
