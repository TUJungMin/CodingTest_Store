import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5648_곽정민 {

    static class Atom {
        int x, y, dir, energy;
        boolean alive = true;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0}; // 상(0) 하(1) 좌(2) 우(3)
    static Atom[] atoms;
    static int N;
    static int aliveCount;
    static int[][] map; // map[y][x] = 그 좌표에 있는(살아있는) 원자 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            atoms = new Atom[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                // 원래 좌표 -1000~1000 -> +1000으로 0~2000 -> *2로 0~4000
                // (음수 없이 map 배열 인덱스로 바로 쓰기 위한 오프셋 + 스케일링)
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());
                atoms[i] = new Atom(x, y, dir, energy);
            }

            long totalEnergy = 0;
            aliveCount = N;
            map = new int[4001][4001];

            // 모든 원자가 죽을 때까지(충돌하거나 맵 밖으로 나갈 때까지) 반복
            while (aliveCount != 0) {
                totalEnergy += moveAndCollide();
            }

            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }

        System.out.print(sb);
    }

    // 한 스텝 이동 + 충돌 판정을 수행하고, 이번 스텝에 방출된 에너지 합을 반환
    static long moveAndCollide() {
        long energyThisStep = 0;
        List<int[]> touched = new ArrayList<>(); // 이번 스텝에 건드린 칸만 기록 (나중에 0으로 되돌리기 위함)

        // 1. 살아있는 원자만 이동
        for (int i = 0; i < N; i++) {
            if (!atoms[i].alive) continue;

            int nx = atoms[i].x + dx[atoms[i].dir];
            int ny = atoms[i].y + dy[atoms[i].dir];

            // 맵(0~4000) 밖으로 나가면 다른 원자와 다시는 못 만나므로 그냥 소멸 (에너지 방출 없음)
            if (nx < 0 || nx > 4000 || ny < 0 || ny > 4000) {
                atoms[i].alive = false;
                aliveCount--;
                continue;
            }

            atoms[i].x = nx;
            atoms[i].y = ny;

            if (map[ny][nx] == 0) {
                touched.add(new int[]{nx, ny}); // 새로 건드린 칸만 기록
            }
            map[ny][nx]++;
        }

        // 2. 같은 칸에 2개 이상 모였으면 충돌 -> 그 칸에 있는 원자 전부 소멸
        //    (홀수/짝수 상관없이 그 칸에 모인 개수만큼 전부 처리됨)
        for (int i = 0; i < N; i++) {
            if (!atoms[i].alive) continue;

            if (map[atoms[i].y][atoms[i].x] > 1) {
                energyThisStep += atoms[i].energy;
                atoms[i].alive = false;
                aliveCount--;
            }
        }

        // 3. 이번 스텝에 건드린 칸만 다시 0으로 초기화 (맵 전체를 매번 새로 만들 필요 없음)
        for (int[] p : touched) {
            map[p[1]][p[0]] = 0;
        }

        return energyThisStep;
    }
}