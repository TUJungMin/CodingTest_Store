import java.io.*;
import java.util.*;

public class Solution {

	static int N, P;          // 방 크기, 사람 수
	static int[][] people;    // people[i] = {r, c}
	static int[][] stairs;    // stairs[k] = {r, c, 길이}
	static int[] choice;      // choice[i] = i번 사람이 고른 계단 (0 or 1)
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			people = new int[10][2];
			stairs = new int[2][3];
			P = 0;
			int s = 0;

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int v = Integer.parseInt(st.nextToken());
					if (v == 1) {
						people[P][0] = r;
						people[P][1] = c;
						P++;
					} else if (v >= 2) {
						stairs[s][0] = r;
						stairs[s][1] = c;
						stairs[s][2] = v;
						s++;
					}
				}
			}

			choice = new int[P];
			answer = Integer.MAX_VALUE;
			dfs(0);
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	// 힌트 1: idx번 사람의 계단을 고르는 재귀 (2^P가지)
	static void dfs(int idx) {
		if (idx == P) {
			answer = Math.min(answer, simulate());
			return;
		}
		for (int k = 0; k < 2; k++) {
			choice[idx] = k;
			dfs(idx + 1);
		}
	}

	// 힌트 2: 두 계단은 독립 -> 각각 계산해서 늦게 끝나는 쪽이 이 배정의 완료 시간
	static int simulate() {
		return Math.max(stairTime(0), stairTime(1));
	}

	// 힌트 3~4: 계단 k 하나를 쓰는 사람들이 모두 내려가는 시간
	static int stairTime(int k) {
		int sr = stairs[k][0], sc = stairs[k][1], K = stairs[k][2];

		// 이 계단을 고른 사람들의 도착 시간 모으기
		int[] arrive = new int[P];
		int cnt = 0;
		for (int i = 0; i < P; i++) {
			if (choice[i] == k) {
				arrive[cnt++] = Math.abs(people[i][0] - sr) + Math.abs(people[i][1] - sc);
			}
		}
		if (cnt == 0) return 0;          // 힌트 5: 아무도 안 쓰는 계단

		Arrays.sort(arrive, 0, cnt);     // 먼저 도착한 사람이 먼저 올라감

		int[] end = new int[cnt];        // end[i] = i번째 사람이 계단을 다 내려간 시각
		for (int i = 0; i < cnt; i++) {
			int start = arrive[i] + 1;                   // 도착 후 1분 대기
			if (i >= 3) start = Math.max(start, end[i - 3]); // 3명 꽉 차면 3칸 앞 사람이 나갈 때까지
			end[i] = start + K;
		}
		return end[cnt - 1];
	}
}