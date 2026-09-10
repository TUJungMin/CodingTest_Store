import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int B;
	static int[] workers;
	static int answer;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= test_case; ++tc) {
			answer = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			workers = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; ++i) {
				workers[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0,0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

	static void dfs(int depth,int sum) {
		if(depth == N) {
			if(sum - B < 0) {
				return;
			}else {
				answer = Math.min(answer, sum-B);
				return;
			}
		}
		dfs(depth+1,sum+workers[depth]);
		
		dfs(depth+1,sum);
		
	}
}
