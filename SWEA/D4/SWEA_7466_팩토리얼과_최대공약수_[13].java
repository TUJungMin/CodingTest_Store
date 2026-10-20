import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= testCase; ++tc) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			long answer = 1;

			// 경우 1: N이 K보다 크거나 같으면, N!은 무조건 K로 나누어 떨어집니다.
			if (N >= K) {
				answer = K;
			} 
			// 경우 2: N이 K보다 작다면, K의 약수 중 N 이하인 것들을 찾아서 나눠줍니다.
			else {
				for (int i = N; i >= 1; i--) {
					if (K % i == 0) {
						answer *= i; // 최대공약수의 일부이므로 곱해줌
						K /= i;      // K를 갱신
					}
					if (K == 1) break; // K가 1이 되면 더 이상 나눌 필요 없음
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
