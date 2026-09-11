import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int V; // 정점 개수
    static int E; // 간선 개수

    static ArrayList<Integer>[] graph;
    static int[] indegree;

    static Queue<Integer> q;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; ++tc) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            indegree = new int[V + 1];
            q = new ArrayDeque<>();

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 입력
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < E; i++) {

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                // from -> to
                graph[from].add(to);

                // to로 들어오는 간선 개수 증가
                indegree[to]++;
            }

            // 진입차수가 0인 정점부터 Queue에 삽입
            for (int i = 1; i <= V; i++) {

                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            sb = new StringBuilder();
            sb.append("#").append(tc);

            TopologicalSort();

            System.out.println(sb);
        }
    }

    static void TopologicalSort() {

        while (!q.isEmpty()) {

            int num = q.poll();

            sb.append(" ").append(num);

            // num에서 나가는 간선 확인
            for (int next : graph[num]) {

                // num -> next 간선을 제거한 것과 동일
                indegree[next]--;

                // 이제 next로 들어오는 간선이 없다면
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}