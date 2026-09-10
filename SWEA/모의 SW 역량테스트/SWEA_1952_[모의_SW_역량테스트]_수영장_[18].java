import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int[] payment = new int[4];
	static int[] months = new int[13];
	static int[] answer = new int[13];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= test_case; ++tc) {
			Arrays.fill(answer, 0);  //0으로 초기화
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; ++i) {
				payment[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; ++i) {
				months[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 1; i<=12;++i) {
				DP(i);
			}
			
			sb.append("#").append(tc).append(" ").append(answer[12]).append("\n");
		}
		System.out.print(sb);
	}

	static int DP(int index) {
		if (index >= 3) {
			answer[index]=Math.min(
					Math.min((answer[index - 1] + months[index] * payment[0]), (answer[index - 1] + payment[1])),
					answer[index - 3] + payment[2]);			//3월달 이상부터는 점화식으로 계산 
			if(index == 12)
				answer[index] = Math.min(answer[index], payment[3]);		//마지막 12월달 점화식으로 구한값과 월간권 계산 
		} else
			answer[index] = Math.min((answer[index - 1] + months[index] * payment[0]),
					(answer[index - 1] + payment[1]));
		
		return answer[index];
	}

}
