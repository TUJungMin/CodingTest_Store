import java.util.*;
import java.io.*;

class Solution {
    
    static class Node /*implements Comparable<Node>*/ {
        int row;
        int col;
        int value; // 출발지부터 현재 칸까지의 누적 최소 비용

        Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        // @Override
        // public int compareTo(Node o) {
        //     return Integer.compare(this.value, o.value);
        // }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringBuilder sb = new StringBuilder();
        int test_case = Integer.parseInt(br.readLine().trim());

        // 방향 벡터 (상, 하, 좌, 우)
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int tc = 1; tc <= test_case; ++tc) {
            int mapSize = Integer.parseInt(br.readLine().trim());

            // 1. map 입력 받기 (※ 보급로는 공백 없이 숫자가 붙어서 들어옵니다)
            int[][] map = new int[mapSize][mapSize];
            for (int i = 0; i < mapSize; ++i) {
               
                String line = br.readLine().trim();
                for (int j = 0; j < mapSize; ++j) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            // 2. 최소 비용을 기록할 distance 배열 초기화
            int[][] distance = new int[mapSize][mapSize];
            for (int i = 0; i < mapSize; i++) {
                for(int j = 0; j<mapSize; ++j){
               distance[i][j] = Integer.MAX_VALUE;
            }
        }

            // 3. 다익스트라 탐색 준비
           // PriorityQueue<Node> pq = new PriorityQueue<>();
           // PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.value));
            // 시작점 (0,0) 설정 
            distance[0][0] = 0; 
            pq.offer(new Node(0, 0, 0));

            int answer = 0;

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int row = current.row;
                int col = current.col;
                int val = current.value;

                // 목적지(우하단)에 도달하면 탐색 종료 
                if (row == mapSize - 1 && col == mapSize - 1) {
                    answer = val;
                    break;
                }

                // 이미 가공된 비용보다 크다면 패스
                if (distance[row][col] < val) {
                    continue;
                }

                // 4. 상하좌우 탐색
                for (int i = 0; i < 4; i++) {
                    int nr = row + dy[i];
                    int nc = col + dx[i];

                    // 범위 검사
                    if (nr >= 0 && nr < mapSize && nc >= 0 && nc < mapSize) {
                        int nextCost = val + map[nr][nc];

                        // 더 작은 비용으로 갱신이 가능한 경우
                        if (nextCost < distance[nr][nc]) {
                            distance[nr][nc] = nextCost;
                            pq.offer(new Node(nr, nc, nextCost));
                        }
                    }
                }
            }

            // SWEA 양식에 맞춰 출력 (#테스트케이스 정답)
                sb.append("#").append(tc).append(" ").append(answer).append("\n");
           
        }
        System.out.print(sb);
    }
}