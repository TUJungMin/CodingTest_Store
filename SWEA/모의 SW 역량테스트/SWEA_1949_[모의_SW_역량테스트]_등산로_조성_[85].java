import java.util.*;
import java.io.*;

public class Solution {
	public static int[][] mountainLoads;
	public static int N;
	public static int K;
	public static int maxLoad;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// N: 등산로 크기, K: 깎을 수 있는 산 크기
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			mountainLoads = new int[N][N];
			int maxHeight = 0;
			
			// 등산로 초기화 및 최대 등산로 높이 구하기
			for(int i = 0; i < N; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int curr = Integer.parseInt(st2.nextToken());
					maxHeight = Math.max(curr, maxHeight);
					mountainLoads[i][j] = curr;
				}
			}
			
			// 해당 등산로 방문 확인
			boolean[][] visited = new boolean[N][N];
			
			// 등산로 최대 길이
			maxLoad = 0;
			
			// 최대 높이인 등산로 탐색
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (maxHeight == mountainLoads[i][j]) {
						visited[i][j] = true;
						nextLoad(i, j, true, visited, 1);
						visited[i][j] = false;
					}
				}
			}
			System.out.printf("#%d %d%n", tc, maxLoad);
		}
	}
	
	public static void nextLoad(int x, int y, boolean shield, boolean[][] visited, int count) {
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		// 더이상 방문한 등산로가 없는지 확인
		boolean check = false;
		
		// 상하좌우 반복
		for(int i = 0; i < 4; i++) {
			
			// 등산로 범위 밖이거나 방문한적 있는 등산로인지 확인
			if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < N && !visited[x + dx[i]][y + dy[i]]) {
				
				// 현재 등산로보다 아래고 방문한 적 없는 등산로면
				if(mountainLoads[x][y] > mountainLoads[x + dx[i]][y + dy[i]]) {
					visited[x + dx[i]][y + dy[i]] = true;
					nextLoad(x + dx[i], y + dy[i], shield, visited, count + 1);
					visited[x + dx[i]][y + dy[i]] = false;
					check = true;
				} else {
					// 방문 한 적 없지만 실드가 남아있고 깎을 수 있으면 
					if (shield && mountainLoads[x][y] > mountainLoads[x + dx[i]][y + dy[i]] - K) {
						// 기존 높이 저장
						int tempMountainLoad = mountainLoads[x + dx[i]][y + dy[i]];
						// 산 깎기: 이전 높이 - 1로
						mountainLoads[x + dx[i]][y + dy[i]] = mountainLoads[x][y] - 1;
						visited[x + dx[i]][y + dy[i]] = true;
						// 실드 부서짐
						shield = false;
						nextLoad(x + dx[i], y + dy[i], shield, visited, count + 1);
						mountainLoads[x + dx[i]][y + dy[i]] = tempMountainLoad;
						visited[x + dx[i]][y + dy[i]] = false;
						shield = true;
						check = true;
					}
				}
			}
		}
		
		if (!check) {
			maxLoad = Math.max(maxLoad, count);
		}
	}
}
