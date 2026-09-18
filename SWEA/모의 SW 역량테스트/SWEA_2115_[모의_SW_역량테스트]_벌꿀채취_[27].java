import java.util.Scanner;

public class Solution {

    static int N, M, C;
    static int[][] hive;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();
            hive = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    hive[i][j] = sc.nextInt();

            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }

        System.out.print(sb);
    }

    static int solve() {
        int windowCount = N - M + 1;
        // profit[row][start] = 해당 행의 [start, start+M-1] 구간에서 얻을 수 있는 최대 수익
        int[][] profit = new int[N][windowCount];

        for (int r = 0; r < N; r++) {
            for (int s = 0; s < windowCount; s++) {
                profit[r][s] = bestProfit(r, s);
            }
        }

        int maxTotal = 0;

        // 모든 구간 쌍을 비교하여, 겹치지 않는 두 구간의 수익 합 중 최댓값을 탐색
        for (int r1 = 0; r1 < N; r1++) {
            for (int s1 = 0; s1 < windowCount; s1++) {
                for (int r2 = 0; r2 < N; r2++) {
                    for (int s2 = 0; s2 < windowCount; s2++) {
                        if (r1 == r2 && overlap(s1, s2)) continue; // 같은 행에서 겹치면 제외

                        int total = profit[r1][s1] + profit[r2][s2];
                        if (total > maxTotal) maxTotal = total;
                    }
                }
            }
        }

        return maxTotal;
    }

    // 같은 행에서 두 구간이 겹치는지 검사 (동일한 구간 선택도 겹침으로 처리됨)
    static boolean overlap(int s1, int s2) {
        return !(s1 + M <= s2 || s2 + M <= s1);
    }

    // 한 구간(연속된 M개의 벌통)에서 얻을 수 있는 최대 수익 계산
    // : 합이 C 이하가 되는 부분집합 중, 제곱합이 최대인 경우를 완전탐색으로 탐색
    static int bestProfit(int row, int start) {
        int[] values = new int[M];
        for (int i = 0; i < M; i++) values[i] = hive[row][start + i];

        int best = 0;
        int subsetCount = 1 << M; // 부분집합 개수: 2^M (M<=5 이므로 최대 32가지)

        for (int mask = 0; mask < subsetCount; mask++) {
            int sum = 0, squareSum = 0;
            for (int i = 0; i < M; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += values[i];
                    squareSum += values[i] * values[i];
                }
            }
            if (sum <= C && squareSum > best) {
                best = squareSum;
            }
        }
        return best;
    }
}