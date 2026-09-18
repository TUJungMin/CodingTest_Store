
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


/**
 * @author TaeHee
 */
public class Solution {

    static int N = 4001;
    static int[][] map = new int[N][N]; // map[y][x] 
    static int[] dx = { 0, 0, -1, 1 };     // 
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= TC; ++tc) {
        	int totalEnergy = 0; //         
            int count = Integer.parseInt(br.readLine()); // 
            ArrayDeque<Unit> dq = new ArrayDeque<>(); // 

            for (int i = 0; i < count; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                // -1000 ~ 1000 -> 0 ~ 2000 -> 0 ~ 4000
                int x = (Integer.parseInt(st.nextToken()) + 1000) << 1;
                int y = (Integer.parseInt(st.nextToken()) + 1000) << 1;
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                map[y][x] = e; // 
                dq.addLast(new Unit(x, y, dir, e)); 
            }

            // 
            
            while (!dq.isEmpty()) {
            	Unit cur = dq.pollFirst();
            	
            	if(map[cur.y][cur.x] != cur.e) {// 
            		totalEnergy += map[cur.y][cur.x];
            		map[cur.y][cur.x] = 0;
            		continue;
            	}
            	
            	// 
            	map[cur.y][cur.x] = 0;
            	int nx = cur.x + dx[cur.dir];
            	int ny = cur.y + dy[cur.dir];
            	
            	if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
            	
            	cur.x = nx;
            	cur.y = ny;
            	map[cur.y][cur.x] += cur.e;
            	
            	dq.addLast(cur);
				
			}
            
            System.out.println("#" + tc + " " + totalEnergy);
        }
    }

    static class Unit {
        int x, y, dir, e;

        public Unit(int x, int y, int dir, int e) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.e = e;
        }
    }
}






