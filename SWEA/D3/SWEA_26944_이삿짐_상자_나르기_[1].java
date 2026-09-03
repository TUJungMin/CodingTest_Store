import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= test_case; ++tc) {
			int result = 0;
			PriorityQueue<Integer> boxes = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> workers = new PriorityQueue<>(Collections.reverseOrder());

			StringTokenizer st = new StringTokenizer(br.readLine());
			int boxCount = Integer.parseInt(st.nextToken());
			int workerCount = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < boxCount; ++i) {

				boxes.offer(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < workerCount; ++i) {

				workers.offer(Integer.parseInt(st.nextToken()));
				
			}

			while (!workers.isEmpty()) {
				int currWorker = workers.poll(); // 현재 가장 무거운걸 들수있는 노동자

				while (!boxes.isEmpty()) {

					int currBox = boxes.poll(); // 남아있는 박스들 중 제일 무거운 박스
					if (currBox <= currWorker) { // 노동자가 박스를 들 수 있는 거라면
						result += currBox;
						break;
					} else
						continue;

				}
				// 2 12 13 11 18
				// 17 4 7 20 3 9 7 9 20 5

			}

			sb.append("#" + tc + " ").append(result).append("\n");
		}

		System.out.print(sb);

	}

}
