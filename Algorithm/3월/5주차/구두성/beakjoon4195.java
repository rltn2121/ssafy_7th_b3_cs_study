package String;
import java.io.*;
import java.util.*;
public class beakjoon4195 {
    static int N,M;
    static int[] parent;
    static int[] network;
    static Map<String, Integer> map = new HashMap<>();
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b){
            parent[b] = a;
            network[a] += network[b];
            network[b] = 1;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        parent = new int[200001];
        network = new int[200001];

        Integer aidx;
        Integer bidx;
        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            int len = N * 2;
            for(int i = 1; i <= len; i++){
                parent[i] = i;
                network[i] = 1;
            }
            
            int idx = 1;
            for(int i = 1; i <= N; i++){
                String[] str = br.readLine().split(" ");
                
                if((aidx = map.get(str[0])) == null) {
                    aidx = idx;
                    map.put(str[0], idx++);
                }
                
                if((bidx = map.get(str[1])) == null) {
                    bidx = idx;
                    map.put(str[1], idx++);
                }
                union(aidx, bidx);
                sb.append(network[find(aidx)]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
