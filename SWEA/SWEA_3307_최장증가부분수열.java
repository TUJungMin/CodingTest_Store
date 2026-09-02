import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

  public static void main(String args[]) throws IOException
  {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());
    for(int tc = 1; tc<= test_case; ++tc){
      int Num = Integer.parseInt(br.readLine().trim());
      int[] a = new int[Num];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i = 0; i<Num; ++i){
        a[i] = Integer.parseInt(st.nextToken());
      }

      int[] dp = new int[Num];
      int answer = 0;
      for(int i = 0; i<Num; ++i){
        dp[i] = 1;
        for(int j = 0; j<i; ++j){
          if(a[j] < a[i]){
            dp[i] = Math.max(dp[i], dp[j] + 1);
          }
        }
        answer = Math.max(answer, dp[i]);
      }

      sb.append('#').append(tc).append(' ').append(answer).append('\n');
    }

    System.out.print(sb);
  }

}
