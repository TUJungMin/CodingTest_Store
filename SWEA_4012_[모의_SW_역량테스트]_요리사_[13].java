import java.io.*;
import java.util.*;
public class Solution {

	static int N;
	static int[][] synergy;
	static boolean[] inA;   // 각 식재료가 A음식인지 표시
	static int result;      // 두 음식 맛 차이의 최솟값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= test_case; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			synergy = new int[N + 1][N + 1];

			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; ++j) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			inA = new boolean[N + 1];
			result = Integer.MAX_VALUE;  // 최솟값이라 큰 값으로 시작

			inA[1] = true;               // 1번은 무조건 A에 고정
			int n = N / 2;
			dfs(2, 1, n);                // index=2부터, A에 이미 1개(1번) 들어감

			sb.append("#" + tc).append(" ").append(result).append("\n");
		}

		System.out.print(sb);
	}

	static void dfs(int index, int count, final int Max_index) {
		if (count == Max_index) {
			
			evaluate();
			return;
		}
		if (index > N) return;   // 더 볼 식재료가 없으면 종료
		
		inA[index] = true;                        // 해당 번호 선택
		dfs(index + 1, count + 1, Max_index);

		inA[index] = false;                       // 해당 번호 미선택 
		dfs(index + 1, count, Max_index);
	}
	
	static void evaluate() {
		int []a = new int [N/2];
		int []b = new int [N/2];
		
		int acnt=0,bcnt=0;
		for(int i = 1; i<=N; ++i) {
			if(inA[i])
				a[acnt++] = i;		//a에 조합 들어있음(1,3,4)
			else
				b[bcnt++] = i;		//b에 조합 들어있음(2,5,6)
				
		}
		
		int resultA = calculate(a);
		int resultB = calculate(b);
		
		int diff = Math.abs(resultA - resultB);
		result = Math.min(result, diff);
	}
	static int calculate(int[] arr) {
		int answer = 0;
		
		for(int i: arr) {
			for(int j:arr) {
				if(i!=j) {
					answer+=synergy[i][j];
				}
			}
		}
		return answer;
	}
	
}