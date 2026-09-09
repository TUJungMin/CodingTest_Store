import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int N;
	static int full;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			full = (1 << N) - 1;

			int result = N_Queen(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

	// col, diag1, diag2 : 지금까지 놓인 퀸들이 점유한 열 / 좌대각선 / 우대각선을 나타내는 비트마스크
	static int N_Queen(int col, int diag1, int diag2) {
		if (col == full) {              // 열 N개를 전부 채웠다 -> 유효한 배치 1개 완성
			return 1;
		}

		int cnt = 0;
		int available = full & ~(col | diag1 | diag2); // 이번 행에서 놓을 수 있는 위치들만 1로 남김

		while (available != 0) {
			int p = available & (-available); // 가능한 위치 중 하나(가장 오른쪽 1비트) 선택
			available -= p;                     // 선택한 위치는 후보에서 제거

			cnt += N_Queen(col | p, (diag1 | p) << 1, (diag2 | p) >> 1);
			// col, diag1, diag2는 값으로 전달되므로 재귀에서 돌아오면 자동으로 되돌려짐 -> 되돌리기 코드 불필요
		}
		return cnt;
	}
}