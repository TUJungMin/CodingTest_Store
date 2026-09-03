import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] S;
    static boolean[] inA;
    static int best;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    S[i][j] = Integer.parseInt(st.nextToken());
            }

            inA = new boolean[N];
            best = Integer.MAX_VALUE;

            inA[0] = true;          // 0번은 항상 A에 고정
            divide(1, 1);

            sb.append("#").append(tc).append(" ").append(best).append("\n");
        }
        System.out.print(sb);
    }

    static void divide(int idx, int countA) {
        if (countA == N / 2) {      // A가 다 찼으면 나머지는 B
            evaluate();
            return;
        }
        if (idx >= N) return;
        if (N - idx < N / 2 - countA) return; // 가지치기

        inA[idx] = true;            // A에 포함
        divide(idx + 1, countA + 1);

        inA[idx] = false;           // B로 보냄
        divide(idx + 1, countA);
    }

    static void evaluate() {
        // A, B 그룹 원소 수집
        int[] a = new int[N / 2];
        int[] b = new int[N / 2];
        int ai = 0, bi = 0;
        for (int i = 0; i < N; i++) {
            if (inA[i]) a[ai++] = i;
            else        b[bi++] = i;
        }

        int tasteA = groupTaste(a);
        int tasteB = groupTaste(b);
        best = Math.min(best, Math.abs(tasteA - tasteB));
    }

    // 그룹 내 모든 순서쌍의 시너지 합
    static int groupTaste(int[] g) {
        int sum = 0;
        for (int i = 0; i < g.length; i++)
            for (int j = 0; j < g.length; j++)
                if (i != j)
                    sum += S[g[i]][g[j]];
        return sum;
    }
}