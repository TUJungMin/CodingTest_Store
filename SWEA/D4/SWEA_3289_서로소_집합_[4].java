import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;




public class Solution {

	static String answer;
	static int N, M;
	static int[] parents;
	static void init() {
		for(int i = 1; i<parents.length; ++i) {
			parents[i] = i;
		}
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a !=b)		//부모(같은집합)가 다를때
			parents[b] = a;
	}
	
	static int find(int x) {
		if(parents[x]==x)
			return x;
		else
			return parents[x] = find(parents[x]);
		
	}
	static boolean isSame(int x, int y) {
		if(find(x) == find(y))
			return true;
		else 
			return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			answer = new String();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			init();	//1번 인덱스에는 1, 2번인덱스에는 2 ... 초기화
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				if(command == 0) {	//같은 집합이라는 소리 
					union(num1, num2);
				}
				else {		//같은 집합인지 확인
					String result = isSame(num1, num2) ? "1" : "0";
					sb.append(result);
				}
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}

}
