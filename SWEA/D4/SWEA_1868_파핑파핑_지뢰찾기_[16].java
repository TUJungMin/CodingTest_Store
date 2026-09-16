import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

	static int answer;
	static int N;
	static char[][] board;
	static int[][] count;      // 각 칸의 인접 지뢰 개수 (지뢰 칸은 -1로 표시)
	static boolean[][] visited;

	// 8방향 (상하좌우 + 대각선)
	static int[] dx = {-1,-1,-1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1,-1, 1,-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			board = new char[N][N];

			// 한 줄만 읽던 부분을 고쳐서, N개의 줄을 각각 읽어야 함
			for (int i = 0; i < N; ++i) {
				String line = br.readLine();
				for (int j = 0; j < N; ++j) {
					board[i][j] = line.charAt(j);
				}
			}

			count = new int[N][N];
			visited = new boolean[N][N];
			answer = 0; // static 필드라 테스트케이스마다 반드시 초기화

			// 1. 지뢰가 아닌 칸의 인접 지뢰 개수 계산
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (board[i][j] == '*') {
						count[i][j] = -1;
						continue;
					}
					int cnt = 0;
					for (int d = 0; d < 8; ++d) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if (board[nx][ny] == '*') cnt++;
					}
					count[i][j] = cnt;
				}
			}

			// 2. 0인 칸들을 BFS로 묶어서 한 번의 클릭으로 처리
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (board[i][j] != '*' && count[i][j] == 0 && !visited[i][j]) {
						answer++; // 이 덩어리 전체가 클릭 1번
						bfs(i, j);
						
					}
				}
			}

			// 3. 어떤 0 덩어리에도 속하지 못한 나머지 숫자 칸은 각각 클릭 필요
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (board[i][j] != '*' && !visited[i][j]) {
						answer++;
						visited[i][j] = true;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int sx, int sy) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[]{sx, sy});
		visited[sx][sy] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];

			// 0이 아닌 칸에서는 더 이상 퍼지지 않음 (자기 자신은 이미 열렸음)
			if (count[x][y] != 0) continue;

			for (int d = 0; d < 8; ++d) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (board[nx][ny] == '*' || visited[nx][ny]) continue;

				visited[nx][ny] = true;
				q.add(new int[]{nx, ny});
			}
		}
	}
}