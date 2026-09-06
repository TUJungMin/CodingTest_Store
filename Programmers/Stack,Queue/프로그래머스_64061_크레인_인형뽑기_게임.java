import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {

        int answer = 0;
        Stack<Integer> basket = new Stack<>();

        // moves 배열 순회 (크레인 작동 횟수만큼 반복)
        for (int move : moves) {
            int col = move - 1; // moves 값은 1-indexed 이므로 배열 인덱스로 변환
            int dollNum = -1;
            for (int i = 0; i < board.length; ++i) {
                // 1. 해당 col 열에서 위에서부터 아래로 훑으며 0이 아닌 첫 인형 찾기
                // - 찾으면 그 칸을 0으로 비우고 반복 종료
                // - 못 찾으면 (열이 모두 비어있으면) 아무 일도 일어나지 않으므로 다음 move로 continue

                if (board[i][col] != 0) {
                    dollNum = board[i][col];
                    board[i][col] = 0;
                    break;
                } else
                    continue;
            }

            if (dollNum == -1) continue; // 해당 열에 인형이 없으면 아무 일도 일어나지 않음

            // 2. 집어올린 인형을 basket에 push
            if (!basket.isEmpty() && dollNum == basket.peek()) {
                // 3. basket에 인형이 1개 이상 쌓여있고, 맨 위 인형과 같은 모양이면
                // - 두 개를 pop하여 제거
                // - answer += 2
                basket.pop();
                answer += 2;
            } else {
                basket.add(dollNum);
            }
        }

        return answer;
    }
}