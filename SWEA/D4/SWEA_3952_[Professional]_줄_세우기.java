import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static ArrayList<Integer>[] graph;
	static int[] Indegree;
	static Queue<Integer> q;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; ++tc) {
			sb = new StringBuilder();
			q = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new ArrayList[N + 1];
			Indegree = new int[N + 1];
			for (int i = 1; i < N + 1; ++i) {
				graph[i] = new ArrayList<Integer>();
			}
			

			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
				Indegree[to]++;
			}
			for(int idx = 1; idx < N+1; ++idx) {
				if(Indegree[idx] == 0)
					q.offer(idx);
			}
			sb.append("#").append(tc);
			TopologicalSort();
			System.out.println(sb);
			
		}
	}
	 static void TopologicalSort() {
		 while(!q.isEmpty()) {
			 int num = q.poll();
			 sb.append(" ").append(num);
			 
			 for(int n:graph[num]) {
				 Indegree[n]--;
				 if(Indegree[n] == 0) {
					 q.offer(n);
				 }
			 }
		 }
	 }
}
