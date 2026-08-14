
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.lang.Exception;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
 static final int size = 16;
    static final int[] dx = { 0, 0, 1, -1 };
    static final int[] dy = { -1, 1, 0, 0 };

    static boolean checkValid(int x, int y, boolean[][] checkMap, int[][] map) {

        if (x < 0 || x >= size || y < 0 || y >= size)
            return false;
        if (checkMap[y][x] == true)
            return false;

        if(map[y][x] == 1)
            return false;

        
        return true;

    }

    static boolean checkPath(int[][] map, boolean[][] checkedMap, int x, int y) {

        if (map[y][x] == 3) {
            return true;
        }

        checkedMap[y][x] = true;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (checkValid(nx, ny, checkedMap,map)) {

                if (checkPath(map, checkedMap, nx, ny)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 0; tc < 10; ++tc) {

            int test_case = Integer.parseInt(br.readLine());
            int[][] map = new int[size][size];
            boolean[][] checked_map = new boolean[size][size];
            int startX = 0, startY = 0;

            for (int i = 0; i < size; i++) {
                String line = br.readLine();
                for (int j = 0; j < size; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        startY = i;
                        startX = j;
                    }
                }
            }

            int result = checkPath(map, checked_map, startX, startY) ? 1 : 0;
            System.out.println("#" + test_case + " " + result);
        }
    }
}