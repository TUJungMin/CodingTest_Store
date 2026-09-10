import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int answer;
	static int maxCoreCount;
	static int N;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= test_case; ++tc) {
			answer = Integer.MAX_VALUE;
			maxCoreCount = 0; // 초기화
			ArrayList<int[]> coreCoord = new ArrayList<>(); // 가장자리가 아닌 core 좌표만 저장
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int edgeCoreCount = 0; // 가장자리 core는 이미 연결된 것으로 간주 -> DFS 대상 아님

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							edgeCoreCount++;
						} else {
							coreCoord.add(new int[] { i, j });
						}
					}
				}
			}

			DFS(coreCoord, 0, coreCoord.size(), 0, 0);

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

	static void DFS(ArrayList<int[]> coreCoord, int depth, int r, int currCoreCount, int lineWeight) {
		if (depth == r) { // 끝까지 코어를 다 돌았을 때 (완성된 조합)
			if (currCoreCount > maxCoreCount) {
				maxCoreCount = currCoreCount;
				answer = lineWeight;
			} else if (currCoreCount == maxCoreCount) {
				answer = Math.min(answer, lineWeight);
			}
			return; // 이게 빠져있었음. 없으면 depth가 r을 넘어서도 계속 재귀호출됨(무한 재귀)
		}

		int r0 = coreCoord.get(depth)[0];
		int c0 = coreCoord.get(depth)[1];

		// 1) 이 코어를 4방향 중 하나로 연결하는 경우
		for (int dir = 0; dir < 4; ++dir) {
			int len = checkDir(r0, c0, dir);
			if (len == -1)
				continue; // 그 방향은 다른 코어/전선에 막혀서 못 감

			markWire(r0, c0, dir, len, 2); // 임시로 전선 표시 (다음 코어들이 이 경로를 지나가지 못하게)
			DFS(coreCoord, depth + 1, r, currCoreCount + 1, lineWeight + len);
			markWire(r0, c0, dir, len, 0); // 백트래킹: 표시 해제
		}

		// 2) 이 코어는 연결하지 않는 경우
		DFS(coreCoord, depth + 1, r, currCoreCount, lineWeight);
	}

	// (r0, c0)에서 dir 방향으로 가장자리까지 직선이 뚫려있는지 확인.
	// 뚫려있으면 그 길이(칸 수)를 반환, 중간에 막히면 -1 반환
	static int checkDir(int r0, int c0, int dir) {
		int nr = r0, nc = c0, len = 0;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (map[nr][nc] != 0)
				return -1; // 다른 core(1) 혹은 이미 놓인 전선(2)에 막힘
			len++;
			if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1)
				break; // 가장자리(전원)에 도달
		}
		return len;
	}

	// (r0, c0)에서 dir 방향으로 len칸을 value로 채움 (전선 표시 / 해제 겸용)
	static void markWire(int r0, int c0, int dir, int len, int value) {
		int nr = r0, nc = c0;
		for (int i = 0; i < len; ++i) {
			nr += dr[dir];
			nc += dc[dir];
			map[nr][nc] = value;
		}
	}
}