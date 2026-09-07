import java.util.*;
import java.io.*;

public class Solution {
	static int[] gyu = new int[9];
	static int[] in = new int[9];
	static long win, lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[] used = new boolean[19];
			for (int i = 0; i < 9; ++i) {
				gyu[i] = Integer.parseInt(st.nextToken());
				used[gyu[i]] = true;
			}
			int idx = 0;
			for (int n = 1; n <= 18; ++n) {
				if (!used[n]) {
					in[idx++] = n;
				}
			}
			win = 0;
			lose = 0;
			permutation(0);
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.print(sb);
	}

	static void permutation(int depth) {
		if (depth == 9) {
			int gyuScore = 0;
			for (int i = 0; i < 9; ++i) {
				if (gyu[i] > in[i]) {
					gyuScore += gyu[i] + in[i];
				}
			}
			if (gyuScore >= 86) {
				win++;
			} else {
				lose++;
			}
			return;
		}
		for (int i = depth; i < 9; ++i) {
			swap(depth, i);
			permutation(depth + 1);
			swap(depth, i);
		}
	}

	static void swap(int a, int b) {
		int tmp = in[a];
		in[a] = in[b];
		in[b] = tmp;
	}
}