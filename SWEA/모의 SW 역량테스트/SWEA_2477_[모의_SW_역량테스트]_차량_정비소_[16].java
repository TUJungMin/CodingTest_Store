import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{

	static int answer;
	static int N, M, K, A, B;
	static int[] a, b, t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			answer = -1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken()) - 1;
			B = Integer.parseInt(st.nextToken()) - 1;
			a = new int[N];
			b = new int[M];
			t = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; ++i)
				b[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; ++i)
				t[i] = Integer.parseInt(st.nextToken());

			simulate();

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

	static void simulate() {
		// 고객별 기록: 이용한 접수 창구 / 정비 창구 번호
		int[] recDesk = new int[K];
		int[] repDesk = new int[K];

		// 창구 상태: 앉아 있는 고객 번호(-1이면 빈 창구), 끝나는 시각
		int[] recWho = new int[N];
		int[] recEnd = new int[N];
		int[] repWho = new int[M];
		int[] repEnd = new int[M];
		Arrays.fill(recWho, -1);
		Arrays.fill(repWho, -1);

		// 대기열
		ArrayDeque<Integer> recQ = new ArrayDeque<>();
		ArrayDeque<Integer> repQ = new ArrayDeque<>();

		int time = 0;
		int next = 0; // 다음에 도착할 고객 인덱스
		int done = 0; // 정비까지 끝난 고객 수

		while (done < K) {
			// 1. 접수 끝난 고객 -> 정비 대기열 (창구 번호 작은 순으로 확인)
			for (int i = 0; i < N; ++i) {
				if (recWho[i] != -1 && recEnd[i] == time) {
					repQ.offer(recWho[i]);
					recWho[i] = -1;
				}
			}

			// 2. 정비 끝난 고객 -> 완료
			for (int j = 0; j < M; ++j) {
				if (repWho[j] != -1 && repEnd[j] == time) {
					repWho[j] = -1;
					done++;
				}
			}

			// 3. 지금 도착한 고객 -> 접수 대기열
			while (next < K && t[next] <= time) {
				recQ.offer(next++);
			}

			// 4. 빈 접수 창구 채우기
			for (int i = 0; i < N; ++i) {
				if (recWho[i] == -1 && !recQ.isEmpty()) {
					int c = recQ.poll();
					recWho[i] = c;
					recEnd[i] = time + a[i];
					recDesk[c] = i;
				}
			}

			// 5. 빈 정비 창구 채우기
			for (int j = 0; j < M; ++j) {
				if (repWho[j] == -1 && !repQ.isEmpty()) {
					int c = repQ.poll();
					repWho[j] = c;
					repEnd[j] = time + b[j];
					repDesk[c] = j;
				}
			}

			time++;
		}

		// 답 계산
		int sum = 0;
		for (int k = 0; k < K; ++k) {
			if (recDesk[k] == A && repDesk[k] == B) {
				sum += k + 1; // 고객 번호는 1부터
			}
		}
		if (sum > 0)
			answer = sum;
	}
}