import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());
            boolean[][] reach = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                reach[a][b] = true; // a < b
            }

            // Floyd-Warshall: 도달 가능성
            for (int k = 1; k <= N; k++)
                for (int i = 1; i <= N; i++)
                    if (reach[i][k])
                        for (int j = 1; j <= N; j++)
                            if (reach[k][j]) reach[i][j] = true;

            int count = 0;
            for (int i = 1; i <= N; i++) {
                int cnt = 0;
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (reach[i][j] || reach[j][i]) cnt++;
                }
                if (cnt == N - 1) count++;
            }
            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}