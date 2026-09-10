import java.util.Scanner;

public class Solution {
	static int N;
	static int[] mountains;
	static int[] up;   // up[i] = i에서 "끝나는" 연속 증가 구간의 길이
	static int[] down; // down[i] = i에서 "시작하는" 연속 감소 구간의 길이

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; ++tc) {
			N = sc.nextInt();
			mountains = new int[N + 1]; // 1-indexed
			up = new int[N + 1];
			down = new int[N + 1];

			for (int i = 1; i <= N; ++i) {
				mountains[i] = sc.nextInt();
			}

			// 왼쪽에서 오른쪽으로 훑으며 "i에서 끝나는 연속 증가 길이" 계산
			up[1] = 1;
			for (int i = 2; i <= N; ++i) {
				if (mountains[i - 1] < mountains[i]) {
					up[i] = up[i - 1] + 1;
				} else {
					up[i] = 1;
				}
			}

			// 오른쪽에서 왼쪽으로 훑으며 "i에서 시작하는 연속 감소 길이" 계산
			down[N] = 1;
			for (int i = N - 1; i >= 1; --i) {
				if (mountains[i] > mountains[i + 1]) {
					down[i] = down[i + 1] + 1;
				} else {
					down[i] = 1;
				}
			}

			// k를 봉우리로 하는 "우뚝 선 산"의 개수 = (왼쪽에서 고를 수 있는 i의 수) * (오른쪽에서 고를 수 있는 j의 수)
			long answer = 0;
			for (int k = 1; k <= N; ++k) {
				long leftChoices = up[k] - 1;   // i < k 인 후보 개수
				long rightChoices = down[k] - 1; // j > k 인 후보 개수
				answer += leftChoices * rightChoices;
			}

			sb.append(String.format("#%d %d\n", tc, answer));
		}
		System.out.print(sb);
		sc.close();
	}
}