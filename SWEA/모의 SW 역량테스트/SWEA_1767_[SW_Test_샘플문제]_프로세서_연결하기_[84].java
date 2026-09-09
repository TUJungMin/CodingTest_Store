import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_곽정민 {
	static int answer;
	static int maxCoreCount;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_case = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= test_case; ++tc) {
			answer = Integer.MAX_VALUE;
			maxCoreCount = 0;	//초기화
			ArrayList<int[]> coreCoord = new ArrayList<>();//core좌표 저장
			int answer = 0;
			int N = Integer.parseInt(br.readLine());
			int[][] Map = new int[N][N];

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if (Map[i][j] == 1)
						coreCoord.add(new int[] { i, j });

				}
			}
			int coreCount = coreCoord.size();
			
		//	combination(0,count);

		}
	}

	static int DFS(int[][] map, int depth,int r,int currCoreCount,int lineWeight) {

		if(depth == r) {	//끝까지 코어 돌았을 때 완성된 조합
			// min값만 뽑으면 됨 todo
			if(currCoreCount>maxCoreCount) {// 현재 선택한 코어 조합이 가장 큰 조합일때
				maxCoreCount = currCoreCount;
				answer = lineWeight;
			}
			else if(currCoreCount==maxCoreCount) {
				//코어 개수가 최대치가 같은데 적은 전선길이 뽑을 때
				answer = Math.min(answer, lineWeight);
				}
		return;
		}
	

		
		
		// index번째를 선택하는 경우
		int line = dfs
		
		//안하는 경우
		dfs
		return ans;
	}

}
