import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        // 아직 가격이 떨어지지 않은 인덱스들을 쌓아두는 스택
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 현재 가격(prices[i])이 스택 top의 가격보다 낮으면(=떨어짐) 확정
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i);
        }

        // 끝까지 안 떨어진 인덱스들은 마지막 초까지의 기간으로 확정
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = (n - 1) - idx;
        }

        return answer;
    }
}