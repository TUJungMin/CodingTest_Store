import java.util.*;
import java.io.*;

public class Solution {

	static int tankX = -1, tankY = -1;
	static int direction = 0; // 상 하 좌 우 순으로 1,2,3,4;
	static char[][] map;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; ++tc) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; ++i) {
				String line = br.readLine();
				for (int j = 0; j < W; ++j) {
					map[i][j] = line.charAt(j);
				}
			}
			InitTankCoord();

			int commandCount = Integer.parseInt(br.readLine());
			String commands = br.readLine();
			for (int i = 0; i < commands.length(); ++i) {
				proceed(commands.charAt(i));
			}
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i<H;++i) {
				for(int j = 0; j<W; ++j) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

	static void InitTankCoord() {
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {
				switch (map[i][j]) {
				case '^':
					tankY = i;
					tankX = j;
					direction = 1;
					break;
				case 'v':
					tankY = i;
					tankX = j;
					direction = 2;
					break;
				case '<':
					tankY = i;
					tankX = j;
					direction = 3;
					break;
				case '>':
					tankY = i;
					tankX = j;
					direction = 4;
					break;

				default:
					break;
				}
			}
		}
	}

	static void proceed(char cmd) {
		switch (cmd) {
		case 'U': 
		{
			map[tankY][tankX] = '^';
			direction = 1;
			int dy = tankY - 1;
			if (Ismap(tankX, dy)) {
				map[tankY][tankX] = '.';
				tankY = dy;
				map[tankY][tankX] = '^';
			}
		}
			break;
		case 'D': 
		{
			map[tankY][tankX] = 'v';
			direction = 2;
			int dy = tankY + 1;
			if (Ismap(tankX, dy)) {
				map[tankY][tankX] = '.';
				tankY = dy;
				map[tankY][tankX] = 'v';
			}
		}
			break;

		case 'L':
		{
			map[tankY][tankX] = '<';
			direction = 3;
			int dx = tankX - 1;
			if (Ismap(dx, tankY)) {
				map[tankY][tankX] = '.';
				tankX = dx;
				map[tankY][tankX] = '<';
			}
		}
			break;

		case 'R':
		{
			map[tankY][tankX] = '>';
			direction = 4;
			int dx = tankX + 1;
			if (Ismap(dx, tankY)) {
				map[tankY][tankX] = '.';
				tankX = dx;
				map[tankY][tankX] = '>';
			}
		}
			break;

		case 'S':
		{
		shoot();	
		}
			
			break;
		default:
			break;
		}
	}

	static boolean Ismap(int x, int y) {
		if ((x < 0 || x >= map[0].length) || (y < 0 || y >= map.length)) {
			return false;
		} 
		if(map[y][x] == '#'|| map[y][x] == '*'||map[y][x] == '-') {
			return false;
		}
			

			return true;
	}
	static void shoot() {
    switch (direction) {
    case 1: // 상
        for (int i = tankY - 1; i >= 0; --i) {
            if (map[i][tankX] == '#') return;
            if (map[i][tankX] == '*') { map[i][tankX] = '.'; return; }
        }
        break;
    case 2: // 하
        for (int i = tankY + 1; i < map.length; ++i) {
            if (map[i][tankX] == '#') return;
            if (map[i][tankX] == '*') { map[i][tankX] = '.'; return; }
        }
        break;
    case 3: // 좌
        for (int j = tankX - 1; j >= 0; --j) {
            if (map[tankY][j] == '#') return;
            if (map[tankY][j] == '*') { map[tankY][j] = '.'; return; }
        }
        break;
    case 4: // 우
        for (int j = tankX + 1; j < map[0].length; ++j) {
            if (map[tankY][j] == '#') return;
            if (map[tankY][j] == '*') { map[tankY][j] = '.'; return; }
        }
        break;
    }
}
}
