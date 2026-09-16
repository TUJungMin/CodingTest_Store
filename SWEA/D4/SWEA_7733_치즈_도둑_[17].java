import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7733_곽정민 {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	// 4방향 (상하좌우) - 이 문제는 8방향이 아니라 4방향 인접만 하나의 덩어리로 본다
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= testCase; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int maxChunk = 1;
			// 요정이 갉아먹는 100일 동안, 매일 끝난 뒤 남은 치즈의 덩어리 개수를 확인
			for (int day = 1; day <= 100; ++day) {
				int chunk = countChunks(day);
				maxChunk = Math.max(maxChunk, chunk);
			}

			sb.append("#").append(tc).append(" ").append(maxChunk).append("\n");
		}
		System.out.println(sb);
	}

	// day일이 지난 뒤(맛 1~day인 칸은 전부 먹힌 상태) 남아있는 칸들의 4방향 연결 요소 개수를 센다
	static int countChunks(int day) {
		visited = new boolean[N][N];
		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (map[i][j] > day && !visited[i][j]) {
					bfs(i, j, day);
					count++;
				}
			}
		}
		return count;
	}

	static void bfs(int si, int sj, int day) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{si, sj});
		visited[si][sj] = true; // 큐에 넣는 시점에 바로 방문 처리 (지난번 버그 수정 반영)

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; ++d) {
				int ni = cur[0] + dx[d];
				int nj = cur[1] + dy[d];
				if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
				if (visited[ni][nj]) continue;
				if (map[ni][nj] <= day) continue; // 이미 먹혀서 없는 칸
				visited[ni][nj] = true;
				q.offer(new int[]{ni, nj});
			}
		}
	}
}