import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		for (int tc = 1; tc <= test_case; ++tc) {
			int result =0;
			st = new StringTokenizer(br.readLine().trim());
			int boxCount = Integer.parseInt(st.nextToken());
			int MaxWeight = Integer.parseInt(st.nextToken());
			int []boxes = new int[boxCount];
			st = new StringTokenizer(br.readLine().trim());
			for(int i = 0; i<boxCount; ++i) {
				boxes[i] = Integer.parseInt(st.nextToken());	
			}
			
			
			result = dfs(boxes, 0, 0, 0, MaxWeight);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int dfs(int[] boxes, int count,int index,int weight,int MaxWeight) {
		
		 // 2개 다 뽑았을 때
	    if (count == 2) {
	        if (weight <= MaxWeight) return weight; // 조건 만족 , 그 무게 반환
	        else return -1;                          // 초과 , 실패
	    }
		
	    // 상자를 다 봤는데 2개를 못 채움  
	    if (index == boxes.length) 
	    	return -1;
		
		int choose = dfs(boxes,count+1,index+1,weight + boxes[index],MaxWeight);
		
		int notchoose = dfs(boxes,count,index+1,weight,MaxWeight);
		
		
		
		return Math.max(choose, notchoose);
	}
	
	
}
