import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
    static final int[] dx = { 0, 0, 1, -1 };
    static final int[] dy = { -1, 1, 0, 0 };
    static int N, W, H;
    static int answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            for (int i = 0; i < H; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = Integer.MAX_VALUE;          // (4) 테스트케이스마다 초기화
            dfs(0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int count) {
        int remain = calculate();
        if (count == N || remain == 0) {         // (5) 다 깨졌으면 더 볼 필요 없음
            answer = Math.min(answer, remain);   // (4) 최솟값 갱신
            return;
        }

        for (int col = 0; col < W; ++col) {
            // (2) 이 열에서 맨 위 벽돌 찾기
            int row = 0;
            while (row < H && map[row][col] == 0) row++;
            if (row == H) continue;              // 빈 열이면 쏠 필요 없음

            int[][] backup = copy(map);          // (1) 쏘기 전 상태 저장
            remove(row, col);
            drop();                              // (3) 중력 적용
            dfs(count + 1);
            map = backup;                        // (1) 복원
        }
    }

    static void remove(int row, int col) {
        int range = map[row][col];
        if (range == 0) return;
        map[row][col] = 0;
        for (int i = 1; i < range; ++i) {
            for (int d = 0; d < 4; ++d) {
                int nr = row + i * dy[d];
                int nc = col + i * dx[d];
                if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] != 0)
                    remove(nr, nc);
            }
        }
    }

    // 각 열마다 아래에서부터 벽돌을 채워 넣음
    static void drop() {
        for (int c = 0; c < W; ++c) {
            int write = H - 1;
            for (int r = H - 1; r >= 0; --r) {
                if (map[r][c] != 0) {
                    int v = map[r][c];
                    map[r][c] = 0;
                    map[write--][c] = v;
                }
            }
        }
    }

    static int[][] copy(int[][] src) {
        int[][] dst = new int[H][W];
        for (int i = 0; i < H; ++i) dst[i] = src[i].clone();
        return dst;
    }

    static int calculate() {
        int count = 0;
        for (int i = 0; i < H; ++i)
            for (int j = 0; j < W; ++j)
                if (map[i][j] != 0) count++;
        return count;
    }
}