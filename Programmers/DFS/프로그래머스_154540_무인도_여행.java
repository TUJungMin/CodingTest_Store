import java.util.*;

class Solution {
    static int[][] map;
    static boolean[][] visited;
    static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dy = { -1, 1, 0, 0 };

    public int[] solution(String[] maps) {

        int[] answer = {};
        int row = maps.length;
        int col = maps[0].length();

        map = new int[row][col];
        visited = new boolean[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                char c = maps[i].charAt(j);
                if (c == 'X') {
                    map[i][j] = 0;

                } else
                    map[i][j] = c - '0';

            }

        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    list.add(dfs(i, j));
                }
            }
        }

        if (list.isEmpty()) {
            return new int[] { -1 };
        }

        Collections.sort(list);
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    static int dfs(int row, int col) {

        if (row < 0 || row > map.length - 1 || col < 0 || col > map[0].length - 1) // 범위밖
        {
            return 0;
        }
        if (map[row][col] == 0 || visited[row][col]) {
            return 0; // X인곳 이거나 방문
        }
        visited[row][col] = true;
        int sum = map[row][col];
        for (int i = 0; i < 4; ++i) {
            int nx = col + dx[i];
            int ny = row + dy[i];
            sum += dfs(row + dy[i], col + dx[i]);

        }
        return sum;
    }
}
