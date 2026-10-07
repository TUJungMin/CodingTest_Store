import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, min;
	static int[][] coord;	// 0: 회사, 1~N: 고객(경유지), N+1: 집

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test_case = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= test_case; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			coord = new int[N + 2][2];
			st = new StringTokenizer(br.readLine());

			// 회사
			coord[0][0] = Integer.parseInt(st.nextToken());
			coord[0][1] = Integer.parseInt(st.nextToken());
			// 집 -> 마지막 칸
			coord[N + 1][0] = Integer.parseInt(st.nextToken());
			coord[N + 1][1] = Integer.parseInt(st.nextToken());
			// 고객 -> 1 ~ N
			for (int i = 1; i <= N; ++i) {
				coord[i][0] = Integer.parseInt(st.nextToken());
				coord[i][1] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			perm(1, 0);
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
	}

	// depth번 칸에 올 경유지를 정함, dist = 회사부터 coord[depth-1]까지 누적 거리
	static void perm(int depth, int dist) {
		if (dist >= min) return;	// 가지치기

		if (depth == N + 1) {		// 경유지 순서 확정 -> 마지막 고객에서 집까지 더함
			min = Math.min(min, dist + distance(coord[N], coord[N + 1]));
			return;
		}

		for (int i = depth; i <= N; ++i) {
			swap(depth, i);
			perm(depth + 1, dist + distance(coord[depth - 1], coord[depth]));
			swap(depth, i);			// 원상복구
		}
	}

	static void swap(int a, int b) {
		int[] tmp = coord[a];
		coord[a] = coord[b];
		coord[b] = tmp;
	}

	static int distance(int[] p, int[] q) {
		return Math.abs(p[0] - q[0]) + Math.abs(p[1] - q[1]);
	}
} 	