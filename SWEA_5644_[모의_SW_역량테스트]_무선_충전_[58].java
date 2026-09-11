import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static class BC {
		int row, col, range, performance;
		BC(int r, int c, int rng, int p) {
			row = r; col = c; range = rng; performance = p;
		}
	}

	static int moveCount;
	static int BCcnt;
	static int[][] user; // user[i][j] = 방향 코드
	static BC[] bc;

	// 방향 코드 0=정지, 1=상, 2=우, 3=하, 4=좌
	// (상/하 부호는 좌표계 정의에 따라 반대일 수 있음 - 채점 결과 안 맞으면 dRow[1], dRow[3] 부호를 뒤집어서 시도)
	static int[] dRow = {0, -1, 0, 1, 0};
	static int[] dCol = {0, 0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			moveCount = Integer.parseInt(st.nextToken());
			BCcnt = Integer.parseInt(st.nextToken());
			user = new int[2][moveCount];
			bc = new BC[BCcnt];

			for (int i = 0; i < 2; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < moveCount; ++j) {
					user[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < BCcnt; ++i) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int performance = Integer.parseInt(st.nextToken());
				bc[i] = new BC(row, col, range, performance);
			}

			// 1) 매 초(T=0..moveCount) 사용자 A, B의 좌표를 미리 계산
			int[][] posRow = new int[2][moveCount + 1];
			int[][] posCol = new int[2][moveCount + 1];
			posRow[0][0] = 1;  posCol[0][0] = 1;  // 사용자 A 시작 위치
			posRow[1][0] = 10; posCol[1][0] = 10; // 사용자 B 시작 위치

			for (int u = 0; u < 2; ++u) {
				for (int t = 0; t < moveCount; ++t) {
					int dir = user[u][t];
					posRow[u][t + 1] = posRow[u][t] + dRow[dir];
					posCol[u][t + 1] = posCol[u][t] + dCol[dir];
				}
			}

			// 2) 매 초마다 얻을 수 있는 최대 충전량을 구해서 전부 더함
			//    (한 초의 선택이 다른 초에 영향을 주지 않으므로, 매 초를 독립적으로 최적화해도 됨)
			long answer = 0;
			for (int t = 0; t <= moveCount; ++t) {
				answer += bestChargeAtTime(posRow[0][t], posCol[0][t], posRow[1][t], posCol[1][t]);
			}

			sb.append(String.format("#%d %d\n", tc, answer));
		}
		System.out.print(sb);
	}

	// 이 시각에 사용자 A(ar,ac), 사용자 B(brow,bcol)가 얻을 수 있는 최대 충전량
	static long bestChargeAtTime(int ar, int ac, int brow, int bcol) {
		List<Integer> candA = new ArrayList<>();
		candA.add(-1); // -1 = 접속 안 함
		List<Integer> candB = new ArrayList<>();
		candB.add(-1);

		for (int i = 0; i < BCcnt; ++i) {
			if (Math.abs(bc[i].row - ar) + Math.abs(bc[i].col - ac) <= bc[i].range) 
				candA.add(i);
			if (Math.abs(bc[i].row - brow) + Math.abs(bc[i].col - bcol) <= bc[i].range) 
				candB.add(i);
		}

		long best = 0;
		for (int a : candA) {
			for (int b : candB) {
				long value;
				if (a == -1 && b == -1) {
					value = 0;
				} else if (a == b) { // 같은 BC를 같이 접속 -> 성능을 나눠가지므로 합치면 그대로 performance
					value = bc[a].performance;
				} else {
					value = 0;
					if (a != -1) value += bc[a].performance;
					if (b != -1) value += bc[b].performance;
				}
				best = Math.max(best, value);
			}
		}
		return best;
	}
}