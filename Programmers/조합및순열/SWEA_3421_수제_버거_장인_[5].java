import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static boolean[][] bad;    // bad[i][j] = i번과 j번은 같이 못 넣음
    static boolean[] picked;   // 현재 버거에 넣기로 한 재료
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            bad = new boolean[N + 1][N + 1];   // 테스트케이스마다 초기화
            picked = new boolean[N + 1];
            count = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                bad[a][b] = true;
                bad[b][a] = true;              // 궁합은 양방향
            }

            dfs(1);
            sb.append('#').append(tc).append(' ').append(count).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int idx) {
        if (idx > N) {          // 
            count++;            // 
            return;
        }

        // 1) idx번 재료를 넣는 경우 — 이미 넣은 재료들과 궁합이 맞을 때만
        if (canPick(idx)) {
            picked[idx] = true;
            dfs(idx + 1);
            picked[idx] = false;   // 선택 취소 (백트래킹)
        }

        // 2) idx번 재료를 넣지 않는 경우
        dfs(idx + 1);
    }

    static boolean canPick(int idx) {
        for (int i = 1; i < idx; i++) {
            if (picked[i] && bad[i][idx]) return false;
        }
        return true;
    }
}