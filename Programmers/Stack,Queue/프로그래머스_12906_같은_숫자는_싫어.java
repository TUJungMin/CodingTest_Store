import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> q = new ArrayDeque<>();
        int last = -1;
        for(int i = 0; i<arr.length; ++i){
            if(last == -1 || last != arr[i]){
                q.offer(arr[i]);
                
                last = arr[i];
            }
        }

        int[] answer = new int[q.size()];
        for(int i = 0; i < answer.length; ++i){
            answer[i] = q.poll();
        }   

        return answer;
    }
}